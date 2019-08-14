package com.lanke.foodie.service;

import com.lanke.foodie.entity.ShopCollect;
import com.lanke.foodie.userdto.PageResults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(value = "FOODIE-DETAIL")
public interface CollectionService {

    @RequestMapping(value = "/usershopdetail/getShopCollect/{page}/{size}/{userId}/{lat}/{lon}",method = RequestMethod.GET)
    public PageResults getShopCollect(@PathVariable("page") Integer page, @PathVariable("size")  Integer size, @PathVariable("userId") Integer userId,@PathVariable("lat") Double lat,@PathVariable("lon") Double lon);
    @RequestMapping(value = "/usershopdetail/checkShopCollect/{userId}/{shopId}",method = RequestMethod.GET)
    public Integer checkShopCollect(@PathVariable("userId") Integer userId,@PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/usershopdetail/deleteShopCollect/{userId}/{shopId}",method = RequestMethod.GET)
    public Integer deleteShopCollect(@PathVariable("userId") Integer userId,@PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/usershopdetail/addShopCollect",method = RequestMethod.POST)
    public Integer addShopCollect(@RequestBody ShopCollect collect);
}
