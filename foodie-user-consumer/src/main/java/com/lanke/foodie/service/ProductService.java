package com.lanke.foodie.service;


import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.entity.MoneyOff;
import com.lanke.foodie.entity.Voucher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-DISHES")
public interface ProductService {

    //商品
    @RequestMapping(value = "/product/findAllProductWithMoneyOff",method = RequestMethod.GET)
    public PageResult findAllProductWithMoneyOff(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value);
    @RequestMapping(value = "/product/findAllProductWithMoneyOffByType",method = RequestMethod.GET)
    public PageResult findAllProductWithMoneyOffByType(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("productTypeId") Integer productTypeId);


}
