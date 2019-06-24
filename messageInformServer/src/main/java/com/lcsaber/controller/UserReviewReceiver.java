package com.lcsaber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcsaber.pojo.UserInformInfo;
import com.lcsaber.pojo.UserReviewInfo;
import com.lcsaber.service.InformService;
import com.lcsaber.util.JsonUtil;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: LiChao
 * @Date: 2019/6/22 8:43
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "userReview-queue",autoDelete = "false"),
                exchange = @Exchange(value = "inform-exchange",type = ExchangeTypes.DIRECT),
                key = "userReview-queue"
        )
)
public class UserReviewReceiver {

    @Autowired
    private InformService informService;

    @RabbitHandler
    public void process(String msg){
        UserReviewInfo userReviewInfo = JsonUtil.transforUReview(msg);
        UserInformInfo userInformInfo = new UserInformInfo();
        userInformInfo.setUserId(userReviewInfo.getUserId());
        userInformInfo.setInformLevel("通知");
        userInformInfo.setInformDescription("用户权限审核通知");
        userInformInfo.setInformContent(userReviewInfo.getReviewDescription());
        userInformInfo.setInformTime(userReviewInfo.getReviewTime());
        if (userReviewInfo!=null) {
            try{
                ObjectMapper mapper = new ObjectMapper();
                WebSocketServer.sendInfoSingle(mapper.writeValueAsString(userInformInfo),""+userReviewInfo.getUserId());
            }catch (Exception e){}
            informService.insertInform(userInformInfo);
        }
    }
}
