package mydlq.club.example.model;

import lombok.Data;
import lombok.ToString;

/**
 * 数据库一用户信息表
 *
 * @author mydlq
 */
@Data
@ToString
public class UserInfo {
    /**
    * 主键
    */
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别（0:男，1:女）
    */
    private String gender;
    /**
    * 岁数
    */
    private Byte age;
}