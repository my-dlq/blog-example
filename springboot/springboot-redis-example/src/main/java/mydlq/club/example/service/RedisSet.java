package mydlq.club.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * Redis 集合操作
 *
 * @author mydlq
 */
@Slf4j
@Service
public class RedisSet {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisBase redisBase;

    /**
     * 返回集合中的所有成员
     *
     * @param key 键
     * @return 集合中的所有成员
     */
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     *
     * @param key   键
     * @param value 值
     * @return 如果存在则返回 true 否则返回 false
     */
    public Boolean sisMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 向集合添加一个或多个成员
     *
     * @param key    键
     * @param values 值
     * @return 添加成功元素的个数
     */
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 向集合添加一个或多个成员，并设置过期时间
     *
     * @param key    键
     * @param time   时间（秒）
     * @param values 值（可以是多个）
     * @return 添加成功元素的个数
     */
    public Long sAdd(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0) {
            redisBase.expire(key, time);
        }
        return count;
    }

    /**
     * 获取集合的成员数
     *
     * @param key 键
     * @return 返回集合中元素的数量
     */
    public Long sCard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除集合中一个或多个成员
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long sRem(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

}
