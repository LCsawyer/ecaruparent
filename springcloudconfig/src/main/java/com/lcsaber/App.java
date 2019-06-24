package com.lcsaber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LiChao
 * @Date: 2019/5/28 19:47
 */
@SpringBootApplication
@EnableConfigServer
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }

}
