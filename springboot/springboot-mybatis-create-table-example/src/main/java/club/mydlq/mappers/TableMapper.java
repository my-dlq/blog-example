package club.mydlq.mappers;

import org.apache.ibatis.annotations.Mapper;

/**
 * 创建数据库表 Mapper
 *
 * @author mydlq
 */
@Mapper
public interface TableMapper {

    /**
     * 创建数据库表
     *
     * @param tableName 表名称
     */
    void createTable(String tableName);

}