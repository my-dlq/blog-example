package club.mydlq.exceptionhandler.handler;

import club.mydlq.exceptionhandler.enums.ResultEnum;
import club.mydlq.exceptionhandler.entity.ResponseInfo;
import club.mydlq.exceptionhandler.exception.MyException;
import club.mydlq.exceptionhandler.exception.NotFountResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 异常处理器
 */
//@RestControllerAdvice代替@ControllerAdvice,这样在方法上就可以不需要添加@ResponseBody
@RestControllerAdvice(basePackages = "club.mydlq")
public class GlobalExceptionHandler {

    /**
     * MyException异常处理器
     *
     * @param e MyException
     * @return ResponseInfo
     */
    @ExceptionHandler(MyException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseInfo myExceptionHandler(MyException e) {
        // 判断异常消息是否为空，如果抛出异常时在异常中设定了异常消息，
        // 则用优先使用异常中设定的信息替换枚举类中设定的异常信息
        if (!StringUtils.isEmpty(e.getMessage())) {
            return new ResponseInfo(ResultEnum.PARAMETER_ERROR.getCode(), e.getMessage());
        }
        return new ResponseInfo(ResultEnum.PARAMETER_ERROR);
    }

    /**
     * NotFountResourceException异常处理器
     * @param e NotFountResourceException
     * @return ResponseInfo
     */
    @ExceptionHandler(NotFountResourceException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseInfo nodFountResourceExceptionHandler(NotFountResourceException e) {
        if (StringUtils.isEmpty(e.getMessage())) {
            return new ResponseInfo(ResultEnum.NOT_FOUNT_RESOURCE.getCode(), e.getMessage());
        }
        return new ResponseInfo(ResultEnum.NOT_FOUNT_RESOURCE);
    }

    /**
     * 全局异常处理器
     * @param e Exception
     * @return ResponseInfo
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo globleExceptionHandler(Exception e) {
        if (StringUtils.isEmpty(e.getMessage())) {
            return new ResponseInfo(ResultEnum.UNKNOWN_ERROR.getCode(), e.getMessage());
        }
        return new ResponseInfo(ResultEnum.UNKNOWN_ERROR);
    }

}