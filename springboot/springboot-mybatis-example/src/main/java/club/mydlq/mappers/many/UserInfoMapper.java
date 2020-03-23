package club.mydlq.mappers.many;

import club.mydlq.model.many.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * User 与 BaseInfo 表操作（一对一）
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 根据用户ID查询用户与用户基本信息
     *
     * @param userId 用户ID
     * @return 用户与用户基本信息
     */
    UserInfo selectByUserId(@Param("userId") Integer userId);

    /**
     * 查询全部用户与用户基本信息
     *
     * @return 用户与基本信息列表
     */
    List<UserInfo> selectAll();

}
