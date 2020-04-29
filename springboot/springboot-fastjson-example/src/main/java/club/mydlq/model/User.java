package club.mydlq.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

/**
 * 测试实体类
 *
 * @author mydlq
 */
public class User {

    /**
     * 设置序列化后字段名称
     */
    @JSONField(name = "my-name")
    private String name;
    /** 不序列化也不反序列化 **/
    @JSONField(serialize = false, deserialize = false)
    private String sex;
    /** 设置时间格式化 **/
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
