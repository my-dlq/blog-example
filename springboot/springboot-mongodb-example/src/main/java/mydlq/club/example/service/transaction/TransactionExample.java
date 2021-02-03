package mydlq.club.example.service.transaction;

import mydlq.club.example.entity.Status;
import mydlq.club.example.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;

/**
 * 事务示例
 *
 * @author mydlq
 */
@Service
public class TransactionExample {

    /** 设置集合名称 */
    private static final String COLLECTION_NAME = "users";

    @Resource
    private MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Exception.class)
    public Object transactionTest(){
        // 设置两个用户信息
        User user1 = new User()
                .setId("11")
                .setAge(22)
                .setSex("男")
                .setRemake("无")
                .setSalary(1500)
                .setName("shiyi")
                .setBirthday(new Date())
                .setStatus(new Status().setHeight(180).setWeight(150));
        // 插入数据
        User newUser1 = mongoTemplate.insert(user1, COLLECTION_NAME);
        // 抛出异常，观察数据是否进行回滚
        int error = 1/0;
        return newUser1;
    }

}
