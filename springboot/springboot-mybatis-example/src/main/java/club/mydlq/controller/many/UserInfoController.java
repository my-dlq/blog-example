package club.mydlq.controller.many;

import club.mydlq.mappers.many.UserInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 用户与用户信息接口（多表查询，一对一）
 */
@RestController
@RequestMapping("/userInfo")
@Api(tags = "用户与用户信息接口（多表查询，一对一）")
public class UserInfoController {

    @Resource
    private UserInfoMapper userInfoMapper;

    @GetMapping("/all")
    @ApiOperation(value = "查询全部\"用户与用户信息\"列表", notes = "测试查询全部\"用户与用户信息\"列表。")
    public Object getUserInfoAll() {
        return userInfoMapper.selectAll();
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "查询某个\"用户与用户信息\"", notes = "测试查询某个\"用户与用户信息。")
    public Object getUserInfo(@PathVariable Integer userId) {
        return userInfoMapper.selectByUserId(userId);
    }

}
