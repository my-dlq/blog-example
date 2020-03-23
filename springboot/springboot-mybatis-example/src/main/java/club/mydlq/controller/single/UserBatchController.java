package club.mydlq.controller.single;

import club.mydlq.mappers.single.UserBatchMapper;
import club.mydlq.model.single.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 用户接口（单表批量操作）
 */
@RestController
@RequestMapping("/userBatch")
@Api(tags = "用户接口（单表批量操作）")
public class UserBatchController {

    @Resource
    private UserBatchMapper userBatchMapper;

    @PostMapping("/find")
    @ApiOperation(value = "批量查询\"用户\"", notes = "测试批量查询\"用户\"。")
    public Object getUserBatch(@RequestBody List<Integer> ids) {
        return userBatchMapper.selectBatch(ids);
    }

    @PostMapping
    @ApiOperation(value = "批量插入\"用户\"", notes = "测试批量插入\"用户\"")
    public Object saveUserBatch(@RequestBody List<User> userList) {
        return userBatchMapper.insertBatch(userList);
    }

    @PutMapping
    @ApiOperation(value = "批量更新\"用户\"", notes = "测试批量更新\"用户\"")
    public Object updateUserBatch(@RequestBody List<User> userList) {
        return userBatchMapper.updateBatch(userList);
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除\"用户\"", notes = "测试批量删除\"用户\"")
    public Object deleteUserBatch(@RequestBody List<Integer> ids) {
        return userBatchMapper.deleteBatch(ids);
    }

}
