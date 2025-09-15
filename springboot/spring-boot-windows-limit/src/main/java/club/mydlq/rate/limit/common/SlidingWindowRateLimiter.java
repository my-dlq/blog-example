package club.mydlq.rate.limit.common;

import jakarta.annotation.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 时间窗口算法实现的限流器
 *
 * @author mydlq
 */
@Component
public class SlidingWindowRateLimiter {

    @Resource
    private ResourceLoader resourceLoader;
    @Resource(name = "rateLimitRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过滑动窗口算法实现限流
     *
     * @param key   KEY
     * @param limit 指定时间内允许的请求次数
     * @param time  时间窗口大小，单位秒
     * @return true 表示通过，false 表示被限流
     */
    public boolean allowRequest(String key, int time, int limit) {
        // 当前时间戳(窗口终点)
        long currentTime = System.currentTimeMillis();
        // 时间窗口大小(默认为秒，所以需要转换为毫秒数)
        long windowsSize = TimeUnit.SECONDS.toMillis(time);
        // 随机值(由于ZSET要求member唯一，所以需要加上随机值来保证唯一性)
        String randomVal = UUID.randomUUID().toString();

        // 设置 lua 脚本对象
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setLocation(resourceLoader.getResource("classpath:/redis/slidingWindow.lua"));

        // 设置 lua 脚本中使用到的 ARGV 参数数组
        Object[] argv = new Object[] {currentTime, windowsSize, limit, randomVal};

        // 执行 Lua 脚本
        Long result = redisTemplate.execute(script, Collections.singletonList(key), argv);

        // 判断结果，如果结果为1，则表示通过
        return result == 1L;
    }

}


