package mydlq.club.lock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
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
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                // 生成 6 位的 UUID，将该 UUID 作为 Redis 的值。
                // 注：设置个 UUID 随机值充当 Redis 存入的 Value 是为了保证，在分布式环境且存在多实例情况下，
                // 进行加锁和解锁操作的都是相同的进程（同一个实例），这样能够避免该锁被别的进程（实例）执行解锁操作。
                String value = UUID.randomUUID().toString().substring(0, 6);
                // 获取分布式锁，设置锁超时时间为 10 秒
                boolean execute = redisLock.lock("test", value, 10, TimeUnit.SECONDS);
                // 如果获取锁成功，则执行对应逻辑
                if (execute) {
                    log.info("线程 {} - 获取分布式锁，执行对应逻辑", Thread.currentThread().getName());
                    // 执行完成，释放分布式锁
                    redisLock.unLock("test", value);
                }
            });
        }
    }

    @GetMapping("/tryLock")
    public void tryLockTest() {
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                // 生成 6 位的 UUID，将该 UUID 作为 Redis 的值。
                // 注：设置个 UUID 随机值充当 Redis 存入的 Value 是为了保证，在分布式环境且存在多实例情况下，
                // 进行加锁和解锁操作的都是相同的进程（同一个实例），这样能够避免该锁被别的进程（实例）执行解锁操作。
                String value = UUID.randomUUID().toString().substring(0, 6);
                // 获取分布式锁，设置锁超时时间为 10 秒,尝试获取锁的时间为 2 秒(2秒内没有获取锁就会获取锁失败)
                boolean execute = redisLock.tryLock("test", value, 10, TimeUnit.SECONDS, 2, TimeUnit.SECONDS);
                // 如果获取锁成功，则执行对应逻辑
                if (execute) {
                    log.info("线程 {} - 获取分布式锁，执行对应逻辑", Thread.currentThread().getName());
                    // 执行完成，释放分布式锁
                    redisLock.unLock("test", value);
                }
            });
        }
    }

}
