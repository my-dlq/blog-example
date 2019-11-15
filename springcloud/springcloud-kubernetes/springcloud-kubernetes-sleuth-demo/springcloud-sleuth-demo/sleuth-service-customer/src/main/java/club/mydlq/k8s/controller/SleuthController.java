package club.mydlq.k8s.controller;

import club.mydlq.k8s.feign.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SleuthController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/")
    public String getInfo(){
        return providerService.getSleuthInfo();
    }

}
