def label = "jnlp-agent"
timeout(time: 900, unit: 'SECONDS') {
    podTemplate(label: label,cloud: 'kubernetes' ){
        node (label) {
            stage('Git阶段'){
                // 执行 Git 命令进行 Clone 项目
                git changelog: true,
                    branch: "${params.GIT_BRANCH}",
                    credentialsId: "${params.GIT_CREADENTIAL}",
                    url: "${GIT_PROJECT_URL}"
            }
            stage('Maven阶段'){
                container('maven') {  
                    // 创建 Maven 需要的 Settings.xml 文件
                    configFileProvider([configFile(fileId: "global-maven-settings", targetLocation: "settings.xml")]){
                        // 执行 Maven 命令构建项目，并且设置 Maven 配置为刚刚创建的 Settings.xml 文件
                        sh "mvn -T 1C clean ${MAVEN_BUILD_OPTION} -Dmaven.test.skip=true --settings settings.xml"
                    }
                }
            }
            stage('读取pom.xml参数阶段'){
                // 读取 Pom.xml 参数
                pom = readMavenPom file: './pom.xml'
                // 设置 appName 和 appVersion 两个全局参数
                appName = "${pom.artifactId}"
                appVersion = "${pom.version}"
            }
            stage('Docker阶段'){
                container('docker') {
                    // 创建 Dockerfile 文件，但只能在方法块内使用
                    configFileProvider([configFile(fileId: "${params.DOCKER_DOCKERFILE_ID}", targetLocation: "Dockerfile")]){
                        // 设置 Docker 镜像名称
                        dockerImageName = "${params.DOCKER_HUB_URL}/${params.DOCKER_HUB_GROUP}/${appName}:${appVersion}"
                        if ("${params.DOCKER_HUB_GROUP}" == '') {
                            dockerImageName = "${params.DOCKER_HUB_URL}/${appName}:${appVersion}"
                        }
                        // 提供 Docker 环境，使用 Docker 工具来进行 Docker 镜像构建与推送
                        docker.withRegistry("http://${params.DOCKER_HUB_URL}", "${params.DOCKER_HUB_CREADENTIAL}") {
                            def customImage = docker.build("${dockerImageName}")
                            customImage.push()
                        }
                    }
                }
            }
            stage('Kubernetes 阶段'){
                // kubectl 镜像
                container('kubectl') {
                    // 使用 Kubectl Cli 插件的方法，提供 Kubernetes 环境，在其方法块内部能够执行 kubectl 命令
                    withKubeConfig([credentialsId: "${params.KUBERNETES_CREADENTIAL}",serverUrl: "https://kubernetes.default.svc.cluster.local"]) {
                        // 使用 configFile 插件，创建 Kubernetes 部署文件 deployment.yaml
                        configFileProvider([configFile(fileId: "${params.KUBERNETES_DEPLOYMENT_ID}", targetLocation: "deployment.yaml")]){
                            // 读取 Kubernetes 部署文件
                            deploy = readFile encoding: "UTF-8", file: "deployment.yaml"
                            // 替换部署文件中的变量，并将替换后的文本赋予 deployfile 变量
                            deployfile = deploy.replaceAll("#APP_NAME","${appName}")
                                           .replaceAll("#APP_REPLICAS","${params.KUBERNETES_APP_REPLICAS}")
                                           .replaceAll("#APP_IMAGE_NAME","${dockerImageName}")
                                           .replaceAll("#APP_UUID",(new Random().nextInt(100000)).toString())
                            // 生成新的 Kubernetes 部署文件，内容为 deployfile 变量中的文本，文件名称为 "deploy.yaml"
                            writeFile encoding: 'UTF-8', file: './deploy.yaml', text: "${deployfile}"
                            // 输出新创建的部署 yaml 文件内容
                            sh "cat deploy.yaml"
                            // 执行 Kuberctl 命令进行部署操作
                            sh "kubectl apply -n ${params.KUBERNETES_NAMESPACE} -f deploy.yaml"
                        }
                    }
                }
            }
            stage('应用启动检查'){
                // 设置检测延迟时间 10s,10s 后再开始检测
                sleep 10
                // 健康检查地址
                httpRequestUrl = "http://${appName}.${params.KUBERNETES_NAMESPACE}:${params.HTTP_REQUEST_PORT}${params.HTTP_REQUEST_URL}"
                // 循环使用 httpRequest 请求，检测服务是否启动
                for(n = 1; n <= "${params.HTTP_REQUEST_NUMBER}".toInteger(); n++){
                    try{
                        // 输出请求信息和请求次数
                        print "访问服务：${appName} \n" +
                              "访问地址：${httpRequestUrl} \n" +
                              "访问次数：${n}"
                        // 如果非第一次检测，就睡眠一段时间，等待再次执行 httpRequest 请求
                        if(n > 1){
                            sleep "${params.HTTP_REQUEST_INTERVAL}".toInteger()
                        }
                        // 使用 HttpRequest 插件的 httpRequest 方法检测对应地址
                        result = httpRequest "${httpRequestUrl}"
                        // 判断是否返回 200
                        if ("${result.status}" == "200") {
                            print "Http 请求成功，流水线结束"
                            break
                        } 
                    }catch(Exception e){
                        print "监控检测失败，将在 ${params.HTTP_REQUEST_INTERVAL} 秒后将再次检测。"
                        // 判断检测次数是否为最后一次检测，如果是最后一次检测，并且还失败了，就对整个 Jenkins 任务标记为失败
                        if (n == "${params.HTTP_REQUEST_NUMBER}".toInteger()) {
                            currentBuild.result = "FAILURE"
                        }
                    }
                }
            }
        }
    }
}