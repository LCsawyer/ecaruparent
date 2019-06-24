package com.lcsaber.service.impl;

import com.lcsaber.mapper.InformMapper;
import com.lcsaber.pojo.InformPageInfo;
import com.lcsaber.pojo.UserInformInfo;
import com.lcsaber.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: LiChao
 * @Date: 2019/6/22 21:12
 */
@Service("informService")
@Transactional
public class InformServiceImpl implements InformService {

    @Autowired
    private InformMapper informMapper;

    @Override
    public int insertInform(UserInformInfo userInformInfo) {
        return informMapper.insInform(userInformInfo);
    }

    @Override
    public InformPageInfo selByPage(Integer pageSize, Integer pageNum) {
        InformPageInfo pageInfo = new InformPageInfo();
        int count = informMapper.selAllCount();
        if (count<=0) return null;
        pageInfo.setTotal(count);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageStart((pageNum-1)*pageSize);
        List<UserInformInfo> list = informMapper.selUserInform((pageNum-1)*pageSize,pageSize);
        if (list==null && list.size()==0) return null;
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public int delById(Long id) {
        return informMapper.delInform(id);
    }
}
