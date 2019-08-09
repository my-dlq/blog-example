package club.mydlq.exceptionhandler.handler;

import club.mydlq.exceptionhandler.entity.ResponseInfo;
import club.mydlq.exceptionhandler.entity.ResultEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于处理404错误
 */
@RestController
public class ErrorHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseInfo handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        ResponseInfo responseInfo = new ResponseInfo();
        if (statusCode == 404){
            responseInfo.error(ResultEnum.NOT_FOUNT_RESOURCE);
        }else{
            responseInfo.error(ResultEnum.UNKNOWN_ERROR);
        }
        return responseInfo;
    }

}
