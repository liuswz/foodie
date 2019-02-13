package com.lanke.foodie.controller;

import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.json.BaseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RestController
public class DishesController {


    private static final String REST_URL_PREFIX = "http://FOODIE-SHOPDISHES";

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/consumer/shopdishes/adddishtype",method = RequestMethod.POST)
    public  BaseJson regist(DishType dishType ){
          log.info("测试{}，日志级别{}，输出{}", "demo1aaaaaaaaaaaaaaaaaaaaaa", dishType.getName(), "info level log");
        BaseJson baseJson = new BaseJson();
        // log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
        // int flag =detailService.regist(registDto);
        dishType.setShopId(1);
        int flag = restTemplate.postForObject(REST_URL_PREFIX + "/shopdishes/adddishtype", dishType, Integer.class);
        if(flag == 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("菜品类别已存在");
        }else {
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("用户名已存在");
        }
        return baseJson;
    }
}
