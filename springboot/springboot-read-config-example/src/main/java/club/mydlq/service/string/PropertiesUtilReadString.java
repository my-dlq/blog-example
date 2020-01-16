package club.mydlq.service.string;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import java.io.IOException;
import java.util.Properties;

/**
 * 通过 properties 工具读取 String 配置
 */
@Slf4j
public class PropertiesUtilReadString {

    private PropertiesUtilReadString(){}

    public static String readString() {
        try {
            ClassPathResource resource = new ClassPathResource("application.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            String name = properties.getProperty("my1.name", "");
            String sex = properties.getProperty("my1.sex", "");
            String age = properties.getProperty("my1.age", "18");
            return name + "," + sex + "," + age;
        } catch (IOException e) {
            log.error("", e);
        }
        return "";
    }

}
