package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

/**
 * Swagger 配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //扫描的包
    private final String BASE_PACKAGE = "com.example.demo.controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // ⾃⾏修改为⾃⼰的包路径
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("客户管理")
                .description("客户管理 操作⽂档")
                //服务条款⽹址
                .termsOfServiceUrl("www.cnblogs.com/duanxinBlog")
                .version("1.0")
                .contact(new Contact("段鑫", "www.cnblogs.com/duanxinBlog", "asdfgh@126.com"))
                .build();
    }
}
