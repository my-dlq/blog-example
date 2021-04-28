package mydlq.club.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
public class BasicAuthController {

    /**
     * 用于 Basic Auth 鉴权验证的接口
     */
    @GetMapping("/test")
    public String authTest() {
        return "Basic Auth 验证成功！";
    }

}
