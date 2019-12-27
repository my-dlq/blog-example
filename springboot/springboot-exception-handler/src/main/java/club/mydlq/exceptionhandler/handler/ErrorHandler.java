package club.mydlq.exceptionhandler.handler;

import club.mydlq.exceptionhandler.entity.ResponseInfo;
import club.mydlq.exceptionhandler.enums.ResultEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 用于处理404错误
 *
 * @author mydlq
 */
@RestController
public class ErrorHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseInfo handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // 如果等于 400 错误，则抛出设定的枚举类中的错误信息
        if (HttpStatus.NOT_FOUND.value() == statusCode) {
            return new ResponseInfo().setMessage(ResultEnum.NOT_FOUNT_RESOURCE.getMessage())
                    .setCode(ResultEnum.NOT_FOUNT_RESOURCE.getCode());
        }
        // 返回默认错误
        return new ResponseInfo().setMessage(ResultEnum.UNKNOWN_ERROR.getMessage())
                .setCode(ResultEnum.UNKNOWN_ERROR.getCode());
    }

}
