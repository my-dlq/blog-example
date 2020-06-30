package mydlq.club.controller;

import mydlq.club.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author mydlq
 */
@RestController
public class LoginController {

    /**
     * 设置存储的 SESSION 名称
     */
    public static final String USER_SESSION_NAME = "user";
    /**
     * 设置模拟用户信息
     */
    private static final Map<String, User> USER_MAP = new HashMap<>();

    /**
     * 初始化，创建两个模拟用户
     */
    @PostConstruct
    public void init() {
        User user1 = new User();
        user1.setUsername("mydlq1");
        user1.setPassword("123456");
        User user2 = new User();
        user2.setUsername("mydlq2");
        user2.setPassword("123456");
        USER_MAP.put(user1.getUsername(), user1);
        USER_MAP.put(user2.getUsername(), user2);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 检测是否已经登录
        if (request.getSession().getAttribute(USER_SESSION_NAME) != null) {
            return "已经登陆，无需重复登陆";
        }
        // 验证用户名密码，如果验证失败则返回错误信息
        if (validate(username, password)) {
            // 验证成功则将登录用户信息存储
            request.getSession().setAttribute(USER_SESSION_NAME, USER_MAP.get(username));
            // 跳转到首页
            response.sendRedirect("/index.html");
        }
        return "用户名或密码不正确";
    }

    /**
     * 验证用户名、密码是否正确
     *
     * @param username 用户名
     * @param password 密码
     */
    private boolean validate(String username, String password) {
        // 验证用户名密码，如果验证失败则返回错误信息
        for (User user : USER_MAP.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}