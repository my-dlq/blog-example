package club.mydlq.operlog.mapper;

import club.mydlq.operlog.model.OperLogInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志 Mapper
 *
 * @author mydlq
 */
@Mapper
public interface OperLogMapper {
    /**
     * 插入操作日志
     *
     * @param operLogInfo 操作日志
     * @return 执行结果
     */
    int insert(OperLogInfo operLogInfo);
}
