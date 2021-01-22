package mydlq.club.lock.controller;

import lombok.extern.slf4j.Slf4j;
import mydlq.club.lock.utils.LockUtil;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Zookeeper Lock 测试接口
 *
 * @author mydlq
 */
@Slf4j
@RestController
public class LockController {

    /**
     * curatorFramework对象
     */
    @Autowired
    private LockUtil lockUtil;

    /**
     * 线程池
     */
    ExecutorService executor = Executors.newFixedThreadPool(10);

    @GetMapping("/lock")
    public void lockTest() {
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    String key = "test";
                    // 获取锁
                    InterProcessMutex lock = lockUtil.tryLock(key, 10, TimeUnit.SECONDS);
                    if (lock != null) {
                        // 如果获取锁成功，则执行对应逻辑
                        log.info("获取分布式锁，执行对应逻辑1");
                        log.info("获取分布式锁，执行对应逻辑2");
                        log.info("获取分布式锁，执行对应逻辑3");
                        // 释放锁
                        lockUtil.unLock(key, lock);
                    }
                } catch (Exception e) {
                    log.error("", e);
                }
            });
        }
    }

}
