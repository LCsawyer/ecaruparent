package com.lcsaber;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiChao
 * @Date: 2019/5/24 16:17
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableDiscoveryClient
@MapperScan("com.lcsaber.mapper")
public class ServiceZuulApp {
    public static void main(String[] args){
        SpringApplication.run(ServiceZuulApp.class,args);
    }

    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider{
        @Override
        public List<SwaggerResource> get() {
            List resource = new ArrayList();
            resource.add(swaggerResource("article","/article/v2/api-docs","1.0"));
            resource.add(swaggerResource("login","/v2/api-docs","1.0"));
            resource.add(swaggerResource("user","/user/v2/api-docs","1.0"));
            return resource;
        }
        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }

    }

}
