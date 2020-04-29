package club.mydlq.config;

import com.google.gson.*;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.ResourceListing;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.UiConfiguration;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

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
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        // 创建与配置 Gson 对象
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                // 使用 Gson 通过的适配器来适配 Swagger
                .registerTypeAdapter(Json.class, SwaggerJsonSerializer.INSTANCE)
                .registerTypeAdapter(ApiListing.class, SwaggerApiListingJsonSerializer.INSTANCE)
                .registerTypeAdapter(SwaggerResource.class, SwaggerResourceSerializer.INSTANCE)
                .registerTypeAdapter(ResourceListing.class, SwaggerResourceListingJsonSerializer.INSTANCE)
                .registerTypeAdapter(SwaggerResource.class, SwaggerResourceSerializer.INSTANCE)
                .registerTypeAdapter(SecurityConfiguration.class, SwaggerSecurityConfigurationSerializer.INSTANCE)
                .registerTypeAdapter(UiConfiguration.class, SwaggerUiConfigurationSerializer.INSTANCE)
                .create();
        gsonHttpMessageConverter.setGson(gson);
        // 将convert 添加到 converters
        messageConverters.add(gsonHttpMessageConverter);
        return new HttpMessageConverters(true, messageConverters);
    }

    static class SwaggerJsonSerializer implements JsonSerializer<Json> {
        public final static SwaggerJsonSerializer INSTANCE = new SwaggerJsonSerializer();

        @Override
        public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
            return JsonParser.parseString(src.value());
        }
    }

    static class SwaggerApiListingJsonSerializer implements JsonSerializer<ApiListing> {
        public final static SwaggerApiListingJsonSerializer INSTANCE = new SwaggerApiListingJsonSerializer();

        @Override
        public JsonElement serialize(ApiListing src, Type typeOfSrc, JsonSerializationContext context) {
            return new Gson().toJsonTree(src, typeOfSrc);
        }
    }

    static class SwaggerResourceSerializer implements JsonSerializer<SwaggerResource> {
        public final static SwaggerResourceSerializer INSTANCE = new SwaggerResourceSerializer();

        @Override
        public JsonElement serialize(SwaggerResource src, Type typeOfSrc, JsonSerializationContext context) {
            return new Gson().toJsonTree(src, typeOfSrc);
        }
    }

    static class SwaggerResourceListingJsonSerializer implements JsonSerializer<ResourceListing> {
        public final static SwaggerResourceListingJsonSerializer INSTANCE = new SwaggerResourceListingJsonSerializer();

        @Override
        public JsonElement serialize(ResourceListing src, Type typeOfSrc, JsonSerializationContext context) {
            return new Gson().toJsonTree(src, typeOfSrc);
        }
    }

    static class SwaggerSecurityConfigurationSerializer implements JsonSerializer<SecurityConfiguration> {
        public final static SwaggerSecurityConfigurationSerializer INSTANCE = new SwaggerSecurityConfigurationSerializer();

        @Override
        public JsonElement serialize(SecurityConfiguration src, Type typeOfSrc, JsonSerializationContext context) {
            return new Gson().toJsonTree(src, typeOfSrc);
        }
    }

    static class SwaggerUiConfigurationSerializer implements JsonSerializer<UiConfiguration> {
        public final static SwaggerUiConfigurationSerializer INSTANCE = new SwaggerUiConfigurationSerializer();

        @Override
        public JsonElement serialize(UiConfiguration src, Type typeOfSrc, JsonSerializationContext context) {
            return new Gson().toJsonTree(src, typeOfSrc);
        }
    }

}

