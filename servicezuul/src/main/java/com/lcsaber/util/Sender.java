package com.lcsaber.util;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: LiChao
 * @Date: 2019/6/19 20:07
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitAmqpTemplate;

    public void send(String msg){
        this.rabbitAmqpTemplate.convertAndSend("log-queue",msg);
    }
}
