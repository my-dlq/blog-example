package club.mydlq.service.string;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 通过 @Value 读取 String 配置
 */
@Service
public class ValueReadString {

    @Value("${my1.name}")
    private String name;

    @Value("${my1.sex}")
    private String sex;

    @Value("${my1.age:18}")
    private String age;

    public String readString() {
        return name + "," + sex + "," + age;
    }

}
