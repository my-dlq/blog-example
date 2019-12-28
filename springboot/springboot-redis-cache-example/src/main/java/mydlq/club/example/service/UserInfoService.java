package mydlq.club.example.service;

import mydlq.club.example.entity.UserInfo;

/**
 * 用户信息业务接口
 * @author mydlq
 */
public interface UserInfoService {

    /**
     * 增加用户信息
     *
     * @param userInfo 用户信息
     */
    void addUserInfo(UserInfo userInfo);

    /**
     * 获取用户信息
     *
     * @param name 姓名
     * @return 用户信息
     */
    UserInfo getByName(String name);

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息
     * @return 用户信息
     */
    UserInfo updateUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     * @param name 姓名
     */
    void deleteByName(String name);

}
