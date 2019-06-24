package com.lcsaber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcsaber.pojo.ArticerReviewInfo;
import com.lcsaber.pojo.UserInformInfo;
import com.lcsaber.pojo.UserReviewInfo;
import com.lcsaber.service.InformService;
import com.lcsaber.util.JsonUtil;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: LiChao
 * @Date: 2019/6/22 8:54
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value="articleReview-queue",autoDelete = "false"),
                exchange = @Exchange(value = "inform-exchange",type = ExchangeTypes.DIRECT),
                key = "articleReview-queue"
        )
)
public class ArticleReviewReceiver{

    @Autowired
    private InformService informService;

    @RabbitHandler
    public void process(String msg){
        ArticerReviewInfo articerReviewInfo = JsonUtil.transforAReview(msg);
        UserInformInfo userInformInfo = new UserInformInfo();
        userInformInfo.setUserId(articerReviewInfo.getAuthorId());
        userInformInfo.setInformDescription("内容审核完成通知");
        userInformInfo.setInformLevel("通知");
        userInformInfo.setInformContent(articerReviewInfo.getDescription());
        userInformInfo.setInformTime(articerReviewInfo.getReviewTime());
        if (articerReviewInfo!=null){
            try {
                ObjectMapper mapper = new ObjectMapper();
                String a = mapper.writeValueAsString(userInformInfo);
                WebSocketServer.sendInfoSingle(a,""+articerReviewInfo.getAuthorId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            informService.insertInform(userInformInfo);
        }
    }
}
