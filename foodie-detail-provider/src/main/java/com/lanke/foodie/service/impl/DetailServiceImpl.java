package com.lanke.foodie.service.impl;


import com.lanke.foodie.dao.DetailDao;

import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.service.DetailService;

import com.lanke.foodie.utils.BaseUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailDao detailDao;

    public int regist(Shop shop) {

        //获取当前时间
        shop.setCreateTime(BaseUtils.getTime());

     //   registDto.setCreate_time(dateStr);

        //注册时商家设为未审核状态
        shop.setShopStatus(0);
        shop.setPassword(DigestUtils.md5Hex(shop.getPassword()));
        //判断用户名是否存在
        int checkUsername = detailDao.checkUsername(shop.getUsername());
        if(checkUsername > 0){
            return 3;
        }

        //判断店名是否存在
        int checkShopName = detailDao.checkShopName(shop.getShopName());
        if(checkShopName > 0){
            return 4;
        }
        //初始化status为0
        shop.setShopStatus(0);
     //   int flag2 = detailDao.addPay(shop);
        int flag = detailDao.addShop(shop);
    //    int flag = flag1 + flag2;
        return flag;
    }

    public int update(Shop shop) {

        //校验店名是否存在
        List<Integer> ids = detailDao.checkShopNameForUpdate(shop.getShopName());
        if(ids.size() > 0 && ids.get(0) != shop.getId()){
            return 4;
        }

        int flag = detailDao.updateShop(shop);
       // int flag2 = detailDao.updatePay(shop);
      //  int flag = flag1 + flag2;
        return flag;
    }

    public Shop getById(Integer id) {
       // Shop shopInfoDto = new Shop();
        Shop shop = detailDao.getShopById(id);
      //  BeanUtils.copyProperties(shop,shopInfoDto);
     //   PayDetail payDetail = detailDao.getShopDetailById(shop.getPayDetailId());
      //  shopInfoDto.setMchId(payDetail.getMchId());
     //   shopInfoDto.setApiKey(payDetail.getApiKey());
        return shop;
    }
}
