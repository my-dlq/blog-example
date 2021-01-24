package mydlq.club.example.model;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 数据库二账户表
 *
 * @author mydlq
 */
@Data
public class Account {
    /** 主键 */
    private Integer id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 创建时间 */
    private LocalDateTime createTime;
    /** 更新时间 */
    private LocalDateTime updateTime;
}