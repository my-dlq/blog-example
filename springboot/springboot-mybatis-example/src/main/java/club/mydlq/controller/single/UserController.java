package club.mydlq.controller.single;

import club.mydlq.mappers.single.UserMapper;
import club.mydlq.model.single.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 用户接口（单表操作）
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口（单表操作）")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/all")
    @ApiOperation(value = "查询全部\"用户\"", notes = "测试查询全部\"用户\"。")
    public Object getUserAll() {
        return userMapper.selectAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询\"用户\"", notes = "测试查询\"用户\"。")
    public Object getUser(@PathVariable Integer id) {
        return userMapper.selectById(id);
    }

    @GetMapping("/username/{username}")
    @ApiOperation(value = "根据\"用户名\"查询\"用户\"", notes = "测试根据\"用户名\"查询\"用户\"。")
    public Object getUserByUsername(@PathVariable String username){
        return userMapper.selectByUsername(username);
    }

    @PostMapping
    @ApiOperation(value = "插入\"用户\"", notes = "测试插入\"用户\"。")
    public Object saveUser(@RequestBody User user) {
        return userMapper.insert(user);
    }

    @PostMapping("/return")
    @ApiOperation(value = "插入\"用户\"并且返回\"主键ID\"", notes = "测试插入\"用户\"并且返回\"主键ID\"。")
    public Object saveUserAndReturnId(@RequestBody User user) {
        boolean inserted = userMapper.insertAndReturnId(user);
        if (inserted){
            return user.getId();
        }
        return "插入失败";
    }

    @PutMapping
    @ApiOperation(value = "更新\"用户\"", notes = "测试更新\"用户\"。")
    public Object updateUser(@RequestBody User user) {
        return userMapper.updateById(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除\"用户\"", notes = "测试删除\"用户\"。")
    public Object deleteUser(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }

}
