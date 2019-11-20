package club.mydlq.valid.entity;

import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * user实体类
 */
@Data
public class User {
    @NotBlank(message = "姓名不为空")
    private String username;
    @NotBlank(message = "密码不为空")
    private String password;
    // 嵌套必须加 @Valid，否则嵌套中的验证不生效
    @Valid
    @NotNull(message = "userinfo不能为空")
    private UserInfo userInfo;
}
