package com.lanke.sms.service;

import com.lanke.foodie.entity.MoneyOff;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.enums.ShopType;
import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.utils.ESUtils;
import com.lanke.sms.dao.DishesDao;
import com.lanke.sms.repository.ShopRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

public class MyCronJob extends QuartzJobBean {

    @Autowired
    private DishesDao dishesDao;
    @Autowired
    private ShopRepository shopRepository;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

       List<SearchShop> searchShops = dishesDao.findAllShopDetails();
       for(SearchShop shop:searchShops){
           shop.setId(ESUtils.getShopId(shop.getShopId()));
           shop.setLocation(new GeoPoint(shop.getLatitude(),shop.getLongitude()));
           shop.setTypeId(ShopType.Shop.getIndex());
       }
      // System.out.println(searchShops);
       if(searchShops!=null&&searchShops.size()!=0){
           shopRepository.deleteAll(searchShops);
           shopRepository.saveAll(searchShops);
       }



    }
}
