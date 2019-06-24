package com.lcsaber;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: LiChao
 * @Date: 2019/6/21 14:30
 */
@SpringBootApplication
@MapperScan("com.lcsaber.mapper")
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
