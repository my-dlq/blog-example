package club.mydlq.service.time;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 通过 @ConfigurationProperties 读取 time 参数
 */
@Data
@Configuration
@PropertySource(encoding = "UTF-8", ignoreResourceNotFound = true, value = "classpath:application.properties")
@ConfigurationProperties(prefix = "my6")
public class ConfigurationReadTime {

    /**
     * 设置以秒为单位
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration time;

    public String readTime() {
        return String.valueOf(time.getSeconds());
    }

}
