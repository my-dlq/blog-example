package club.mydlq.jenkins.option;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.client.util.EncodingUtils;
import java.io.IOException;

/**
 * 自定义服务，补充 jenkins java client 没有封装的内容
 *
 * @author mydlq
 */
public class CustomService {

    // Jenkins 对象
    private JenkinsServer jenkinsServer;
    // http 客户端对象
    private JenkinsHttpClient jenkinsHttpClient;

    /**
     * 构造方法中调用连接 Jenkins 方法
     */
    CustomService() {
        // 连接 Jenkins
        jenkinsServer = JenkinsConnect.connection();
        // 设置客户端连接 Jenkins
        jenkinsHttpClient = JenkinsConnect.getClient();
    }

    /**
     * 创建 Jenkins Job 并指定 Job 类型
     */
    public void createJob() {
        try {
            // job 名称
            String jobName = "test-project";
            // 创建 Job 的xml
            String jobXml = "<project>\n" +
                                "<keepDependencies>false</keepDependencies>\n" +
                                "<properties/>\n" +
                                "<scm class=\"hudson.scm.NullSCM\"/>\n" +
                                "<canRoam>false</canRoam>\n" +
                                "<disabled>false</disabled>\n" +
                                "<blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>\n" +
                                "<blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>\n" +
                                "<triggers/>\n" +
                                "<concurrentBuild>false</concurrentBuild>\n" +
                                "<builders/>\n" +
                                "<publishers/>\n" +
                                "<buildWrappers/>\n" +
                                "<link type=\"text/css\" id=\"dark-mode\" rel=\"stylesheet\" href=\"\"/>\n" +
                            "</project>";
            // 创建 Jenkins Job 并指定 Job 类型
            jenkinsHttpClient.post_xml("createItem?name=" + EncodingUtils.encodeParam(jobName) +
                    "&mode=hudson.model.FreeStyleProject", jobXml, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 自定义服务
        CustomService customService = new CustomService();
        // 创建指定类型的 Job
        customService.createJob();
    }

}
