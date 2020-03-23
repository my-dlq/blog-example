package club.mydlq.model.many;

import club.mydlq.model.single.BaseInfo;
import club.mydlq.model.single.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends User {

    /** 用户基本信息 */
    private BaseInfo baseInfo;

}
