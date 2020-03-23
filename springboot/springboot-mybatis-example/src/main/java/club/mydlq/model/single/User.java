package club.mydlq.model.single;

import lombok.Data;

@Data
public class User {
    /** 主键ID */
    private Integer id;
    /** 组号 */
    private Integer groupId;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
}