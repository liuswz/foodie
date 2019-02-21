package com.lanke.foodie.controller;

import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.findOrderParamsDto;
import com.lanke.foodie.entity.DishType;
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


    @RequestMapping(value = "/shoporder/addOrder",method = RequestMethod.POST)
    public Integer addOrder(@RequestBody OrderAndItemDto orderAndItemDto ){

        return orderService.add_order(orderAndItemDto);
    }

    @RequestMapping(value = "/shoporder/findAllOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findAllOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId) {

        return orderService.findAllOrder(page,size,shopId);
    }
    @RequestMapping(value = "/shoporder/findOrderByTime",method = RequestMethod.GET)
    public PageResult findOrderByTime(Integer page, Integer size, findOrderParamsDto findOrderParamsDto) {

        return orderService.findOrderByTime(page,size,findOrderParamsDto);
    }
    @RequestMapping(value = "/shoporder/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId,@PathVariable("shopId") Integer shopId) {

        log.info(orderId+"--------------------");
        return orderService.findOrderItem(orderId,shopId);
    }
}
