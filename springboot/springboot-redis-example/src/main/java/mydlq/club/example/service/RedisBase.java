package mydlq.club.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Redis 基本操作
 *
 * @author mydlq
 */
@Slf4j
@Service
public class RedisBase {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定 key 的过期时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 根据 key 获取过期时间（-1 即为永不过期）
     *
     * @param key 键
     * @return 过期时间
     */
    public Long ttl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断 key 是否存在
     *
     * @param key 键
     * @return 如果存在 key 则返回 true，否则返回 false
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除 key
     *
     * @param key 键
     */
    public Long del(String... key) {
        if (key == null || key.length < 1) {
            return 0L;
        }
        return redisTemplate.delete(Arrays.asList(key));
    }

    /**
     * 获取 Key 的类型
     *
     * @param key 键
     */
    public String type(String key) {
        DataType dataType = redisTemplate.type(key);
        assert dataType != null;
        return dataType.code();
    }

}
