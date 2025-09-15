package club.mydlq.rate.limit.common;

import club.mydlq.rate.limit.exception.RateLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author mydlq
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 限流异常处理
     * @param ex 限流异常
     * @return 限流后响应的信息
     */
    @ExceptionHandler(RateLimitException.class)
    public ResponseEntity<String> handleRateLimitExceededException(RateLimitException ex) {
        // 返回 HTTP 状态码 429 和自定义的限流错误消息
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ex.getMessage());
    }

}