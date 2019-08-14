package com.lanke.foodie.controller;


import com.lanke.foodie.dto.PageResult;

import com.lanke.foodie.entity.Product;
import com.lanke.foodie.entity.ProductType;
import com.lanke.foodie.entity.Voucher;
import com.lanke.foodie.json.BaseJson;

import com.lanke.foodie.service.ProductService;
import com.lanke.foodie.service.VoucherService;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userdto.ProductUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private RedisTemplate redisTemplate;

    //商品
    @RequestMapping(value = "/product/findAllProductWithMoneyOff",method = RequestMethod.GET)
    public PageResults findAllProductWithMoneyOff(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("value") String value) {
        if(!redisTemplate.hasKey("productSearchName")){
            redisTemplate.expire("productSearchName",1, TimeUnit.DAYS);
        }
        redisTemplate.boundZSetOps("productSearchName").incrementScore(value,1L);
        PageResults pageResults = productService.findAllProductWithMoneyOff(page,size,value);
        pageResults.setCode(0);
        pageResults.setMessage("成功");
        return  pageResults;
    }
    @RequestMapping(value = "/product/findAllProductWithMoneyOffByType",method = RequestMethod.GET)
    public PageResults findAllProductWithMoneyOffByType(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("productType") String productType) {
        PageResults pageResults = productService.findAllProductWithMoneyOffByType(page,size,productType);
        pageResults.setCode(0);
        pageResults.setMessage("成功");
        return  pageResults;
    }

    @RequestMapping(value = "/product/findAllProductType",method = RequestMethod.GET)
    public BaseJson findAllProductType(){
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(productService.findAllProductType());
        return  baseJson;
    }


    @RequestMapping(value = "/product/addProductSearchName/{searchName}",method = RequestMethod.GET)
    public void addProductSearchName(@PathVariable("searchName") String searchName){

        redisTemplate.boundZSetOps("productSearchName").incrementScore(searchName,1L);

    }
    @RequestMapping(value = "/product/getHotProductSearchName",method = RequestMethod.GET)
    public BaseJson getHotProductSearchName(){

      
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(redisTemplate.boundZSetOps("productSearchName").reverseRange(0,10));
        return  baseJson;

    }

    @RequestMapping(value = "/product/getProductNamesByValue/{value}",method = RequestMethod.GET)
    public PageResults getProductNamesByValue(@PathVariable("value") String value) {
        PageResults pageResults = new PageResults();
        pageResults.setRows(productService.getProductNamesByValue(value));
        pageResults.setCode(0);
        pageResults.setMessage("成功");
        return  pageResults;
    }


    @RequestMapping(value = "/product/getProductDetailForUser/{id}",method = RequestMethod.GET)
    public BaseJson getProductDetailForUser(@PathVariable("id") Integer id) {
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(productService.getProductDetailForUser(id));
        return  baseJson;
    }


}
