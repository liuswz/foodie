package com.lanke.foodie.controller;

import com.lanke.foodie.entity.ShopCollect;

import com.lanke.foodie.enums.CollectionResponse;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.CollectionService;
import com.lanke.foodie.userdto.PageResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @RequestMapping(value = "/usershopdetail/getShopCollect/{page}/{size}/{userId}/{lat}/{lon}",method = RequestMethod.GET)
    public PageResults getShopCollect(@PathVariable("page") Integer page, @PathVariable("size")  Integer size, @PathVariable("userId") Integer userId,@PathVariable("lat") Double lat,@PathVariable("lon") Double lon){

        PageResults pageResults =collectionService.getShopCollect(page,size,userId,lat,lon);
        pageResults.setCode(0);
        pageResults.setMessage("成功");
        return  pageResults;

    }
    @RequestMapping(value = "/usershopdetail/checkShopCollect/{userId}/{shopId}",method = RequestMethod.GET)
    public BaseJson checkShopCollect(@PathVariable("userId") Integer userId,@PathVariable("shopId") Integer shopId){
        BaseJson baseJson =new BaseJson();
        Integer flag = collectionService.checkShopCollect(userId,shopId);
        if(flag>0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult(Result.SUCCESS.getIndex());
        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult(Result.FAIL.getIndex());
        }
        return baseJson;

    }
    @RequestMapping(value = "/usershopdetail/deleteShopCollect/{userId}/{shopId}",method = RequestMethod.GET)
    public BaseJson deleteShopCollect(@PathVariable("userId") Integer userId,@PathVariable("shopId") Integer shopId){
        BaseJson baseJson =new BaseJson();
        Integer flag = collectionService.deleteShopCollect(userId,shopId);
        if(flag>0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("删除成功");
        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("删除失败");
        }
        return baseJson;
    }
    @RequestMapping(value = "/usershopdetail/addShopCollect",method = RequestMethod.POST)
    public BaseJson addShopCollect(@RequestBody ShopCollect collect){
        BaseJson baseJson =new BaseJson();
        Integer status = collectionService.checkShopCollect(collect.getUserId(),collect.getShopId());
        if(status>0){
            Integer flag = collectionService.deleteShopCollect(collect.getUserId(),collect.getShopId());
            if(flag>0){
                baseJson.setCode(Result.SUCCESS.getIndex());
                baseJson.setMessage("成功");
                baseJson.setResult(CollectionResponse.HadDelete.getIndex());
            }else{
                baseJson.setCode(Result.FAIL.getIndex());
                baseJson.setMessage("失败");
            }
        }else{
            Integer flag = collectionService.addShopCollect(collect);
            if(flag>0){
                baseJson.setCode(Result.SUCCESS.getIndex());
                baseJson.setMessage("成功");
                baseJson.setResult(CollectionResponse.HadAdd.getIndex());
            }else{
                baseJson.setCode(Result.FAIL.getIndex());
                baseJson.setMessage("失败");

            }
        }
        return baseJson;

    }
}
