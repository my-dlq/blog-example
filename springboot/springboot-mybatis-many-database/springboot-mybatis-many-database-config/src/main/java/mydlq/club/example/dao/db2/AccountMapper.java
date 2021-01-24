package mydlq.club.example.dao.db2;

import mydlq.club.example.model.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据库二 Postgresql 的 Mapper
 *
 * @author mydlq
 */
@Mapper
public interface AccountMapper {
    /**
     * 根据主键查找数据
     *
     * @param id 主键ID
     * @return 数据
     */
    Account selectByPrimaryKey(Integer id);

}