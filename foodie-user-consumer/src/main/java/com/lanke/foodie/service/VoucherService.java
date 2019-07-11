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
public interface VoucherService {


    @RequestMapping(value = "/product/getVoucherByCity/{page}/{size}/{city}",method = RequestMethod.GET)
    public PageResult getVoucherByCity(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("city") String city);

    @RequestMapping(value = "/product/getVoucherById/{shopId}",method = RequestMethod.GET)
    public Voucher getVoucherById(@PathVariable("shopId") Integer shopId);
}
