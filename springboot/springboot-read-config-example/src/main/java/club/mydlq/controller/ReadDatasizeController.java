package club.mydlq.controller;

import club.mydlq.service.datasize.ConfigurationReadDatasize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取 Datasize Controller
 */
@RestController
@RequestMapping("/data")
public class ReadDatasizeController {

    @Autowired
    private ConfigurationReadDatasize configurationReadDatasize;

    @GetMapping("/configuration")
    public String configReadList(){
        return configurationReadDatasize.readDatasize();
    }

}
