package mydlq.club.example.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 用户信息实体
 */
@Data
@ToString
public class UserInfo {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
}
