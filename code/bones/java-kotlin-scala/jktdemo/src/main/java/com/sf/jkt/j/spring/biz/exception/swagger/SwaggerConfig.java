package com.sf.jkt.j.spring.biz.exception.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger uri:
 *  http://localhost:8081/swagger-ui.html
 */

@Configuration
//@Profile({"dev","test"})
 @ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
@EnableSwagger2
public class SwaggerConfig {
   @Value("${swagger.enable}")
    private boolean enable;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.sf.jkt.j.spring.biz.exception.bad"))
                .paths(PathSelectors.any()).build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("使用 swagger2 构建短视频后端api 接口文档")
                .contact(new Contact("songfei","www.hupu.com","songfei@hupu.com"))
                .description("欢迎访问 jktdemo 接口文档")
                .version("1.0").build();
    }
}
