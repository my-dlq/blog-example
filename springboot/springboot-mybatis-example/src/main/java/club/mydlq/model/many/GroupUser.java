package club.mydlq.model.many;

import club.mydlq.model.single.Group;
import club.mydlq.model.single.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupUser extends Group {

    /** 用户列表 */
    private List<User> users;

}
