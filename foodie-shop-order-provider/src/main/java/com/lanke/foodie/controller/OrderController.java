package com.lanke.foodie.controller;

import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/shoporder/addOrder",method = RequestMethod.POST)
    public Integer addOrder(@RequestBody OrderAndItemDto orderAndItemDto ){

        return orderService.add_order(orderAndItemDto);
    }
}
