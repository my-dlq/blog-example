package club.mydlq.rate.limit.common;

import club.mydlq.rate.limit.exception.RateLimitException;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 限流 Aspect
 *
 * @author mydlq
 */
@Aspect
@Component
public class RateLimitAspect {

    @Resource
    private SlidingWindowRateLimiter slidingWindowRateLimiter;

    /**
     * 限流KEY名称
     */
    private static final String DEFAULT_RATE_LIMIT_KEY = "rate:limit:%s_%s";

    @Around("@annotation(rateLimit)")
    public Object rateLimitAdvice(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取类名
        String className = signature.getDeclaringType().getSimpleName();
        // 获取方法名
        String methodName = signature.getName();

        // 拼接限流key
        String key = StringUtils.isNotBlank(rateLimit.key()) ?
                rateLimit.key() :
                String.format(DEFAULT_RATE_LIMIT_KEY,  className, methodName);

        // 判断是否达到限流阈值，是则抛出限流异常
        boolean allowRequest = slidingWindowRateLimiter.allowRequest(key, rateLimit.time(), rateLimit.limit());
        if (!allowRequest) {
            throw new RateLimitException("请求过于频繁，请稍后再试");
        }

        return joinPoint.proceed();
    }

}
