package club.mydlq.elasticsearch.model.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private String id;
    private String name;
    private Integer age;
    private String username;

}
