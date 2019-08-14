package com.lanke.foodie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lanke.foodie.dto.DishAndTypeDto;
import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.enums.OperateStatus;
import com.lanke.foodie.json.BaseJson;

import com.lanke.foodie.repository.ShopRepository;
import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.service.DetailService;
import com.lanke.foodie.service.DishService;
import com.lanke.foodie.utils.ESUtils;
import feign.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
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
//    @Autowired
//    private DishRepository dishRepository;

    @Autowired
    private ShopRepository shopRepository;

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
        BaseJson baseJson = new BaseJson();


        Integer flag = dishService.addDish(dish);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("菜品已存在");
        }else {

            if(detailService.getOperaterStatus(dish.getShopId())==OperateStatus.HadOperate.getIndex()){


                shopRepository.save(ESUtils.setSearchDishAndId(detailService.getShopDetailsById(dish.getShopId()),dish,flag));
                Map<String,Integer> map = new HashMap<String, Integer>();
                map.put("id",dish.getShopId());
                map.put("flag",0);
                rabbitTemplate.convertAndSend("statics", map);

            }

            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");


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
        int flag = dishService.delDishTypeById(ids);

        if(flag > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("删除成功");

        }else if(flag==0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("有此类别下的菜品，无法删除");
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("删除失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/shopdishes/delDishById/{ids}/{shopId}",method = RequestMethod.GET)
    public BaseJson delDisheById(@PathVariable("ids") String ids,@PathVariable("shopId") Integer shopId) {

        BaseJson baseJson = new BaseJson();
        if(dishService.delDishById(ids) > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");

            if(detailService.getOperaterStatus(shopId)==OperateStatus.HadOperate.getIndex()){
                String idss[] = ids.split(",");
                for(String id:idss){
                    shopRepository.deleteById(ESUtils.getDishId(Integer.parseInt(id)));
                }
                Map<String,Integer> map = new HashMap<String, Integer>();
                map.put("id",shopId);
                map.put("flag",0);
                rabbitTemplate.convertAndSend("statics", map);
            }


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
            if(detailService.getOperaterStatus(dish.getShopId())==OperateStatus.HadOperate.getIndex()){

                SearchShop searchShop  = ESUtils.setSearchDish(detailService.getShopDetailsById(dish.getShopId()),dish);
                shopRepository.save(searchShop);

                Map<String,Integer> map = new HashMap<String, Integer>();
                map.put("id",dish.getShopId());
                map.put("flag",0);
                rabbitTemplate.convertAndSend("statics", map);

            }

        }else {

            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("菜名已存在");
        }
        return baseJson;

    }

//    @RequestMapping(value = "/shopdishes/getIfDishByTypeId",method = RequestMethod.POST)
//    public BaseJson getIfDishByTypeId( DishesDto dishesDto ){
//        BaseJson baseJson = new BaseJson();
//
//
//        int flag = dishService.getIfDishByTypeId(dishesDto);
//        if(flag > 0){
//            baseJson.setCode(0);
//
//        }else {
//            baseJson.setCode(1);
//        }
//        return baseJson;
//
//    }


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
            @RequestParam("productType") String productType) {
        return dishService.findAllProductWithMoneyOffByType(page,size,productType);
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

    @RequestMapping(value = "/shopdishes/updateIfHotDish/{id}/{value}",method = RequestMethod.GET)
    public BaseJson updateIfHotDish(@PathVariable("id") Integer id,@PathVariable("value") Integer value) {
        Integer flag = dishService.updateIfHotDish(id,value);
        BaseJson baseJson = new BaseJson();
        if(flag==0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("更改热门菜品成功");
        }else if(flag==1){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("已经是热门菜品");
        }else{
            baseJson.setCode(2);
            baseJson.setMessage("失败");
            baseJson.setResult("本身不是热门菜品");
        }
        return baseJson;
    }
}
