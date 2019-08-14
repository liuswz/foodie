package com.lanke.foodie.controller;

import com.lanke.foodie.entity.Advertisement;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.AdvertisementService;
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
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @Cacheable(value="advertisement", key="'ads_'+#city")
    @RequestMapping(value = "/advertisement/getAdvertisementByCity/{city}",method = RequestMethod.GET)
    public BaseJson getAdvertisementByCity(@PathVariable("city") String city){
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(advertisementService.getAdvertisementByCity(city));
        return  baseJson;
    }
}
