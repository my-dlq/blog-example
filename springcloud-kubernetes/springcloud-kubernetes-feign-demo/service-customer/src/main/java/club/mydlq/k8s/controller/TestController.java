package club.mydlq.k8s.controller;

import club.mydlq.k8s.feign.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String getTestInfo(){
        return testService.getInfo();
    }

}
