package club.mydlq.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author mydlq
 */
@TableName(value = "user")
public class User {
    /** 主键 **/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /** 姓名 **/
    private String name;
    /** 岁数 **/
    private Integer age;

    /** 构造方法 **/
    public User() {
    }
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /** Set、Get方法 **/
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
