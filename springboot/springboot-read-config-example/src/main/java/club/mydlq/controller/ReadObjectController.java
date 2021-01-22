package club.mydlq.controller;

import club.mydlq.service.string.ConfigurationReadObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取参数到对象
 */
@RestController
@RequestMapping("/object")
public class ReadObjectController {

    @Autowired
    private ConfigurationReadObject.User user;

    @GetMapping("/value")
    public Object valueReadConfig(){
        return user;
    }

}
