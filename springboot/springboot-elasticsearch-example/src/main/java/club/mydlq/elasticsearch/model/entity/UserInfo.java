package club.mydlq.elasticsearch.model.entity;

import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
@ToString
public class UserInfo {
    /** 姓名 */
    private String name;
    /** 地址 */
    private String address;
    /** 岁数 */
    private Integer age;
    /** 工资 */
    private Float salary;
    /** 出生日期 */
    private String birthDate;
    /** 备注信息 */
    private String remark;
    /** 创建时间 */
    private Date createTime;
}
