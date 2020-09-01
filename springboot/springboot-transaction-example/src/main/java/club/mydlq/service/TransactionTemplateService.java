package club.mydlq.service;

import club.mydlq.mapper.UserMapper;
import club.mydlq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 编程式事务 TransactionTemplate
 *
 * @author mydlq
 */
@Service
public class TransactionTemplateService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public void addUser() {
        // 设置事务传播属性
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 设置事务的隔离级别
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        // 设置事务超时时间
        transactionTemplate.setTimeout(30000);
        // 执行业务逻辑
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            public void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    // 设置待插入用户信息
                    User user = new User("小豆丁", 20);
                    // 数据库中插入数据
                    userMapper.insert(user);
                    // 创建异常，方便测试回滚
                    int exception = 1 / 0;
                } catch (Exception e) {
                    // 发生异常，进行回滚
                    transactionStatus.setRollbackOnly();
                }
            }
        });
    }

}
