package com.lanke.foodie.controller;

import com.lanke.foodie.dto.DishAndTypeDto;
import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
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
import java.util.Map;

@Slf4j
@RestController
public class DishesController {


   // private static final String REST_URL_PREFIX = "http://FOODIE-SHOPDISHES";

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/consumer/shopdishes/adddishtype",method = RequestMethod.POST)
    public  BaseJson adddishtype(DishType dishType ){
       //   log.info("测试{}，日志级别{}，输出{}", "demo1aaaaaaaaaaaaaaaaaaaaaa", dishType.getTypeName(), "info level log");
        BaseJson baseJson = new BaseJson();
        // log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
        // int flag =detailService.regist(registDto);
      //  dishType.setShopId(1);
        //int flag = restTemplate.postForObject(REST_URL_PREFIX + "/shopdishes/adddishtype", dishType, Integer.class);
        int flag = dishService.addDishType(dishType);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("菜品类别已存在");

        }else {
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");

        }
        return baseJson;
    }

    @RequestMapping(value = "/consumer/shopdishes/adddishes",method = RequestMethod.POST)
    public  BaseJson adddishes(Dish dish ){
    //    log.info("测试{}，日志级别{}，输出{}", "demo1aaaaaaaaaaaaaaaaaaaaaa", dish.getName(), "info level log");
        BaseJson baseJson = new BaseJson();
        // log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
        // int flag =detailService.regist(registDto);

        //int flag = restTemplate.postForObject(REST_URL_PREFIX + "/shopdishes/adddishtype", dishType, Integer.class);
        int flag = dishService.addDish(dish);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("菜品已存在");
        }else {

            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");


        }
        return baseJson;
    }
    @RequestMapping(value = "/consumer/shopdishes/getAllDishType/{shopId}/{page}/{size}",method = RequestMethod.GET)
    public PageResult findAllDishType(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId) {
            return dishService.findAllDishType(page,size,shopId);
       // return restTemplate.getForObject(REST_URL_PREFIX + "/shopdishes/getAllDishType?shopId="+shopId, List.class);
    }
    @RequestMapping(value = "/consumer/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public List<DishType> findAllDishType(@PathVariable("shopId") Integer shopId) {
        return dishService.findAllDishType(shopId);
        // return restTemplate.getForObject(REST_URL_PREFIX + "/shopdishes/getAllDishType?shopId="+shopId, List.class);
    }
    @RequestMapping(value = "/consumer/shopdishes/getAllDishes",method = RequestMethod.GET)
    public PageResult getAllDishes(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("shopId") Integer shopId,
            @RequestParam(value="value",defaultValue="") String value) {

        return dishService.getAllDishes(page,size,shopId,value);
    }

    @RequestMapping(value = "/consumer/shopdishes/delDishTypeById/{ids}",method = RequestMethod.GET)
    public BaseJson delDishTypeById(@PathVariable("ids") String ids) {

        BaseJson baseJson = new BaseJson();
        if(dishService.delDishTypeById(ids) > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/consumer/shopdishes/delDishById/{ids}",method = RequestMethod.GET)
    public BaseJson delDisheById(@PathVariable("ids") String ids) {

        BaseJson baseJson = new BaseJson();
        if(dishService.delDishById(ids) > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/consumer/shopdishes/getDishAndTypeById/{id}/{shopId}",method = RequestMethod.GET)
    public DishAndTypeDto getDishAndTypeById(@PathVariable("id") Integer id,@PathVariable("shopId") Integer shopId) {
     //   BaseJson baseJson = new BaseJson();
        DishAndTypeDto dishAndTypeDto = new DishAndTypeDto();

        dishAndTypeDto.setDish(dishService.getDishById(id));
        dishAndTypeDto.setTypelist(dishService.findAllDishType(shopId));
        return dishAndTypeDto;

    }
    @RequestMapping(value = "/consumer/shopdishes/updateDish",method = RequestMethod.POST)
    public BaseJson updateDish( Dish dish ){
        BaseJson baseJson = new BaseJson();
        log.info(dish.getName()+"***********************8");
        int flag = dishService.updateDish(dish);
        if(flag > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");


        }else {

            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("更改失败");
        }
        return baseJson;

    }

    @RequestMapping(value = "/consumer/shopdishes/getIfDishByTypeId",method = RequestMethod.POST)
    public BaseJson getIfDishByTypeId( DishesDto dishesDto ){
        BaseJson baseJson = new BaseJson();

        int flag = dishService.getIfDishByTypeId(dishesDto);
        if(flag > 0){
            baseJson.setCode(0);

        }else {
            baseJson.setCode(1);
        }
        return baseJson;

    }
}
