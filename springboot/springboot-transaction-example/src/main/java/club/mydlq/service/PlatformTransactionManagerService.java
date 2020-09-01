package club.mydlq.service;

import club.mydlq.mapper.UserMapper;
import club.mydlq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 编程式事务 PlatformTransactionManager
 *
 * @author mydlq
 */
@Service
public class PlatformTransactionManagerService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public void addUser() {
        // 获取事务定义对象，方便配置事务隔属性
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        // 设置事务隔离级别
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        // 设置事务传播行为
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 设置事务超时时间
        definition.setTimeout(30000);
        // 获取事务状态对象
        TransactionStatus status = platformTransactionManager.getTransaction(definition);
        try {
            // 设置待插入用户信息
            User user = new User("小豆丁", 20);
            // 数据库中插入数据
            userMapper.insert(user);
            // 创建异常，方便测试回滚
            int exception = 1 / 0;
            // 事务提交
            platformTransactionManager.commit(status);
        } catch (Exception e) {
            // 发生异常，进行回滚
            platformTransactionManager.rollback(status);
        }
    }

}
