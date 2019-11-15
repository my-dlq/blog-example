package club.mydlq.k8s.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SleuthController {

    @GetMapping("/")
    public String getSleuthInfo() {
        return "链路测试";
    }

}
