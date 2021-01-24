package mydlq.club.example.dao.db1;

import mydlq.club.example.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据库一 Mysql 的 Mapper
 *
 * @author mydlq
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 根据主键查找数据
     *
     * @param id 主键ID
     * @return 数据
     */
    UserInfo selectByPrimaryKey(Integer id);

}