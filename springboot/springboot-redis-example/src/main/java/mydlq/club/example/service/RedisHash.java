package mydlq.club.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Redis 哈希操作
 *
 * @author mydlq
 */
@Slf4j
@Service
public class RedisHash {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisBase redisBase;

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key  键
     * @param item 项
     * @return 值
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 将哈希表 key 中的字段 field 的值设为 value（如果不存在将创建）
     *
     * @param key   键
     * @param item  项
     * @param value 值
     */
    public void hSet(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * 将哈希表 key 中的字段 field 的值设为 value，并设置过期时间
     * （如果不存在将创建，且如果已存在的hash表有时间,这里将会替换原有的时间）
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间（秒）
     */
    public void hSet(String key, String item, Object value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        if (time > 0) {
            redisBase.expire(key, time);
        }
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key 键
     * @return 一个包含多个给定字段关联值的表，表值的排列顺序和指定字段的请求顺序一样
     */
    public Map<Object, Object> hmGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 同时将多个“域-值”对设置到哈希表 key 中
     *
     * @param key 键
     * @param map 对应多个键值
     */
    public void hmSet(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 同时将多个“域-值”对设置到哈希表 key 中，并设置过期时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间（秒）
     */
    public void hmSet(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        if (time > 0) {
            redisBase.expire(key, time);
        }
    }

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key  键
     * @param item 项（可以使多个）
     * @return 成功删除字段的数量
     */
    public Long hDel(String key, Object... item) {
        return redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 如果存在则返回 true，不存在则返回 false
     */
    public Boolean hExists(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 哈希证书递增或递减数（正数递增、负数递减），如果不存在,就会创建一个，并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   增加或减少的数
     * @return 执行操作后，哈希表中字段的值
     */
    public Double hIncrby(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

}
