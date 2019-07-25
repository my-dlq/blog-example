package club.mydlq.admin.controller;

import club.mydlq.admin.configMap.ConfigRead;
import club.mydlq.admin.secret.SecretRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigController {

    @Autowired
    private ConfigRead configRead;

    @Autowired
    private SecretRead secretRead;

    @GetMapping("/configmap")
    public  Map<String,Object> getConfig() {
        Map<String,Object> map = new HashMap<>();
        map.put("StringValue:",configRead.getStringValue());
        map.put("NumberValue:",configRead.getNumberValue());
        map.put("BooleanValue:",configRead.isBooleanValue());
        return map;
    }

    @GetMapping("/secret")
    public  Map<String,Object> getSecret() {
        Map<String,Object> map = new HashMap<>();
        map.put("username:",secretRead.getUsername());
        map.put("password:",secretRead.getPassword());
        return map;
    }

}
