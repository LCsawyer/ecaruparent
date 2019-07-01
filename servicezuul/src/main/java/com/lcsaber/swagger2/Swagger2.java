package com.lcsaber.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: LiChao
 * @Date: 2019/6/25 16:05
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().
                apis(RequestHandlerSelectors.basePackage("com.lcsaber.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("登录接口")
                .description("用户名、邮箱、手机登录，返回token，每次通过网关访问均设置header: Authorization=token")
                .termsOfServiceUrl("http://223.3.65.243:9095").version("1.0").build();
    }
}
