package mydlq.club.example.controller;

import mydlq.club.example.entity.UserInfo;
import mydlq.club.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息 Controller
 *
 * @author mydlq
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/userInfo/{name}")
    public UserInfo getUserInfo(@PathVariable String name) {
        return userInfoService.getByName(name);
    }

    @PostMapping("/userInfo")
    public String createUserInfo(@RequestBody UserInfo userInfo) {
        userInfoService.addUserInfo(userInfo);
        return "SUCCESS";
    }

    @PutMapping("/userInfo")
    public UserInfo updateUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }

    @DeleteMapping("/userInfo/{name}")
    public String deleteUserInfo(@PathVariable String name) {
        userInfoService.deleteByName(name);
        return "SUCCESS";
    }

}
