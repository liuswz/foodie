package com.lanke.foodie.controller;

import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.FindOrderParamsDto;
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
    @RequestMapping(value = "/shoporder/findOrderByTime",method = RequestMethod.POST)
    public PageResult findOrderByTime(@RequestParam("page")  Integer page, @RequestParam("size") Integer size,@RequestBody FindOrderParamsDto findOrderParamsDto) {

        return orderService.findOrderByTime(page,size,findOrderParamsDto);
    }
    @RequestMapping(value = "/shoporder/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId,@PathVariable("shopId") Integer shopId) {


        return orderService.findOrderItem(orderId,shopId);
    }
}
