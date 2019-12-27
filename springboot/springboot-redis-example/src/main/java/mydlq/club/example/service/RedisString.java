package mydlq.club.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis 字符串操作
 *
 * @author mydlq
 */
@Slf4j
@Service
public class RedisString {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取指定 key 的值
     *
     * @param key 键
     * @return 返回 key 的值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取所有一个或多个给定 key 的值
     *
     * @param list Key 集合
     * @return 值集合
     */
    public List<Object> mGet(Collection<String> list) {
        return redisTemplate.opsForValue().multiGet(list);
    }

    /**
     * 设置给定 key 的值（如果 key 已经存在就覆写旧值）
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 同时设置一个或多个 key-value 对
     *
     * @param map 键值集合
     */
    public void set(Map<String, Object> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 设置给定 key 的值，并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间(秒)
     */
    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return 递增后的值
     */
    public Long incr(String key, long delta) {
        if (delta > 0) {
            throw new IllegalArgumentException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return 递减后的值
     */
    public Long decr(String key, long delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("递减因子必须小于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

}
