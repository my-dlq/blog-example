package club.mydlq.controller;

import club.mydlq.service.string.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取 String Controller
 */
@RestController
@RequestMapping("/string")
public class ReadStringController {

    @Autowired
    private ValueReadString valueReadConfig;

    @Autowired
    private EnvironmentReadString environmentReadConfig;

    @Autowired
    private ConfigurationReadString configurationReadConfig;

    @GetMapping("/value")
    public String valueReadConfig(){
        return valueReadConfig.readString();
    }

    @GetMapping("/env")
    public String envReadConfig(){
        return environmentReadConfig.readString();
    }

    @GetMapping("/util")
    public String utilReadConfig(){
        return PropertiesUtilReadString.readString();
    }

    @GetMapping("/configuration")
    public String configurationReadConfig(){
        return configurationReadConfig.readString();
    }

}
