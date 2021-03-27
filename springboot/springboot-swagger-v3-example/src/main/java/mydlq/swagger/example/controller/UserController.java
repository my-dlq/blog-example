package mydlq.swagger.example.controller;

import io.swagger.annotations.*;
import mydlq.swagger.example.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * @author mydlq
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("users")
public class UserController {

    @ApiOperation(value = "查询用户", notes = "查询用户信息。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "查询的用户姓名", required = true),
            @ApiImplicitParam(name = "age", value = "查询的用户岁数", required = false)
    })
    @GetMapping(value = "/{name}")
    public ResponseEntity<User> findUser(@PathVariable(value = "name", required = true) String name,
                                         @RequestParam(value = "age", required = false) Integer age) {
        // 创建模拟数据，然后返回数据
        return ResponseEntity.ok(new User(name, "男", age, LocalDateTime.now()));
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "创建用户", notes = "创建新的用户信息。")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return ResponseEntity.ok(user.getName() + " created");
    }

    @PutMapping(value = "/")
    @ApiOperation(value = "更新用户", notes = "更新用户信息。")
    public ResponseEntity<User> modifyUser(@RequestBody User user) {
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{name}")
    @ApiOperation(value = "删除用户", notes = "删除用户信息。")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(name + " deleted");
    }

}
