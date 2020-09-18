package mydlq.club.lock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Redis Lock 测试接口
 *
 * @author mydlq
 */
@Slf4j
@RestController
public class LockController {

    /**
     * Redis 分布式锁操作类
     */
    @Autowired
    private RedisLock redisLock;

    /**
     * 线程池
     */
    ExecutorService executor = Executors.newFixedThreadPool(10);

    @GetMapping("/lock")
    public void lockTest() {
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                // 获取分布式锁，设置超时时间为 10 秒
                boolean execute = redisLock.tryLock("test", "", 10, TimeUnit.SECONDS);
                // 如果获取锁成功，则执行对应逻辑
                if (execute) {
                    log.info("获取分布式锁，执行对应逻辑1");
                    log.info("获取分布式锁，执行对应逻辑2");
                    log.info("获取分布式锁，执行对应逻辑3");
                    // 执行完成，释放分布式锁
                    redisLock.unLock("test","");
                }
            });
        }
    }

}
