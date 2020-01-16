package club.mydlq.controller;

import club.mydlq.service.time.ConfigurationReadTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取 time Controller
 */
@RestController
@RequestMapping("/time")
public class ReadTimeController {

    @Autowired
    private ConfigurationReadTime configurationReadTime;

    @GetMapping("/configuration")
    public String configReadList(){
        return configurationReadTime.readTime();
    }

}
