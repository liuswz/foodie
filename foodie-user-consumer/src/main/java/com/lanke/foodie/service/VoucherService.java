package com.lanke.foodie.service;


import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userdto.VoucherForUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-DISHES")
public interface VoucherService {


    @RequestMapping(value = "/product/getVoucherByCity/{page}/{size}/{city}",method = RequestMethod.GET)
    public PageResults getVoucherByCity(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("city") String city);
    @RequestMapping(value = "/product/getVoucherForUserByUserId/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults getVoucherForUserByUserId(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("userId") Integer userId);
    @RequestMapping(value = "/product/getVoucherById/{shopId}",method = RequestMethod.GET)
    public Voucher getVoucherById(@PathVariable("shopId") Integer shopId);

    @RequestMapping(value = "/product/addShopVoucherForUser",method = RequestMethod.POST)
    public Integer addShopVoucherForUser(@RequestBody VoucherForUser voucher);

    @RequestMapping(value = "/product/checkVoucherForUser/{shopId}/{userId}/{voucherId}",method = RequestMethod.GET)
    public Integer checkVoucherForUser(@PathVariable("shopId") Integer shopId,@PathVariable("userId") Integer userId,@PathVariable("voucherId") Integer voucherId);

    @RequestMapping(value = "/product/getVoucherForUserById/{shopId}/{userId}",method = RequestMethod.GET)
    public List<VoucherForUserDto> getVoucherForUserById(@PathVariable("shopId")  Integer shopId, @PathVariable("userId")  Integer userId);
    @RequestMapping(value = "/product/delVoucherForUserById/{id}",method = RequestMethod.GET)
    public Integer delVoucherForUserById(@PathVariable("id")  Integer id);

}
