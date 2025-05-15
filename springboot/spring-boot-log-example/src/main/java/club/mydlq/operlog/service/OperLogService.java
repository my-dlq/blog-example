package club.mydlq.operlog.service;

import club.mydlq.operlog.model.OperLogInfo;
import club.mydlq.operlog.mapper.OperLogMapper;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 操作日志 Service
 *
 * @author mydlq
 */
@Service
public class OperLogService {

    @Resource
    private OperLogMapper operLogMapper;

    /**
     * 使用异步方式记录操作日志 (使用 logAsyncExecutor 线程池)
     */
    @Async(value = "logAsyncExecutor")
    public void saveLog(OperLogInfo operLogInfo) {
        operLogMapper.insert(operLogInfo);
    }

}
