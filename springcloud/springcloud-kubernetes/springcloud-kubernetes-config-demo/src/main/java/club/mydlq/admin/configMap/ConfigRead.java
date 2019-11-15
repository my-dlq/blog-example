package club.mydlq.admin.configMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config")
public class ConfigRead {

    private String stringValue;
    private Integer numberValue;
    private boolean booleanValue;

    public String getStringValue() {
        return stringValue;
    }
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
    public Integer getNumberValue() {
        return numberValue;
    }
    public void setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
    }
    public boolean isBooleanValue() {
        return booleanValue;
    }
    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

}
