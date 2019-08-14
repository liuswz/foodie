package com.lanke.foodie.controller;

import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.AdvertisementService;
import com.lanke.foodie.service.DishService;
import com.lanke.foodie.userdto.DishDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class DishController {

    @Autowired
    private DishService dishService;

  //  @Cacheable(value="advertisement", key="'ads_'+#city")
    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public BaseJson  findAllDishType(@PathVariable("shopId") Integer shopId){
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(dishService.findAllDishType(shopId));
        return  baseJson;
    }

    @RequestMapping(value = "/shopdishes/getHotDish/{shopId}",method = RequestMethod.GET)
    public BaseJson getHotDish(@PathVariable("shopId") Integer shopId){
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(dishService.getHotDish(shopId));
        return  baseJson;
    }
    @RequestMapping(value = "/shopdishes/getDishByTypeId/{shopId}/{typeId}",method = RequestMethod.GET)
    public BaseJson getDishByTypeId(@PathVariable("shopId") Integer shopId,@PathVariable("typeId") Integer typeId){
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(dishService.getDishByTypeId(shopId,typeId));
        return  baseJson;
    }
}
