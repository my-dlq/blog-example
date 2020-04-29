package club.mydlq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * 测试实体类
 *
 * @author mydlq
 */
public class User {

    /** 序列化也反序列化 **/
    @Expose()
    private String name;
    /** 序列化也反序列化，且设置序列化后的名称 **/
    @Expose()
    @SerializedName("birth-day")
    private Date birthday;
    /** 不序列化也不反序列化 **/
    @Expose(serialize = false, deserialize = false)
    private String sex;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

}
