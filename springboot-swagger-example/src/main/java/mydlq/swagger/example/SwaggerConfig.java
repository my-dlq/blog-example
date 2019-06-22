package mydlq.swagger.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.groupName("swagger-example-service") // 项目组名
                .select()                               // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any())    // 对所有api进行监控
                .paths(PathSelectors.any())             // 对所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-example-service")           // 文档标题
                .description("This is a swagger project.")  // 文档描述
                .version("1.0.0")                           // 文档版本
                .build();
    }

}