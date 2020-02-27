package mydlq.club.lock.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Redis Lock 测试接口
 */
@Slf4j
@RestController
public class LockController {

    /** Redisson 对象 */
    @Autowired
    private RedissonClient redissonClient;

    /** 线程池 */
    ExecutorService executor = Executors.newFixedThreadPool(10);

    @GetMapping("/lock")
    public void lockTest() {
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                // 获取锁对象（可以为"可重入锁"、"公平锁",如果redis是集群模式，还可以使用"红锁"）
                //RLock lock = redissonClient.getFairLock("test");  //公平锁
                RLock lock = redissonClient.getLock("test");     //可重入锁
                try {
                    // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
                    boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
                    // 如果获取锁成功，则执行对应逻辑
                    if (res) {
                        log.info("获取分布式锁，执行对应逻辑1");
                        log.info("获取分布式锁，执行对应逻辑2");
                        log.info("获取分布式锁，执行对应逻辑3");
                    }
                } catch (InterruptedException e) {
                    log.error("", e);
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            });
        }
    }

}
