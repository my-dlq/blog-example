package mydlq.club.example.service.impl;

import mydlq.club.example.entity.User;
import mydlq.club.example.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.HashMap;

/**
 * 用户业务实现
 *
 * @author mydlq
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    private HashMap<String, User> userMap = new HashMap<>();

    @Override
    public void addUser(User user) {
        userMap.put(user.getUsername(), user);
    }

    @Override
    @Cacheable(key = "#username",unless = "#result==null ")
    public User getUserByUsername(String username) {
        if (!userMap.containsKey(username)) {
            return null;
        }
        return userMap.get(username);
    }

    @Override
    @CachePut(key = "#user.username")
    public User updateUser(User user) {
        if (!userMap.containsKey(user.getUsername())){
            throw new RuntimeException("不存在该用户");
        }
        // 获取存储的对象
        User newUser = userMap.get(user.getUsername());
        // 复制要更新的数据到新对象，因为不能更改用户名信息，所以忽略
        BeanUtils.copyProperties(user, newUser, "username");
        // 将新的对象存储，更新旧对象信息
        userMap.put(newUser.getUsername(), newUser);
        // 返回新对象信息
        return newUser;
    }

    @Override
    @CacheEvict(key = "#username")
    public void deleteByUsername(String username) {
        userMap.remove(username);
    }

}
