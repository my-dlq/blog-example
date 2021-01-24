package mydlq.club.example.controller;

import mydlq.club.example.model.AccountUserInfo;
import mydlq.club.example.model.UserInfo;
import mydlq.club.example.model.Account;
import mydlq.club.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试的 Controller 类
 *
 * @author mydlq
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 同时查询两个库的数据
     *
     * @return 查询结果
     */
    @GetMapping("/test")
    public Object hello1() {
        // 查询账户数据
        Account account = testService.findAccount(1);
        // 查询用户信息
        UserInfo userInfo = testService.findUserInfo(1);
        // 创建响应对象
        AccountUserInfo accountUserInfo = new AccountUserInfo();
        accountUserInfo.setAccount(account);
        accountUserInfo.setUserInfo(userInfo);
        return accountUserInfo;
    }

}
