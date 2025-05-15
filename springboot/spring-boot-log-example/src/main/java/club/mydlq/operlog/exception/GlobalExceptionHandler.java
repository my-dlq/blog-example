package club.mydlq.operlog.exception;

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
     * 处理通用异常
     *
     * @param ex 捕获的异常
     * @return 响应实体
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // 默认返回异常信息
        return ResponseEntity.ok(ex.getMessage());
    }

}