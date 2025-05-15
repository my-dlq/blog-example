package club.mydlq.operlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池
 *
 * @author mydlq
 */
@EnableAsync
@Configuration
public class ThreadPoolsConfig {

    @Bean("logAsyncExecutor")
    public ThreadPoolTaskExecutor getParamTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("LogAsyncExecutor-");
        // 核心线程数
        executor.setCorePoolSize(10);
        // 最大线程数
        executor.setMaxPoolSize(20);
        // 队列容量
        executor.setQueueCapacity(10000);
        // 等待时间(秒)
        executor.setAwaitTerminationSeconds(60);
        // 应用关闭时等待任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
