package club.mydlq.exceptionhandler.controller;

import club.mydlq.exceptionhandler.entity.ResponseInfo;
import club.mydlq.exceptionhandler.enums.ResultEnum;
import club.mydlq.exceptionhandler.exception.MyException;
import club.mydlq.exceptionhandler.exception.NotFountResourceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 Controller
 *
 * @author mydlq
 */
@RestController
public class TestController {

    /**
     * 正常接口
     */
    @GetMapping("/normal")
    public ResponseInfo normal() {
        String data = "模拟的响应数据";
        return new ResponseInfo().setCode(ResultEnum.SUCCESS.getCode())
                .setMessage(ResultEnum.SUCCESS.getMessage())
                .setData(data);
    }

    /**
     * 抛出自定义不能发行资源异常，测试自定义无法发现资源异常处理器
     */
    @GetMapping("/rsexception")
    public String createException() throws NotFountResourceException {
        throw new NotFountResourceException("NullPointerException 空指针异常信息");
    }

    /**
     * 抛出自定义异常，测试自定义异常处理器
     */
    @GetMapping("/myexception")
    public String createMyException() throws MyException {
        throw new MyException("MyException 自定义异常信息");
    }

    /**
     * 抛出空指针异常，测试全局异常处理器
     */
    @GetMapping("/exception")
    public String createGlobleException() {
        throw new NullPointerException("NullPointerException 空指针异常信息");
    }

}
