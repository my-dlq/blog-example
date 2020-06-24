package mydlq.club.lock.config;

import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.curator.framework.CuratorFrameworkFactory;

/**
 * 初始化 Zookeeper Curator 客户端
 *
 * @author mydlq
 */
@Configuration
public class ZookeeperConfig {

    /**
     * 创建 CuratorFramework 对象并连接 Zookeeper
     *
     * @param zookeeperProperties 从 Spring 容器载入 zookeeperProperties Bean 对象，读取连接 ZK 的参数
     * @return CuratorFramework
     */
    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(ZookeeperProperties zookeeperProperties) {
        return CuratorFrameworkFactory.newClient(
                zookeeperProperties.getAddress(),
                zookeeperProperties.getSessionTimeoutMs(),
                zookeeperProperties.getConnectionTimeoutMs(),
                new RetryNTimes(zookeeperProperties.getRetryCount(),
                        zookeeperProperties.getElapsedTimeMs()));
    }

}
