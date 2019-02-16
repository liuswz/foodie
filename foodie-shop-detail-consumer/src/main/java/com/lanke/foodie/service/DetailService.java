package com.lanke.foodie.service;

import com.lanke.foodie.dto.DishesDto;

import com.lanke.foodie.entity.Shop;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "FOODIE-SHOPDETAIL")
public interface DetailService {


    @RequestMapping(value = "/shopdetail/register",method = RequestMethod.POST)
    public Integer regist(@RequestBody Shop shop  );

    @RequestMapping(value = "/shopdetail/update",method = RequestMethod.POST)
    public Integer update(@RequestBody Shop shop);

    @RequestMapping(value = "/shopdetail/getById",method = RequestMethod.GET)
    public Shop getById(@RequestParam("id") Integer id);


}
