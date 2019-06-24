package com.lcsaber.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcsaber.pojo.ArticerReviewInfo;
import com.lcsaber.pojo.UserReviewInfo;

/**
 * @Author: LiChao
 * @Date: 2019/6/22 9:07
 */
public class JsonUtil {
    public static UserReviewInfo transforUReview(String msg){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
        try{
            UserReviewInfo userReviewInfo = (UserReviewInfo) mapper.readValue(msg,UserReviewInfo.class);
            return userReviewInfo;
        }
        catch (Exception e){}
        return null;
    }

    public static ArticerReviewInfo transforAReview(String msg){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
        try{
            ArticerReviewInfo articerReviewInfo = (ArticerReviewInfo) mapper.readValue(msg,ArticerReviewInfo.class);
            return articerReviewInfo;
        }
        catch (Exception e){}
        return null;
    }
}
