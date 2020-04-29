package club.mydlq.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Json 配置类
 *
 * @author mydlq
 */
@Configuration
public class JsonConfig {

    /**
     * 配置 HttpMessageConverters 来使用 Gson 实现 JSON 转换
     */
    @Bean
    public HttpMessageConverters customConverters() {
        // 创建 convert 消息转换对象
        Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 创建与配置 Fastjson 对象
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 解决中文乱码
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 将convert 添加到 converters
        messageConverters.add(fastConverter);
        return new HttpMessageConverters(true, messageConverters);
    }

}

