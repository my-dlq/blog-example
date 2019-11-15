package club.mydlq.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${test:默认值}")
    private String test;

    @GetMapping("/test")
    public String test(){
        return "test的值为:" + test;
    }
}
