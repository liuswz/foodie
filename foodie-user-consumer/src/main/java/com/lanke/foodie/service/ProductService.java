package com.lanke.foodie.service;


import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userdto.ProductUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-DISHES")
public interface ProductService {

    //商品getVoucherByCity
    @RequestMapping(value = "/product/findAllProductWithMoneyOff",method = RequestMethod.GET)
    public PageResults findAllProductWithMoneyOff(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value);
    @RequestMapping(value = "/product/findAllProductWithMoneyOffByType",method = RequestMethod.GET)
    public PageResults findAllProductWithMoneyOffByType(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("productType") String productType);
    @RequestMapping(value = "/product/findAllProductType",method = RequestMethod.GET)
    public List<ProductType> findAllProductType();
    @RequestMapping(value = "/product/getProductNamesByValue/{value}",method = RequestMethod.GET)
    public List<String> getProductNamesByValue(@PathVariable("value") String value);

    @RequestMapping(value = "/product/getProductDetailForUser/{id}",method = RequestMethod.GET)
    public ProductUserDto getProductDetailForUser(@PathVariable("id") Integer id);
}
