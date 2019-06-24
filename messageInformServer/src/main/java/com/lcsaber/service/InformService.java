package com.lcsaber.service;

import com.lcsaber.pojo.InformPageInfo;
import com.lcsaber.pojo.UserInformInfo;

/**
 * @Author: LiChao
 * @Date: 2019/6/21 15:18
 */
public interface InformService {

    int insertInform(UserInformInfo userInformInfo);

    InformPageInfo selByPage(Integer pageSize,Integer pageNum);

    int delById(Long id);
}
