package com.lanke.foodie.service;

import com.lanke.foodie.dto.DishesDto;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Shop;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "FOODIE-SHOPDETAIL")
public interface DetailService {



    @RequestMapping(value = "/shopdetail/getById",method = RequestMethod.GET)
    public Shop getById(@RequestParam("id") Integer id);
    @RequestMapping(value = "/shopdetail/findAllShop",method = RequestMethod.GET)
    public PageResult findAllShop(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value);
    @RequestMapping(value = "/shopdetail/updateStatus/{id}",method = RequestMethod.GET)
    public Integer updateStatus(Integer id);

    @RequestMapping(value = "/shopdetail/deleteShop/{id}",method = RequestMethod.GET)
    public Integer deleteShop(Integer id);

    @RequestMapping(value = "/shopdetail/findPayPhoto/{id}",method = RequestMethod.GET)
    public String findPayPhoto(Integer id);

}
