package com.lcsaber;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

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
}
