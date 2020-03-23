package club.mydlq.mappers.many;

import club.mydlq.model.many.GroupUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * user 与 role 和 user_role 三表操作（多对多）
 */
@Mapper
public interface GroupUserRoleMapper {

    /**
     * 根据组ID查找某个用户组下的全部用户与用户关联角色信息
     *
     * @param groupId 组ID
     * @return 用户与用户角色信息列表
     */
    List<GroupUserRole> selectUserAndRoleByGroupId(@Param("groupId") Integer groupId);

}
