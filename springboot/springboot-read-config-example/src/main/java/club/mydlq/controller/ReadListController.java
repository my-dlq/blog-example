package club.mydlq.controller;

import club.mydlq.service.list.ConfigurationReadList;
import club.mydlq.service.list.ValueReadList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取 List Controller
 */
@RestController
@RequestMapping("/list")
public class ReadListController {

    @Autowired
    private ValueReadList vlueReadList;

    @Autowired
    private ConfigurationReadList configurationReadList;

    @GetMapping("/value")
    public String valueReadList(){
        return vlueReadList.readList();
    }

    @GetMapping("/configuration")
    public String configReadList(){
        return configurationReadList.readList();
    }

}
