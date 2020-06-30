package mydlq.club.model;

import lombok.Data;

/**
 * 用户实体类
 *
 * @author mydlq
 */
@Data
public class User {
    private String username;
    private String password;
    private String name;
    private String sex;
}
