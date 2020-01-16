package club.mydlq.service.string;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 通过 @PropertySource 指定读取的文件中 String 配置，通过 @ConfigurationProperties 过滤前缀
 */
@Data
@Configuration
@PropertySource(encoding = "UTF-8", ignoreResourceNotFound = true, value = "classpath:application.properties")
@ConfigurationProperties(prefix = "my1")
public class ConfigurationReadString {

    private String name;
    private String sex;
    private String age;

    public String readString(){
        return name + "," + sex + "," + age;
    }

}
