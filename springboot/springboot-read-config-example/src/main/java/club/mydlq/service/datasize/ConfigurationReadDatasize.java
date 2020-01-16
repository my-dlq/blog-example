package club.mydlq.service.datasize;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

/**
 * 通过 @ConfigurationProperties 读取 datasize 参数
 */
@Data
@Configuration
@PropertySource(encoding = "UTF-8", ignoreResourceNotFound = true, value = "classpath:application.properties")
@ConfigurationProperties(prefix = "my7")
public class ConfigurationReadDatasize {

    /**
     * 设置以 MB 为单位
     */
    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize fileSize;

    public DataSize getFileSize() {
        return fileSize;
    }

    public void setFileSize(DataSize fileSize) {
        this.fileSize = fileSize;
    }

    public String readDatasize() {
        return String.valueOf(fileSize.toMegabytes());
    }

}
