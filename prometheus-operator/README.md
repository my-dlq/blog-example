# Prometheus Operator

这是在 Kubernetes 环境下部署 Prometheus Operator 的部署文件。

详情请参考我的个人博客地址： http://www.mydlq.club/article/10/

## 部署步骤：

### 1、创建一个名称为 fast 的 StorageClass。

> 注意，由于存储类型的不同，StorageClass 配置也不一样，这里用的是 GlusterFS 存储,例如NFS等配置请自行查找。

```yaml
apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: fast                            #---SorageClass 名称
provisioner: kubernetes.io/glusterfs    #---标识 provisioner 为 GlusterFS
parameters:
  resturl: "http://10.10.249.63:8080"   
  restuser: "admin"
  gidMin: "40000"
  gidMax: "50000"
  volumetype: "none" 
```

### 2、创建 Grafana PVC

```yaml
kind: PersistentVolumeClaim
apiVersion: v1
metadata:
  name: grafana
  namespace: monitoring  #---指定namespace为monitoring
spec:
  storageClassName: fass #----指定StorageClass为上面创建的fast
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 5Gi
```

### 3、部署前各节点提前下载镜像

为了保证服务启动速度，所以最好部署节点提前下载所需镜像。

```bash
docker pull quay-mirror.qiniu.com/coreos/configmap-reload:v0.0.1
docker pull quay-mirror.qiniu.com/coreos/prometheus-config-reloader:v0.29.0
docker pull quay-mirror.qiniu.com/coreos/prometheus-operator:v0.29.0
docker pull quay-mirror.qiniu.com/coreos/k8s-prometheus-adapter-amd64:v0.4.1
docker pull quay-mirror.qiniu.com/prometheus/alertmanager:v0.17.0
docker pull quay-mirror.qiniu.com/prometheus/node-exporter:v0.17.0 
docker pull quay-mirror.qiniu.com/coreos/kube-rbac-proxy:v0.4.1
docker pull quay-mirror.qiniu.com/coreos/kube-state-metrics:v1.5.0
docker pull registry.aliyuncs.com/google_containers/addon-resizer:1.8.4
docker pull quay-mirror.qiniu.com/prometheus/prometheus:v2.7.2
```

### 4、更改 kubernetes 配置

由于 Kubernetes 集群是由 kubeadm 搭建的，其中 kube-scheduler 默认绑定 IP 是 127.0.0.1 地址。Prometheus Operator 是通过节点 IP 去访问，所以 我们将 kube-scheduler 绑定的地址更改成 0.0.0.0。

编辑 /etc/kubernetes/manifests/kube-scheduler.yaml 文件

```bash
$ vim /etc/kubernetes/manifests/kube-scheduler.yaml
```

将 command 的 bind-address 地址更改成 0.0.0.0

```yaml
......
spec:
  containers:
  - command:
    - kube-scheduler
    - --bind-address=0.0.0.0    #改为0.0.0.0
    - --kubeconfig=/etc/kubernetes/scheduler.conf
    - --leader-elect=true
......
```

### 5、安装Prometheus Operator

### 1、下载该部署文件

```bash
$ git clone https://github.com/my-dlq/blog-example.git
```

然后进入该文件夹

```bash
$ cd blog-example/prometheus-operator
```

### 2、创建 namespace

```bash
$ kubectl apply -f 00namespace-namespace.yaml
```

### 3、安装 Operator

```bash
$ kubectl apply -f operator/
```

查看 Pod，等 pod 创建起来在进行下一步

```bash
$ kubectl get pods -n monitoring

NAME                                   READY   STATUS    RESTARTS
prometheus-operator-5d6f6f5d68-mb88p   1/1     Running   0  
```

### 4、安装其它组件

```bash
kubectl apply -f adapter/
kubectl apply -f alertmanager/
kubectl apply -f node-exporter/
kubectl apply -f kube-state-metrics/
kubectl apply -f grafana/
kubectl apply -f prometheus/
kubectl apply -f serviceMonitor/
```

查看 Pod 状态

```bash
$ kubectl get pods -n monitoring

NAME                                   READY   STATUS    RESTARTS
alertmanager-main-0                    2/2     Running   0          
alertmanager-main-1                    2/2     Running   0         
alertmanager-main-2                    2/2     Running   0         
grafana-b6bd6d987-2kr8w                1/1     Running   0
kube-state-metrics-6f7cd8cf48-ftkjw    4/4     Running   0          
node-exporter-4jt26                    2/2     Running   0  
node-exporter-h88mw                    2/2     Running   0          
node-exporter-mf7rr                    2/2     Running   0 
prometheus-adapter-df8b6c6f-jfd8m      1/1     Running   0          
prometheus-k8s-0                       3/3     Running   0  
prometheus-k8s-1                       3/3     Running   0  
prometheus-operator-5d6f6f5d68-mb88p   1/1     Running   0  
```

## 6、查看 Prometheus & Grafana

### 1、查看 Prometheus

打开地址： http://192.168.2.11:32101 查看 Prometheus 采集的目标，看其各个采集服务状态有木有错误。

![](http://ww1.sinaimg.cn/large/007vhU0ely1g3tcklnr5jj30t60c5t8r.jpg)

![](http://ww1.sinaimg.cn/large/007vhU0ely1g3tckfdzddj30t60uh75l.jpg)
### 2、查看 Grafana

打开地址： http://192.168.2.11:32102 查看 Grafana 图表，看其 Kubernetes 集群是否能正常显示。

- 默认用户名：admin
- 默认密码：admin

![](http://ww1.sinaimg.cn/large/007vhU0ely1g3tcg38zedj30t60fm3z1.jpg)

可以看到各种仪表盘

![](http://ww1.sinaimg.cn/large/007vhU0ely1g3tci1ehi4j30t60qytah.jpg)