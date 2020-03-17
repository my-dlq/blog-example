package club.mydlq.exceptionhandler.handler;

import club.mydlq.exceptionhandler.enums.ResultEnum;
import club.mydlq.exceptionhandler.entity.ResponseInfo;
import club.mydlq.exceptionhandler.exception.MyException;
import club.mydlq.exceptionhandler.exception.NotFountResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
//@RestControllerAdvice代替@ControllerAdvice,这样在方法上就可以不需要添加@ResponseBody
@RestControllerAdvice(basePackages = "club.mydlq.exceptionhandler.controller")
public class GlobalExceptionHandler {

    /**
     * 全局异常处理器
     *
     * @param e Exception
     * @return ResponseInfo
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseInfo globleExceptionHandler(Exception e, HttpServletResponse response) {
        // 判断是否为 MyException 异常
        if (e instanceof MyException) {
            // 设置 HTTP 状态码
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            // 返回错误信息
            return new ResponseInfo()
                    .setMessage(e.getMessage())
                    .setCode(ResultEnum.PARAMETER_ERROR.getCode());
        }
        // 判断是否为 NotFountResourceException 异常
        else if (e instanceof NotFountResourceException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseInfo()
                    .setCode(ResultEnum.NOT_FOUNT_RESOURCE.getCode())
                    .setMessage(e.getMessage());
        }
        // 判断是否为丢失请求参数异常
        else if (e instanceof MissingServletRequestParameterException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseInfo()
                    .setCode(ResultEnum.PARAMETER_MISSING_ERROR.getCode())
                    .setMessage(ResultEnum.PARAMETER_MISSING_ERROR.getMessage());
        }
        // 判断是否为缺少请求体异常
        else if (e instanceof HttpMessageNotReadableException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseInfo()
                    .setCode(ResultEnum.REQUEST_MISSING_BODY_ERROR.getCode())
                    .setMessage(ResultEnum.REQUEST_MISSING_BODY_ERROR.getMessage());
        }
        // 判断是否为请求参数错误异常
        else if (e instanceof MethodArgumentNotValidException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseInfo()
                    .setCode(ResultEnum.PARAMETER_ERROR.getCode())
                    .setMessage(ResultEnum.PARAMETER_ERROR.getMessage());
        }
        // 不知道异常原因，默认返回未知异常
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseInfo()
                .setCode(ResultEnum.UNKNOWN_ERROR.getCode())
                .setMessage(ResultEnum.UNKNOWN_ERROR.getMessage());
    }

}