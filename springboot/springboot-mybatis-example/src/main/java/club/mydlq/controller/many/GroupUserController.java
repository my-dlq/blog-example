package club.mydlq.controller.many;

import club.mydlq.mappers.many.GroupUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 组与用户接口（多表操作，一对多）
 */
@RestController
@RequestMapping("/groupUser")
@Api(tags = "组与用户接口（多表操作，一对多）")
public class GroupUserController {

    @Resource
    private GroupUserMapper groupUserMapper;

    @GetMapping("/{userId}")
    @ApiOperation(value = "查询某组\"用户\"列表", notes = "测试查询某组\"用户\"列表。")
    public Object getUserInfo(@PathVariable Integer userId) {
        return groupUserMapper.selectByGroupId(userId);
    }

}
