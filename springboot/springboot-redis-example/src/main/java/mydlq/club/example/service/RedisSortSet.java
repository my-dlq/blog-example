package mydlq.club.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * Redis 有序集合操作
 *
 * @author mydlq
 */
@Slf4j
@Service
public class RedisSortSet {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 向有序集合添加成员
     *
     * @param key   键
     * @param value 值
     * @param score 分数
     * @return 添加成功元素的个数
     */
    public Boolean zAdd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 向有序集合添加多个成员
     *
     * @param key 键
     * @param set 值
     * @return 添加成功元素的个数
     */
    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> set) {
        return redisTemplate.opsForZSet().add(key, set);
    }

    /**
     * 移除有序集合中的一个或多个成员
     *
     * @param key    键
     * @param values 值
     */
    public Long zRem(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 有序集合操作分数的加/减
     *
     * @param key   键
     * @param value 值
     * @param score 分数
     * @return 成员的新分数值
     */
    public Double zincrby(String key, String value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * 返回有序集中，成员的分数值
     *
     * @param key   键
     * @param value 值
     * @return 成员的分数值
     */
    public Double zScore(String key, String value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 返回有序集合中指定成员的索引
     *
     * @param key   键
     * @param value 值
     * @return 成员的索引
     */
    public Long zRank(String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 获取有序集合的成员数（集合长度）
     *
     * @param key 键
     * @return 成员总数
     */
    public Long zCard(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 通过索引区间返回有序集合指定区间内的成员（按分数从小到大排序）
     *
     * @param key   键
     * @param start 开始数（0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推）
     * @param end   结束数（-1 表示最后一个成员，-2 表示倒数第二个成员，以此类推）
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表
     */
    public Set<Object> zrange(String key, int start, int end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 通过索引区间返回有序集合指定区间内的成员 & 分数
     *
     * @param key   键
     * @param start 开始数（0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推）
     * @param end   结束数（-1 表示最后一个成员，-2 表示倒数第二个成员，以此类推）
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表
     */
    public Set<ZSetOperations.TypedTuple<Object>> zRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 返回有序集中指定区间内的成员，通过索引，分数从高到低
     *
     * @param key   键
     * @param start 开始
     * @param end   结束
     * @return 指定区间内的成员
     */
    public Set<Object> zRevRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 通过分数返回有序集合指定区间内的成员
     *
     * @param key 键
     * @param min 最小
     * @param max 最大
     * @return 指定区间内的成员
     */
    public Set<Object> zRangeByScore(String key, int min, int max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

}
