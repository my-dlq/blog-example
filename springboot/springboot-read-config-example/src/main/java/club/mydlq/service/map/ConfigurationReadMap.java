package club.mydlq.service.map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.Map;

/**
 * 通过 @ConfigurationProperties 方式读取文件中的 Map 数据
 */
@Data
@Configuration
@PropertySource(encoding = "UTF-8", ignoreResourceNotFound = true, value = "classpath:application.properties")
@ConfigurationProperties(prefix = "my2")
public class ConfigurationReadMap {

    private Map<String,String> map;

}
