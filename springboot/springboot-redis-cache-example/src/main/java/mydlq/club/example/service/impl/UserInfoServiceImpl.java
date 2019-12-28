package mydlq.club.example.service.impl;

import mydlq.club.example.entity.UserInfo;
import mydlq.club.example.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.HashMap;

/**
 * 用户信息业务实现
 *
 * @author mydlq
 */
@Service
@CacheConfig(cacheNames = "userInfo")
public class UserInfoServiceImpl implements UserInfoService {

    private HashMap<String, UserInfo> userInfoMap = new HashMap<>();

    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfoMap.put(userInfo.getName(), userInfo);
    }

    @Override
    @Cacheable(key = "#name", unless = "#result==null")
    public UserInfo getByName(String name) {
        if (!userInfoMap.containsKey(name)) {
            return null;
        }
        return userInfoMap.get(name);
    }

    @Override
    @CachePut(key = "#userInfo.name")
    public UserInfo updateUserInfo(UserInfo userInfo) {
        if (!userInfoMap.containsKey(userInfo.getName())) {
            throw new RuntimeException("该用户信息没有找到");
        }
        // 获取存储的对象
        UserInfo newUserInfo = userInfoMap.get(userInfo.getName());
        // 复制要更新的数据到新对象，因为不能更改用户名信息，所以忽略
        BeanUtils.copyProperties(userInfo, newUserInfo, "name");
        // 将新的对象存储，更新旧对象信息
        userInfoMap.put(newUserInfo.getName(), newUserInfo);
        // 返回新对象信息
        return newUserInfo;
    }

    @Override
    @CacheEvict(key = "#name")
    public void deleteByName(String name) {
        userInfoMap.remove(name);
    }

}
