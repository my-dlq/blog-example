package club.mydlq.mappers.many;

import club.mydlq.model.many.GroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Group 与 User 表操作（一对多）
 */
@Mapper
public interface GroupUserMapper {

    /**
     * 根据组ID查询用户列表
     *
     * @param groupId 组ID
     * @return 用户列表
     */
    GroupUser selectByGroupId(@Param("groupId") Integer groupId);

}
