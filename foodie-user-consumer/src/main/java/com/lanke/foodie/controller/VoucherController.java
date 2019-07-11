package com.lanke.foodie.controller;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Voucher;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    //代金卷

    @RequestMapping(value = "/product/getVoucherById/{shopId}",method = RequestMethod.GET)
    public Voucher getVoucherById(@PathVariable("shopId") Integer shopId) {
        return voucherService.getVoucherById(shopId);
    }
    @RequestMapping(value = "/product/getVoucherByCity/{page}/{size}/{city}",method = RequestMethod.GET)
    public PageResult getVoucherByCity(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("city") String city){
        return voucherService.getVoucherByCity(page,size,city);
    }

}
