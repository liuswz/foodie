package com.lanke.foodie.service.impl;


import com.lanke.foodie.dao.DetailDao;
import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    DetailDao detailDao;

    public int regist(RegistDto registDto) {

        //获取当前时间
        Date now = new Date();
        registDto.setCreateTime(now);

        int flag1 = detailDao.addShop(registDto);
        int flag2 = detailDao.addPay(registDto);
        int flag = flag1 + flag2;
        return flag;
    }
}
