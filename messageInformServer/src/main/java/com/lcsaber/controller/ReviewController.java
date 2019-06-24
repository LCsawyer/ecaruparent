package com.lcsaber.controller;

import com.lcsaber.pojo.InformPageInfo;
import com.lcsaber.pojo.ResponseBean;
import com.lcsaber.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LiChao
 * @Date: 2019/6/21 15:24
 */
@RestController
public class ReviewController {

    @Autowired
    private InformService informService;

    @DeleteMapping("/userInforms/{id}")
    public ResponseBean delById(@PathVariable Long id){
        int index = informService.delById(id);
        if (index <=0){
            return new ResponseBean(404,"Not Found",null);
        }
        return new ResponseBean(201,"ok",null);
    }

    @GetMapping("/userInforms")
    public ResponseBean<InformPageInfo> selPage(@RequestParam Integer pageSize,@RequestParam Integer pageNum){
        InformPageInfo pageInfo = informService.selByPage(pageSize,pageNum);
        if (pageInfo==null){
            return new ResponseBean<>(404,"Not Found",null);
        }
        return new ResponseBean<>(200,"ok",pageInfo);
    }
}
