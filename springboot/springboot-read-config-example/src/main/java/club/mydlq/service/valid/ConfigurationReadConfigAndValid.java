package club.mydlq.service.valid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 通过 @ConfigurationProperties 读取配置并进行 valid 效验
 */
@Data
@Validated
@Configuration
@PropertySource(encoding = "UTF-8", ignoreResourceNotFound = true, value = "classpath:application.properties")
@ConfigurationProperties(prefix = "my8")
public class ConfigurationReadConfigAndValid {

    @NotNull(message = "姓名不能为空")
    private String name;
    @Max(value = 20L,message = "年龄不能超过 20 岁")
    private Integer age;

    public String readString() {
        return name + "," + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
