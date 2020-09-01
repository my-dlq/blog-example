package club.mydlq.service;

import club.mydlq.mapper.UserMapper;
import club.mydlq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 声明式事务 @Transactional
 *
 * @author mydlq
 */
@Service
public class TransactionalService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 30000)
    public void addUser() {
        // 设置待插入用户信息
        User user = new User("小豆丁", 20);
        // 数据库中插入数据
        userMapper.insert(user);
        // 创建异常，方便测试回滚
        int exception = 1 / 0;
    }

}