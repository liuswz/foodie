package com.lanke.foodie.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/consumer/shoporder/addOrder",method = RequestMethod.POST)
    public BaseJson addOrder( Order order , String orderItemList){//Order order,

        List<OrderItem> orderItem = (List<OrderItem>)JSONArray.parseArray(orderItemList, OrderItem.class);

     //   log.info(order.getOrderNo());
        OrderAndItemDto orderAndItemDto=new OrderAndItemDto();
        orderAndItemDto.setOrder(order);
        orderAndItemDto.setOrderItemList(orderItem);
        BaseJson baseJson = new BaseJson();
        // log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
        // int flag =detailService.regist(registDto);

        //int flag = restTemplate.postForObject(REST_URL_PREFIX + "/shopdishes/adddishtype", dishType, Integer.class);
        int flag = orderService.addOrder(orderAndItemDto);
        if(flag == Result.SUCCESS.getIndex()){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
        }else {

            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("添加失败");


        }
        return baseJson;

    }
}
