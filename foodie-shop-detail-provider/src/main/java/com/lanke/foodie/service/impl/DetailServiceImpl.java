package com.lanke.foodie.service.impl;


import com.lanke.foodie.dao.DetailDao;
import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.dto.ShopInfoDto;
import com.lanke.foodie.dto.ShopUpdateDto;
import com.lanke.foodie.entity.PayDetail;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.service.DetailService;

import com.lanke.foodie.utils.BaseUtils;
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

    public int regist(RegistDto registDto) {

        //获取当前时间
        registDto.setCreateTime(BaseUtils.getTime());

     //   registDto.setCreate_time(dateStr);

        //注册时商家设为未审核状态
        registDto.setShopStatus(0);

        //判断用户名是否存在
        int checkUsername = detailDao.checkUsername(registDto.getUsername());
        if(checkUsername > 0){
            return 3;
        }

        //判断店名是否存在
        int checkShopName = detailDao.checkShopName(registDto.getShopName());
        if(checkShopName > 0){
            return 4;
        }

        int flag2 = detailDao.addPay(registDto);
        int flag1 = detailDao.addShop(registDto);
        int flag = flag1 + flag2;
        return flag;
    }

    public int update(ShopUpdateDto shopUpdateDto) {

        //校验店名是否存在
        List<Integer> ids = detailDao.checkShopNameForUpdate(shopUpdateDto.getShopName());
        if(ids.size() > 0 && ids.get(0) != shopUpdateDto.getId()){
            return 4;
        }

        int flag1 = detailDao.updateShop(shopUpdateDto);
        int flag2 = detailDao.updatePay(shopUpdateDto);
        int flag = flag1 + flag2;
        return flag;
    }

    public ShopInfoDto getById(Integer id) {
        ShopInfoDto shopInfoDto = new ShopInfoDto();
        Shop shop = detailDao.getShopById(id);
        BeanUtils.copyProperties(shop,shopInfoDto);
        PayDetail payDetail = detailDao.getShopDetailById(shop.getPayDetailId());
        shopInfoDto.setMchId(payDetail.getMchId());
        shopInfoDto.setApiKey(payDetail.getApiKey());
        return shopInfoDto;
    }
}
