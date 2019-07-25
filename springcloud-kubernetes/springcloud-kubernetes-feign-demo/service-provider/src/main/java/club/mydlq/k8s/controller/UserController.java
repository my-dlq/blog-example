package club.mydlq.k8s.controller;

import club.mydlq.feign.TestInterface;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements TestInterface {

    @Override
    public String getInfo() {
        return "Hello World!";
    }

}
