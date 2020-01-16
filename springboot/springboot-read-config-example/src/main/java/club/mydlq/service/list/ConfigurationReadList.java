package club.mydlq.service.list;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.List;

/**
 * 通过 @ConfigurationProperties 方式读取文件中的 List 数据
 */
@Data
@Configuration
@PropertySource(encoding = "UTF-8", ignoreResourceNotFound = true, value = "classpath:application.properties")
@ConfigurationProperties(prefix = "my4")
public class ConfigurationReadList {

    private List<String> list;

    public String readList() {
        StringBuilder builder = new StringBuilder();
        for (String str:list){
            builder.append(str).append(",");
        }
        // 移除最后的“，”号
        builder.delete(builder.length()-1,builder.length());
        return builder.toString();
    }

}
