package mydlq.club.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Redis 列表操作
 *
 * @author mydlq
 */
@Slf4j
@Service
public class RedisList {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisBase redisBase;

    /**
     * 获取列表指定范围内的元素，正数则表示正向查找，负数则倒叙查找
     *
     * @param key   键
     * @param start 开始
     * @param end   结束
     * @return boolean
     * @see <a href="https://redis.io/commands/lrange">Redis Documentation: LRANGE</a>
     */
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取列表长度
     *
     * @param key 键
     * @return 列表长度
     */
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引获取列表中的元素
     *
     * @param key   键
     * @param index 索引（index>=0时，0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推）
     * @return 列表中的元素
     */
    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 在列表前端添加一个值
     *
     * @param key   键
     * @param value 值
     * @return 返回列表当前长度
     */
    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 在列表前端添加一个值，并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间（秒）
     * @return 返回列表当前长度
     */
    public Long lPush(String key, Object value, long time) {
        Long size = redisTemplate.opsForList().leftPush(key, value);
        if (time > 0) {
            redisBase.expire(key, time);
        }
        return size;
    }

    /**
     * 在列表前端添加多个值
     *
     * @param key   键
     * @param value 值
     * @return 返回列表当前长度
     */
    public Long lPush(String key, List<Object> value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 在列表前端添加多个值，并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间（秒）
     * @return 返回列表当前长度
     */
    public Long lPush(String key, List<Object> value, long time) {
        Long size = redisTemplate.opsForList().leftPushAll(key, value);
        if (time > 0) {
            redisBase.expire(key, time);
        }
        return size;
    }

    /**
     * 在列末尾端添加一个值
     *
     * @param key   键
     * @param value 值
     * @return 返回列表当前长度
     */
    public Long rPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 在列表末尾添加一个值，并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间（秒）
     * @return 返回列表当前长度
     */
    public Long rPush(String key, Object value, long time) {
        Long size = redisTemplate.opsForList().leftPush(key, value);
        if (time > 0) {
            redisBase.expire(key, time);
        }
        return size;
    }

    /**
     * 在列表末尾添加多个值
     *
     * @param key   键
     * @param value 值
     * @return 返回列表当前长度
     */
    public Long rPush(String key, List<Object> value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 在列表末尾添加多个值，并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间（秒）
     * @return 返回列表当前长度
     */
    public Long rPush(String key, List<Object> value, long time) {
        Long size = redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0) {
            redisBase.expire(key, time);
        }
        return size;
    }

    /**
     * 通过索引设置列表元素的值
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public void lSet(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除列表元素
     *
     * @param key   键
     * @param count 移除数量（"负数"则从列表倒叙查找删除 count 个对应的值; "整数"则从列表正序查找删除 count 个对应的值;）
     * @param value 值
     * @return 成功移除的个数
     */
    public Long lRem(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

}
