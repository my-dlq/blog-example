package club.mydlq.model.many;

import club.mydlq.model.single.Role;
import club.mydlq.model.single.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends User {

    /** 角色列表 */
    private List<Role> roles;

}