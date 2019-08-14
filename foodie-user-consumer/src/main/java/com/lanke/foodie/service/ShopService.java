package com.lanke.foodie.service;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.simpleEntity.EasyShopDetail;
import com.lanke.foodie.userdto.EasyShopDto;
import com.lanke.foodie.userdto.EntireShopDetail;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userparams.ShopInSiteAndTypeParam;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "FOODIE-DETAIL")
public interface ShopService {

    @RequestMapping(value = "/usershopdetail/findAllShopDetails",method = RequestMethod.GET)
    public PageResults findAllShopDetails(@RequestBody  ShopInSiteAndTypeParam param);
    @RequestMapping(value = "/usershopdetail/findAllShopDetailsByMark",method = RequestMethod.GET)
    public PageResults findAllShopDetailsByMark(@RequestBody ShopInSiteAndTypeParam param);
    @RequestMapping(value = "/usershopdetail/findAllShopDetailsByTypeId",method = RequestMethod.GET)
    public PageResults findAllShopDetailsByTypeId(@RequestBody ShopInSiteAndTypeParam param);
    @RequestMapping(value = "/usershopdetail/findAllShopDetailsByMarkAndTypeId",method = RequestMethod.GET)
    public PageResults findAllShopDetailsByMarkAndTypeId(@RequestBody ShopInSiteAndTypeParam param);

    @RequestMapping(value = "/usershopdetail/getEntireShopDetail/{shopId}",method = RequestMethod.GET)
    public EntireShopDetail getEntireShopDetail(@PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/usershopdetail/getEasyShopDetail/{shopId}",method = RequestMethod.GET)
    public EasyShopDetail getEasyShopDetail(@PathVariable("shopId") Integer shopId);

    @RequestMapping(value = "/usershopdetail/getEasyShop/{shopId}",method = RequestMethod.GET)
    public EasyShopDto getEasyShop(@PathVariable("shopId") Integer shopId);

    @RequestMapping(value = "/usershopdetail/getShopPhoneById/{shopId}",method = RequestMethod.GET)
    public String getShopPhoneById(@PathVariable("shopId") Integer shopId);
}
