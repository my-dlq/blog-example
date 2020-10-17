package mydlq.club.lock.controller;

import java.util.UUID;
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

    /**
     * 测试接口，这里循环创建多线程执行任务
     */
    @GetMapping("/lock")
    public void lockTest() {
        for (int i = 0; i < 1000; i++) {
            // 在线程池中，执行带分布式锁的业务逻辑
            executor.submit(() -> {
                executeLockService();
            });
        }
    }

    /**
     * 执行业务逻辑，并且使用分布式锁
     */
    private void executeLockService() {
        // 设置 Redis 键，该键名跟业务挂钩，应为一个不变的值，这里设置为 test
        String key = "test";
        // 生成 UUID，将该 UUID 最为 Redis 的值。
        // 注：设置个 UUID 随机值充当 Redis 存入的 Value 是为了保证，在分布式环境且存在多实例情况下，
        // 进行加锁和解锁操作的都是相同的进程（同一个实例），这样能够避免该锁被别的进程（实例）执行解锁操作。
        String value = UUID.randomUUID().toString();
        // 获取分布式锁，设置超时时间为 10 秒
        boolean execute = redisLock.tryLock(key, value, 10, TimeUnit.SECONDS);
        // 如果获取锁成功，则执行对应逻辑
        if (execute) {
            log.info("获取分布式锁，执行对应逻辑1");
            log.info("获取分布式锁，执行对应逻辑2");
            log.info("获取分布式锁，执行对应逻辑3");
            // 执行完成，释放分布式锁
            redisLock.unLock(key, value);
        }
    }

}