package mydlq.club.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户实体对象
 *
 * @author mydlq
 */
@Data
@AllArgsConstructor
public class User {
    private String username;
    private String token;
}
