package club.mydlq.controller.many;

import club.mydlq.mappers.many.GroupUserRoleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 组、用户与角色接口（多表查询，一对一对多）
 */
@RestController
@RequestMapping("/GroupUserRole")
@Api(tags = "组、用户与角色接口（多表查询，一对一对多）")
public class GroupUserRoleController {

    @Resource
    private GroupUserRoleMapper groupUserRoleMapper;

    @GetMapping("/{groupId}")
    @ApiOperation(value = "查询某组\"用户与角色\"列表", notes = "测试查询某组\"用户与角色\"列表。")
    public Object getUserInfo(@PathVariable Integer groupId) {
        return groupUserRoleMapper.selectUserAndRoleByGroupId(groupId);
    }

}
