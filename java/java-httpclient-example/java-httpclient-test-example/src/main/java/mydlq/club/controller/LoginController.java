package mydlq.club.controller;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * Form 表单登录
     *
     * @param username 用户名
     * @param password 密码
     * @param httpSession Session 对象
     * @return 登录信息
     */
    @PostMapping("/from")
    public String formLogin(@RequestParam(value = "t_username") String username, @RequestParam(value = "t_password") String password, HttpSession httpSession) {
        if ("admin".equals(username) && "123456".equals(password)) {
            httpSession.setAttribute("username", username);
            return "登录成功！";
        }
        return "登录失败！";
    }

    /**
     * 用户验证是否登录的测试接口
     *
     * @param httpSession 操作 Session 的对象
     * @return 测试信息
     */
    @GetMapping("/test")
    public String formLoginTest(HttpSession httpSession) {
        if (httpSession.getAttribute("username") != null) {
            return "已经登录，成功获取信息！";
        }
        return "没有登录，获取信息失败！";
    }

}
