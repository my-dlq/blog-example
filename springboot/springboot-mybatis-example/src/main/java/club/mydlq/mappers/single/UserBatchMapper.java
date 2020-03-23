package club.mydlq.mappers.single;

import club.mydlq.model.single.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * User 表批量操作 Mapper
 */
@Mapper
public interface UserBatchMapper {

    /**
     * 批量查询用户
     *
     * @param ids 用户ID列表
     * @return 用户列表
     */
    List<User> selectBatch(@Param("ids") List<Integer> ids);

    /**
     * 批量插入用户
     *
     * @param userList 用户列表
     * @return 是否插入成功
     */
    Boolean insertBatch(@Param("userList") List<User> userList);

    /**
     * 批量更新用户
     *
     * @param userList 用户列表
     * @return 是否更新成功
     */
    Boolean updateBatch(@Param("userList") List<User> userList);

    /**
     * 批量查询用户
     *
     * @param ids 用户ID列表
     * @return 用户列表
     */
    Boolean deleteBatch(@Param("ids") List<Integer> ids);

}