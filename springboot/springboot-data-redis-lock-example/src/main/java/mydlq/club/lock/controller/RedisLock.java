package mydlq.club.lock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Redis 锁实现
 *
 * @author mydlq
 */
@Slf4j
@Service
public class RedisLock {

    /**
     * RedisTemplate 对象
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取分布式锁
     *
     * @param key            分布式锁 key
     * @param value          分布式锁 value
     * @param expireTime     锁的超时时间,防止死锁
     * @param expireTimeUnit 锁的超时时间单位
     * @return 是否成功获取分布式锁
     */
    public boolean lock(String key, String value, int expireTime, TimeUnit expireTimeUnit) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, expireTimeUnit);
        if (Boolean.TRUE.equals(result)) {
            log.info("线程 {} - 申请锁( {} | {} )成功", Thread.currentThread().getName(), key, value);
            return true;
        }
        log.info("线程 {} - 申请锁( {} | {} )失败", Thread.currentThread().getName(), key, value);
        return false;
    }

    /**
     * 尝试获取分布式锁,并设置获取锁的超时时间
     *
     * @param key                分布式锁 key
     * @param value              分布式锁 value
     * @param expireTime         锁的超时时间,防止死锁
     * @param expireTimeUnit     锁的超时时间单位
     * @param acquireTimeout     尝试获取锁的等待时间,如果在时间范围内获取锁失败,就结束获取锁
     * @param acquireTimeoutUnit 尝试获取锁的等待时间单位
     * @return 是否成功获取分布式锁
     */
    public boolean tryLock(String key, String value, int expireTime, TimeUnit expireTimeUnit, int acquireTimeout, TimeUnit acquireTimeoutUnit) {
        try {
            // 尝试自旋获取锁,等待配置的一段时间,如果在时间范围内获取锁失败,就结束获取锁
            long end = System.currentTimeMillis() + acquireTimeoutUnit.toMillis(acquireTimeout);
            while (System.currentTimeMillis() < end) {
                // 尝试获取锁
                Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, expireTimeUnit);
                // 验证是否成功获取锁
                if (Boolean.TRUE.equals(result)) {
                    log.info("线程 {} - 申请锁( {} | {} )成功", Thread.currentThread().getName(), key, value);
                    return true;
                }
                // 睡眠 50 毫秒
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error("申请锁(" + key + "|" + value + ")错误:", e);
        }
        log.info("线程 {} - 申请锁( {} | {} )失败", Thread.currentThread().getName(), key, value);
        return false;
    }

    /**
     * 释放锁
     *
     * @param key   设置分布式锁 key
     * @param value 设置分布式锁 value
     */
    public void unLock(String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Long result = redisTemplate.execute(redisScript, Arrays.asList(key, value));
        if (result != null && result != 0L) {
            log.info("线程 {} - 解锁({} | {}})成功", Thread.currentThread().getName(), key, value);
        } else {
            log.info("线程 {} - 解锁({} | {})失败！", Thread.currentThread().getName(), key, value);
        }
    }

}