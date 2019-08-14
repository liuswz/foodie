package com.lanke.foodie.controller;



import com.lanke.foodie.dto.ShopIdAndCityDto;
import com.lanke.foodie.dto.ShopNameAndIdDto;
import com.lanke.foodie.dto.ShopSearchPropertyDto;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopCollect;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.entity.ShopType;
import com.lanke.foodie.service.DetailService;
import com.lanke.foodie.service.UserDetailService;
import com.lanke.foodie.simpleEntity.EasyShopDetail;
import com.lanke.foodie.userdto.EasyShopDto;
import com.lanke.foodie.userdto.EntireShopDetail;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userdto.ShopDetailDto;
import com.lanke.foodie.userparams.ShopInSiteAndTypeParam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserDetailController {


    @Autowired
    private UserDetailService userDetailService;


    @RequestMapping(value = "/usershopdetail/findAllShopDetails",method = RequestMethod.GET)
    public PageResults findAllShopDetails(@RequestBody ShopInSiteAndTypeParam param){
        return userDetailService.findAllShopDetails(param);
    }
    @RequestMapping(value = "/usershopdetail/findAllShopDetailsByMark",method = RequestMethod.GET)
    public PageResults findAllShopDetailsByMark(@RequestBody  ShopInSiteAndTypeParam param){
        return userDetailService.findAllShopDetailsByMark(param);
    }
    @RequestMapping(value = "/usershopdetail/findAllShopDetailsByTypeId",method = RequestMethod.GET)
    public PageResults findAllShopDetailsByTypeId(@RequestBody ShopInSiteAndTypeParam param){
        return userDetailService.findAllShopDetailsByTypeId(param);
    }
    @RequestMapping(value = "/usershopdetail/findAllShopDetailsByMarkAndTypeId",method = RequestMethod.GET)
    public PageResults findAllShopDetailsByMarkAndTypeId(@RequestBody ShopInSiteAndTypeParam param){
        return userDetailService.findAllShopDetailsByMarkAndTypeId(param);
    }
    @RequestMapping(value = "/usershopdetail/getEntireShopDetail/{shopId}",method = RequestMethod.GET)
    public EntireShopDetail getEntireShopDetail(@PathVariable("shopId") Integer shopId){
        return userDetailService.getEntireShopDetail(shopId);
    }

    @RequestMapping(value = "/usershopdetail/getEasyShopDetail/{shopId}",method = RequestMethod.GET)
    public EasyShopDetail getEasyShopDetail(@PathVariable("shopId") Integer shopId) {
        return userDetailService.getEasyShopDetail(shopId);
    }
    @RequestMapping(value = "/usershopdetail/getEasyShop/{shopId}",method = RequestMethod.GET)
    public EasyShopDto getEasyShop(@PathVariable("shopId") Integer shopId) {
        return userDetailService.getEasyShop(shopId);
    }
    @RequestMapping(value = "/usershopdetail/getShopCollect/{page}/{size}/{userId}/{lat}/{lon}",method = RequestMethod.GET)
    public PageResults getShopCollect(@PathVariable("page") Integer page,@PathVariable("size")  Integer size,@PathVariable("userId") Integer userId,@PathVariable("lat") Double lat,@PathVariable("lon") Double lon){
        return userDetailService.getShopCollect(page,size,userId,lat,lon);
    }
    @RequestMapping(value = "/usershopdetail/checkShopCollect/{userId}/{shopId}",method = RequestMethod.GET)
    public Integer checkShopCollect(@PathVariable("userId") Integer userId,@PathVariable("shopId") Integer shopId){

        return userDetailService.checkShopCollect(userId,shopId);
    }
    @RequestMapping(value = "/usershopdetail/deleteShopCollect/{userId}/{shopId}",method = RequestMethod.GET)
    public Integer deleteShopCollect(@PathVariable("userId") Integer userId,@PathVariable("shopId") Integer shopId){
        return userDetailService.deleteShopCollect(userId,shopId);
    }
    @RequestMapping(value = "/usershopdetail/addShopCollect",method = RequestMethod.POST)
    public Integer addShopCollect(@RequestBody ShopCollect collect){
        return userDetailService.addShopCollect(collect);
    }
    @RequestMapping(value = "/usershopdetail/getShopPhoneById/{shopId}",method = RequestMethod.GET)
    public String getShopPhoneById(@PathVariable("shopId") Integer shopId) {
        return userDetailService.getShopPhoneById(shopId);
    }
}
