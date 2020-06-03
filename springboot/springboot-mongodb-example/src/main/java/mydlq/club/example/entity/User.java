package mydlq.club.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.Date;

/**
 * 测试用的用户实体类
 *
 * @author mydlq
 */
@Data
@ToString
@Accessors(chain = true)
public class User {

    /**
     * 使用 @MongoID 能更清晰的指定 _id 主键
     */
    @MongoId
    private String id;
    private String name;
    private String sex;
    private Integer salary;
    private Integer age;
    @JsonFormat( pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date birthday;
    private String remake;
    private Status status;

}
