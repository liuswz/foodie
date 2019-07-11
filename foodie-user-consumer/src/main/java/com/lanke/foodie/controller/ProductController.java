package com.lanke.foodie.controller;


import com.lanke.foodie.dto.PageResult;

import com.lanke.foodie.entity.Voucher;
import com.lanke.foodie.json.BaseJson;

import com.lanke.foodie.service.ProductService;
import com.lanke.foodie.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class ProductController {
   // private static final String REST_URL_PREFIX = "http://FOODIE-SHOPDISHES";
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private ProductService productService;



    //商品
    @RequestMapping(value = "/product/findAllProductWithMoneyOff",method = RequestMethod.GET)
    public PageResult findAllProductWithMoneyOff(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(value="value",defaultValue="") String value) {

        return productService.findAllProductWithMoneyOff(page,size,value);
    }
    @RequestMapping(value = "/product/findAllProductWithMoneyOffByType",method = RequestMethod.GET)
    public PageResult findAllProductWithMoneyOffByType(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("productTypeId") Integer productTypeId) {
        return productService.findAllProductWithMoneyOffByType(page,size,productTypeId);
    }


}
