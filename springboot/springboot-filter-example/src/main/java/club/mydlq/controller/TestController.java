package club.mydlq.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/aa/hello")
    public String hello1(){
        return "hello world! aa";
    }

    @GetMapping("/bb/hello")
    public String hello2(){
        return "hello world! bb";
    }

    @GetMapping("/cc/hello")
    @Validated
    public String hello3(){
        return "hello world! cc";
    }

}
