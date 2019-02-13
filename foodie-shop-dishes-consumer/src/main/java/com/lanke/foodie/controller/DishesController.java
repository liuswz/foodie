package com.lanke.foodie.controller;

import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class DishesController {


    private static final String REST_URL_PREFIX = "http://FOODIE-SHOPDISHES";

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/consumer/shopdishes/adddishtype",method = RequestMethod.POST)
    public  BaseJson adddishtype(DishType dishType ){
          log.info("测试{}，日志级别{}，输出{}", "demo1aaaaaaaaaaaaaaaaaaaaaa", dishType.getName(), "info level log");
        BaseJson baseJson = new BaseJson();
        // log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
        // int flag =detailService.regist(registDto);
        dishType.setShopId(1);
        //int flag = restTemplate.postForObject(REST_URL_PREFIX + "/shopdishes/adddishtype", dishType, Integer.class);
        int flag = dishService.addDishType(dishType);
        if(flag == 0){
            baseJson.setCode(0);
            baseJson.setMessage("失败");
            baseJson.setResult("菜品类别已存在");
        }else {
            baseJson.setCode(1);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
        }
        return baseJson;
    }

    @RequestMapping(value = "/consumer/shopdishes/adddishes",method = RequestMethod.POST)
    public  BaseJson adddishes(Dish dish ){
       // log.info("测试{}，日志级别{}，输出{}", "demo1aaaaaaaaaaaaaaaaaaaaaa", dishType.getName(), "info level log");
        BaseJson baseJson = new BaseJson();
        // log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
        // int flag =detailService.regist(registDto);

        //int flag = restTemplate.postForObject(REST_URL_PREFIX + "/shopdishes/adddishtype", dishType, Integer.class);
        int flag = dishService.addDish(dish);
        if(flag == 0){
            baseJson.setCode(0);
            baseJson.setMessage("失败");
            baseJson.setResult("菜品已存在");
        }else {
            baseJson.setCode(1);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
        }
        return baseJson;
    }
    @RequestMapping(value = "/consumer/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public List<DishType> findAllDishType(@PathVariable("shopId") int shopId) {
            return dishService.findAllDishType(shopId);
       // return restTemplate.getForObject(REST_URL_PREFIX + "/shopdishes/getAllDishType?shopId="+shopId, List.class);
    }
}
