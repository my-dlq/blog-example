package club.mydlq.rate.limit.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流注解
 *
 * @author mydlq
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    /**
     * 限流的key，如果不指定，则使用类名+方法名
     */
    String key() default "";

    /**
     * 时间窗口大小(即限流时间，单位秒，默认60秒)
     */
    int time() default 60;

    /**
     * 限制次数
     */
    int limit();
}
