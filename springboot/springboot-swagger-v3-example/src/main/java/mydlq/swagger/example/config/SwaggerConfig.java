package mydlq.swagger.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author mydlq
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Value("${springfox.documentation.swagger-ui.enabled}")
    private boolean enabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(
                // 设置使用 OpenApi 3.0 规范
                DocumentationType.OAS_30)
                // 是否开启 Swagger
                .enable(enabled)
                // 配置项目基本信息
                .apiInfo(apiInfo())
                // 设置项目组名
                //.groupName("xxx组")
                // 选择那些路径和api会生成document
                .select()
                // 对所有api进行监控
                .apis(RequestHandlerSelectors.any())
                // 如果需要指定对某个包的接口进行监控，则可以配置如下
                //.apis(RequestHandlerSelectors.basePackage("mydlq.swagger.example.controller"))
                // 对所有路径进行监控
                .paths(PathSelectors.any())
                // 忽略以"/error"开头的路径,可以防止显示如404错误接口
                .paths(PathSelectors.regex("/error.*").negate())
                // 忽略以"/actuator"开头的路径
                .paths(PathSelectors.regex("/actuator.*").negate())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("用户服务接口")
                // 文档描述
                .description("用户相关操作接口")
                // 文档版本
                .version("0.0.1")
                // 设置许可声明信息
                .license("Apache LICENSE 2.0")
                // 设置许可证URL地址
                .licenseUrl("https://github/my-dlq")
                // 设置管理该API人员的联系信息
                .contact(new Contact("超级小豆丁", "http://www.mydlq.club", "mynamedlq@163.com"))
                .build();
    }

}