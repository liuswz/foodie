package com.lanke.foodie.service;

import com.lanke.foodie.dto.DishesDto;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Shop;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "FOODIE-DETAIL")
public interface DetailService {



    @RequestMapping(value = "/shopdetail/getById/{id}",method = RequestMethod.GET)
    public Shop getById(@PathVariable("id") Integer id);
    @RequestMapping(value = "/shopdetail/findAllShop",method = RequestMethod.GET)
    public PageResult findAllShop(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value);
    @RequestMapping(value = "/shopdetail/updateStatus/{id}",method = RequestMethod.GET)
    public Integer updateStatus(@PathVariable("id") Integer id);

    @RequestMapping(value = "/shopdetail/deleteShop/{id}",method = RequestMethod.GET)
    public Integer deleteShop(@PathVariable("id") Integer id);

    @RequestMapping(value = "/shopdetail/findPayPhoto/{id}",method = RequestMethod.GET)
    public String findPayPhoto(@PathVariable("id") Integer id);

}
