package club.mydlq.controller;

import club.mydlq.service.valid.ConfigurationReadConfigAndValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取参数进行 valid 效验的 Controller
 */
@RestController
@RequestMapping("/valid")
public class ReadValidController {

    @Autowired
    private ConfigurationReadConfigAndValid configurationReadConfigAndValid;

    @GetMapping("/configuration")
    public String configReadList(){
        return configurationReadConfigAndValid.readString();
    }

}
