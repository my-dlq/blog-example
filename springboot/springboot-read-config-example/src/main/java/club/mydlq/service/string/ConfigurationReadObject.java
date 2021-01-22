package club.mydlq.service.string;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过 @ConfigurationProperties 读取到 Spring Bean 对象中
 */
@Configuration
public class ConfigurationReadObject {

    /**
     * 测试的实体类对象
     */
    @Data
    public static class User {
       private String name;
       private Integer age;
    }

    /**
     * 读取以 my9 为前缀的参数的值，到新建的对象中
     */
    @Bean("user")
    @ConfigurationProperties(prefix = "my9")
    public User readObjectData(){
        return new User();
    }

}
