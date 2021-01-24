package mydlq.club.example.model;

import lombok.Data;

/**
 * 包含库一库二的实体对象
 *
 * @author mydlq
 */
@Data
public class AccountUserInfo {
    private UserInfo userInfo;
    private Account account;
}