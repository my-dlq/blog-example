package club.mydlq.exceptionhandler.handler;

import club.mydlq.exceptionhandler.entity.ResultEnum;
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
public class ExceptionHandler {

    /**
     * MyException异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseInfo myExceptionHandler(MyException e){
        ResponseInfo responseInfo = new ResponseInfo();
        // 将枚举类中的异常信息设置到responseInfo对象中
        responseInfo.error(ResultEnum.PARAMETER_ERROR);
        // 判断异常消息是否为空，如果抛出异常时在异常中设定了异常消息，
        // 则用优先使用异常中设定的信息替换枚举类中设定的异常信息
        if (StringUtils.isEmpty(e.getMessage())){
            responseInfo.setMessage(e.getMessage());
        }
        return responseInfo;
    }

    /**
     * NotFountResourceException异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(NotFountResourceException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseInfo nodFountResourceExceptionHandler(NotFountResourceException e){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.error(ResultEnum.NOT_FOUNT_RESOURCE);
        if (StringUtils.isEmpty(e.getMessage())){
            responseInfo.setMessage(e.getMessage());
        }
        return responseInfo;
    }

    /**
     * 全局异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo globleExceptionHandler(Exception e){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.error(ResultEnum.UNKNOWN_ERROR);
        if (StringUtils.isEmpty(e.getMessage())){
            responseInfo.setMessage(e.getMessage());
        }
        return responseInfo;
    }

}