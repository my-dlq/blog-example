package mydlq.club.example.controller;

import mydlq.club.example.entity.User;
import mydlq.club.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户 Controller
 *
 * @author mydlq
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/user")
    public String createUser(@RequestBody User user) {
        userService.addUser(user);
        return "SUCCESS";
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteByUsername(username);
        return "SUCCESS";
    }

}
