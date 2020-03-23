package club.mydlq.model.single;

import lombok.Data;
import java.util.Date;

@Data
public class BaseInfo {
    /** 主键ID */
    private Integer id;
    /** 用户ID */
    private String userId;
    /** 姓名 */
    private String name;
    /** 性别 */
    private String sex;
    /** 出生日期 */
    private Date birthday;
    /** 备注 */
    private String remark;
}
