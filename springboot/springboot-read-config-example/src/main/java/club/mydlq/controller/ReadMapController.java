package club.mydlq.controller;

import club.mydlq.service.map.ConfigurationReadMap;
import club.mydlq.service.map.ValueReadMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取 Map Controller
 */
@RestController
@RequestMapping("/map")
public class ReadMapController {

    @Autowired
    private ConfigurationReadMap configurationReadMap;

    @Autowired
    private ValueReadMap valueReadMap;

    @GetMapping("/configuration")
    public String configReadMap(){
        return configurationReadMap.getMap().get("name") + "," + configurationReadMap.getMap().get("sex") + "," + configurationReadMap.getMap().get("age");
    }

    @GetMapping("/value")
    public String valueReadMap(){
        return valueReadMap.readMap();
    }

}
