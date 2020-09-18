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
     * 尝试获取分布式锁
     *
     * @param key        分布式锁 key
     * @param value      分布式锁 value
     * @param expireTime 超时时间
     * @param timeUnit   超时时间单位
     * @return 是否成功获取分布式锁
     */
    public boolean tryLock(String key, String value, int expireTime, TimeUnit timeUnit) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, timeUnit);
        if (Boolean.TRUE.equals(result)) {
            log.error("申请锁(" + key + "|" + value + ")成功");
            return true;
        }
        log.error("申请锁(" + key + "|" + value + ")失败");
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
            log.info("解锁(" + key + "|" + value + ")成功");
        } else {
            log.info("解锁(" + key + "|" + value + ")失败！");
        }
    }

}