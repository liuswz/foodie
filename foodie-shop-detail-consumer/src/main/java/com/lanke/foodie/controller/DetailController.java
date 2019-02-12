package com.lanke.foodie.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.dto.ShopUpdateDto;
import com.lanke.foodie.json.BaseJson;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class DetailController {


    private static final String REST_URL_PREFIX = "http://FOODIE-SHOPDETAIL";

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "商家入驻", notes = "商家管理")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", value = "用户名", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", value = "密码", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "shopName", dataType = "String", value = "店名", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "shopAddress", dataType = "String", value = "店家地址", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "shopEmail", dataType = "String", value = "店家邮箱", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "shopPhone", dataType = "String", value = "店家电话", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "mchId", dataType = "String", value = "支付id", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "apiKey", dataType = "String", value = "支付key", defaultValue = "")
    })
    @RequestMapping(value = "/consumer/shopdetail/register",method = RequestMethod.POST)
    public BaseJson regist(RegistDto registDto){
        //  log.info("测试{}，日志级别{}，输出{}", "demo1aaaaaaaaaaaaaaaaaaaaaa", "info", "info level log");
        BaseJson baseJson = new BaseJson();
        // log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
        // int flag =detailService.regist(registDto);

        int flag = restTemplate.postForObject(REST_URL_PREFIX + "/shopdetail/register", registDto, Integer.class);
        if(flag == 2){
            baseJson.setCode(0);
            baseJson.setResult("注册成功");
        }else if(flag == 3){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("用户名已存在");
        }else if(flag == 4){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("店名已存在");
        }else {
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;
    }

    @RequestMapping(value = "/consumer/shopdetail/update",method = RequestMethod.GET)
    public BaseJson update(ShopUpdateDto shopUpdateDto){
        BaseJson baseJson = new BaseJson();
        int flag = restTemplate.getForObject(REST_URL_PREFIX + "/shopdetail/update",Integer.class,shopUpdateDto);
        if(flag == 2){
            baseJson.setCode(0);
            baseJson.setResult("修改成功");
        }else if(flag == 4){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("店名已存在");
        }else {
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;
    }

}
