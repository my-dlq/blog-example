package club.mydlq.mappers.single;

import java.util.List;
import club.mydlq.model.single.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * User 表操作 Mapper
 */
@Mapper
public interface UserMapper {

    /**
     * 查询全部用户数据列表
     *
     * @return 用户信息列表
     */
    List<User> selectAll();

    /**
     * 根据主键ID查询用户
     *
     * @param id 主键ID
     * @return 用户
     */
    User selectById(@Param("id") Integer id);

    /**
     * 根据用户名模糊查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 根据主键ID更新用户
     *
     * @param user 用户实体对象
     * @return 是否更新成功
     */
    Boolean updateById(@Param("user") User user);

    /**
     * 插入用户
     *
     * @param user 用户实体对象
     * @return 是否插入成功
     */
    Boolean insert(@Param("user") User user);

    /**
     * 插入用户并返回主键ID到实体对象
     *
     * @param user 用户实体对象
     * @return 是否插入成功
     */
    Boolean insertAndReturnId(@Param("user") User user);

    /**
     * 根据主键ID删除用户信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    Boolean deleteById(@Param("id") Integer id);

}