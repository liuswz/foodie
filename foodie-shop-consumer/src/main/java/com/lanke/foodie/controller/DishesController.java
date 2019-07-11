package com.lanke.foodie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lanke.foodie.dto.DishAndTypeDto;
import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.entity.Voucher;
import com.lanke.foodie.enums.OperateStatus;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.DetailService;
import com.lanke.foodie.service.DishService;
import feign.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/shopconsumer")
public class DishesController {
   // private static final String REST_URL_PREFIX = "http://FOODIE-SHOPDISHES";
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private DishService dishService;
    @Autowired
    private DetailService detailService;

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @RequestMapping(value = "/shopdishes/adddishtype",method = RequestMethod.POST)
    public  BaseJson adddishtype(@RequestBody DishType dishType ){
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

    @RequestMapping(value = "/shopdishes/adddishes",method = RequestMethod.POST)
    public  BaseJson adddishes(@RequestBody Dish dish ){
    //    log.info("测试{}，日志级别{}，输出{}", "demo1aaaaaaaaaaaaaaaaaaaaaa", dish.getName(), "info level log");
        BaseJson baseJson = new BaseJson();
        // log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
        // int flag =detailService.regist(registDto);
        log.info(dish.getName());
       // List<Dish> dishList = (List<Dish>)JSONArray.parseArray(dishes, Dish.class);
      //  Dish dishs = JSON.parseObject(dish, Dish.class);
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

            rabbitTemplate.convertAndSend("statics", dish.getShopId());
        }
        return baseJson;
    }
    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}/{page}/{size}",method = RequestMethod.GET)
    public PageResult findAllDishType(@PathVariable("shopId") Integer shopId,@PathVariable("page") Integer page, @PathVariable("size") Integer size) {

            return dishService.findAllDishType(shopId,page,size);
       // return restTemplate.getForObject(REST_URL_PREFIX + "/shopdishes/getAllDishType?shopId="+shopId, List.class);
    }
    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public List<DishType> findAllDishType(@PathVariable("shopId") Integer shopId) {
        return dishService.findAllDishType(shopId);
        // return restTemplate.getForObject(REST_URL_PREFIX + "/shopdishes/getAllDishType?shopId="+shopId, List.class);
    }
    @RequestMapping(value = "/shopdishes/getAllDishes",method = RequestMethod.GET)
    public PageResult getAllDishes(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("shopId") Integer shopId,
            @RequestParam(value="value",defaultValue="") String value) {

        return dishService.getAllDishes(page,size,shopId,value);
    }

    @RequestMapping(value = "/shopdishes/delDishTypeById/{ids}/{shopId}",method = RequestMethod.GET)
    public BaseJson delDishTypeById(@PathVariable("ids") String ids,@PathVariable("shopId") Integer shopId) {

        BaseJson baseJson = new BaseJson();
        if(dishService.delDishTypeById(ids) > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            rabbitTemplate.convertAndSend("statics",shopId);
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/shopdishes/delDishById/{ids}/{shopId}",method = RequestMethod.GET)
    public BaseJson delDisheById(@PathVariable("ids") String ids,@PathVariable("shopId") Integer shopId) {

        BaseJson baseJson = new BaseJson();
        if(dishService.delDishById(ids) > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            rabbitTemplate.convertAndSend("statics",shopId);
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/shopdishes/getDishAndTypeById/{id}/{shopId}",method = RequestMethod.GET)
    public DishAndTypeDto getDishAndTypeById(@PathVariable("id") Integer id,@PathVariable("shopId") Integer shopId) {
     //   BaseJson baseJson = new BaseJson();
        DishAndTypeDto dishAndTypeDto = new DishAndTypeDto();

        dishAndTypeDto.setDish(dishService.getDishById(id));
        dishAndTypeDto.setTypelist(dishService.findAllDishType(shopId));
        return dishAndTypeDto;

    }
    @RequestMapping(value = "/shopdishes/updateDish",method = RequestMethod.POST)
    public BaseJson updateDish(@RequestBody Dish dish ){
        BaseJson baseJson = new BaseJson();
      //  log.info(dish.getName()+"***********************8");
        int flag = dishService.updateDish(dish);
        if(flag > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");
            rabbitTemplate.convertAndSend("statics",dish.getShopId());
        }else {

            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("菜名已存在");
        }
        return baseJson;

    }

    @RequestMapping(value = "/shopdishes/getIfDishByTypeId",method = RequestMethod.POST)
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


    //商品
    @RequestMapping(value = "/product/findAllProductWithMoneyOff",method = RequestMethod.GET)
    public PageResult findAllProductWithMoneyOff(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(value="value",defaultValue="") String value) {

        return dishService.findAllProductWithMoneyOff(page,size,value);
    }
    @RequestMapping(value = "/product/findAllProductWithMoneyOffByType",method = RequestMethod.GET)
    public PageResult findAllProductWithMoneyOffByType(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("productTypeId") Integer productTypeId) {
        return dishService.findAllProductWithMoneyOffByType(page,size,productTypeId);
    }

    //代金卷
    @RequestMapping(value = "/product/addShopVoucher",method = RequestMethod.POST)
    public BaseJson addShopVoucher(@RequestBody Voucher voucher ){
        BaseJson baseJson = new BaseJson();

        int operaterStatus = detailService.getOperaterStatus(voucher.getShopId());   //
        if(operaterStatus == OperateStatus.NotOperate.getIndex()){
            baseJson.setCode(0);
            baseJson.setMessage("失败");
            baseJson.setResult("您还没开始运营,无法添加代金卷");
        }else {

            int flag = dishService.addShopVoucher(voucher);
            if(flag > 0){
                baseJson.setCode(0);
                baseJson.setMessage("成功");
                baseJson.setResult("添加成功");

            }else {
                baseJson.setCode(1);
                baseJson.setMessage("失败");
                baseJson.setResult("添加失败");
            }
        }



        return baseJson;

    }
    @RequestMapping(value = "/product/getVoucherById/{shopId}",method = RequestMethod.GET)
    public Voucher getVoucherById(@PathVariable("shopId") Integer shopId) {
        return dishService.getVoucherById(shopId);
    }
    @RequestMapping(value = "/product/delVoucherById/{shopId}",method = RequestMethod.GET)
    public BaseJson delVoucherById(@PathVariable("shopId") Integer shopId) {

        BaseJson baseJson = new BaseJson();

        int operaterStatus = detailService.getOperaterStatus(shopId);   //
        if(operaterStatus == OperateStatus.NotOperate.getIndex()){
            baseJson.setCode(0);
            baseJson.setMessage("失败");
            baseJson.setResult("您还没开始运营,没有代金卷");
        }else {

            if(dishService.delVoucherById(shopId) > 0){
                baseJson.setCode(0);
                baseJson.setMessage("成功");
                baseJson.setResult("取消代金卷成功");
            }else{
                baseJson.setCode(1);
                baseJson.setMessage("失败");
                baseJson.setResult("取消代金卷失败");
            }
        }



        return baseJson;

    }
}
