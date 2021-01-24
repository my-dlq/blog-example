package mydlq.club.example.service;

import mydlq.club.example.dao.db1.UserInfoMapper;
import mydlq.club.example.dao.db2.AccountMapper;
import mydlq.club.example.model.UserInfo;
import mydlq.club.example.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试 Service，分别查询两中数据库的数据
 *
 * @author mydlq
 */
@Service
public class TestService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 数据库1，查询用户信息
     * @param id 主键ID
     * @return 用户信息
     */
    public UserInfo findUserInfo(Integer id){
        return userInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 数据库2，查询账户数据
     * @param id 主键ID
     * @return 账户数据
     */
    public Account findAccount(Integer id){
        return accountMapper.selectByPrimaryKey(id);
    }

}