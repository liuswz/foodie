package com.lanke.foodie.service.impl;


import com.lanke.foodie.dao.DetailDao;
import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.service.DetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailDao detailDao;

    public int regist(RegistDto registDto) {

        //获取当前时间
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(now);

        registDto.setCreateTime(dateStr);

        //注册时商家设为未审核状态
        registDto.setShopStatus(0);

        int checkUsername = detailDao.checkUsername(registDto.getUsername());
        int checkShopName = detailDao.checkShopName(registDto.getShopName());

        //判断用户名是否存在
        if(checkUsername > 0){
            return 3;
        }

        //判断店名是否存在
        if(checkShopName > 0){
            return 4;
        }

        int flag2 = detailDao.addPay(registDto);
        int flag1 = detailDao.addShop(registDto);
        int flag = flag1 + flag2;
        return flag;
    }
}
