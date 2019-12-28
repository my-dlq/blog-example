package mydlq.club.example.service;

import mydlq.club.example.entity.User;

/**
 * 用户业务接口
 * @author mydlq
 */
public interface UserService {

    /**
     * 增加账户
     *
     * @param user 账户
     */
    void addUser(User user);

    /**
     * 获取账户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByUsername(String username);

    /**
     * 修改账户
     *
     * @param user 用户信息
     * @return 用户信息
     */
    User updateUser(User user);

    /**
     * 删除账户
     * @param username 用户名
     */
    void deleteByUsername(String username);

}
