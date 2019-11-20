package club.mydlq.valid.controller;

import club.mydlq.valid.entity.ResponseResult;
import club.mydlq.valid.entity.User;
import club.mydlq.valid.enums.ResultEnum;
import club.mydlq.valid.exception.ParamaErrorException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class TestController {

    /**
     * 获取用户信息
     *
     * @param username 姓名
     * @return ResponseResult
     */
    @Validated
    @GetMapping("/user")
    public ResponseResult findUserInfo(@RequestParam String username) {
        if (username == null || "".equals(username)) {
            throw new ParamaErrorException("username 不能为空");
        }
        return new ResponseResult(ResultEnum.SUCCESS);
    }


    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @PostMapping("/user")
    public ResponseResult addUserInfo(@Valid @RequestBody User user) {
        return new ResponseResult(ResultEnum.SUCCESS);
    }

}