package mydlq.club.controller;

import mydlq.club.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/base")
public class BaseController {

    /**
     * Get 测试接口
     *
     * @param name 姓名
     * @param sex  性别
     * @return 响应信息
     */
    @GetMapping("/get")
    public String getTest(@RequestParam(value = "name") String name, @RequestParam(value = "sex") String sex) {
        return "Get 请求：——" + "姓名：" + name + ",性别：" + sex;
    }

    /**
     * Form 表单测试接口
     *
     * @param name 姓名
     * @param sex  性别
     * @return 响应信息
     */
    @PostMapping("/form")
    public String postFormTest(@RequestParam(value = "name") String name, @RequestParam(value = "sex") String sex) {
        return "Post From 表单请求：——" + "姓名：" + name + ",性别：" + sex;
    }

    /**
     * Post Json 测试接口
     *
     * @param userInfo 请求体
     * @return 响应信息
     */
    @PostMapping("/json")
    public Object postJsonTest(@RequestBody UserInfo userInfo) {
        return userInfo;
    }

}
