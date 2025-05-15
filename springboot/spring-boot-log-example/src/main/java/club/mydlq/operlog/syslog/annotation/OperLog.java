package club.mydlq.operlog.syslog.annotation;

import club.mydlq.operlog.syslog.enums.OperTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解，作为 AOP 中的切入点，应用于 Controller 层
 *
 * @author mydlq
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperLog {
    /**
     * 系统模块
     */
    String module();
    /**
     * 接口描述
     */
    String description() default "";
    /**
     * 操作类型
     */
    OperTypeEnum operType();
}
