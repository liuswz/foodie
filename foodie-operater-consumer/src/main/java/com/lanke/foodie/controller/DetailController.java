package com.lanke.foodie.controller;

import com.fasterxml.jackson.databind.ser.Serializers;

import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.json.BaseJson;

import com.lanke.foodie.service.DetailService;
import com.lanke.foodie.service.OrderService;
import com.lanke.foodie.view.TransferView;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class DetailController {

    @Autowired
    private DetailService detailService;

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/consumer/shopdetail/getById/{id}",method = RequestMethod.GET)
    public Shop getById(@PathVariable("id") Integer id){
        BaseJson baseJson = new BaseJson();
        Shop shop = detailService.getById(id);

        return shop;

    }

    @RequestMapping(value = "/consumer/shopdetail/findAllShop",method = RequestMethod.GET)
    public PageResult findAllShop(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value){
        return detailService.findAllShop(page,size,value);
    }





    @RequestMapping(value = "/consumer/shopdetail/updateStatus/{id}",method = RequestMethod.GET)
    public BaseJson updateStatus(@PathVariable("id") Integer id){

        int flag = detailService.updateStatus(id);
        BaseJson baseJson = new BaseJson();
        if(flag >0){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");
        }else {

            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("更改失败");


        }
        return baseJson;

    }
    @RequestMapping(value = "/consumer/shopdetail/deleteShop/{id}",method = RequestMethod.GET)
    public BaseJson deleteShop(@PathVariable("id") Integer id){

        int flag = detailService.deleteShop(id);
        BaseJson baseJson = new BaseJson();
        if(flag >0){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("删除成功");
        }else {

            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("删除失败");


        }
        return baseJson;

    }

    @RequestMapping(value = "/consumer/shopdetail/findPayPhoto/{id}",method = RequestMethod.GET)
    public BaseJson findPayPhoto(@PathVariable("id") Integer id){
        String  payPhoto = detailService.findPayPhoto(id);
        BaseJson baseJson = new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(payPhoto);
        return baseJson;

    }


}
