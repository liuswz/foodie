package com.lanke.foodie.controller;

import com.lanke.foodie.dto.*;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.service.OrderService;
import com.lanke.foodie.service.UserOrderService;
import com.lanke.foodie.simpleEntity.SimpleOrderItem;
import com.lanke.foodie.userdto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserOrderController {
    @Autowired
    private UserOrderService orderService;


    @RequestMapping(value = "/order/addOrder",method = RequestMethod.POST)
    public Integer addOrder(@RequestBody OrderAndItemDto orderAndItemDto ){

        return orderService.add_order(orderAndItemDto);
    }
    @RequestMapping(value = "/order/addDishForAppointOrder",method = RequestMethod.POST)
    public Integer addDishForAppointOrder(@RequestBody OrderAndItemDto<DishForAppointOrder> order){

        return orderService.addDishForAppointOrder(order);
    }
    @RequestMapping(value = "/order/addDishForGoShopOrder",method = RequestMethod.POST)
    public Integer addDishForGoShopOrder(@RequestBody OrderAndItemDto<DishForGoShopOrder> order){

        return orderService.addDishForGoShopOrder(order);
    }
    @RequestMapping(value = "/order/addProductForDeliveryOrder",method = RequestMethod.POST)
    public Integer addProductForDeliveryOrder(@RequestBody OrderAndItemDto<ProductForDeliveryOrder> order){

        return orderService.addProductForDeliveryOrder(order);
    }
    @RequestMapping(value = "/order/addProductForGoShopOrder",method = RequestMethod.POST)
    public Integer addProductForGoShopOrder(@RequestBody OrderAndItemDto<ProductForGoShopOrder> order){

        return orderService.addProductForGoShopOrder(order);
    }


    @RequestMapping(value = "/order/findAllSimpleOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults findAllSimpleOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId){
        return orderService.findAllSimpleOrder(page,size,userId);
    }
    @RequestMapping(value = "/order/findAllNotPayOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults findAllNotPayOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId){
        return orderService.findAllNotPayOrder(page,size,userId);
    }
    @RequestMapping(value = "/order/findAllHadPayOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults findAllHadPayOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId){
        return orderService.findAllHadPayOrder(page,size,userId);
    }
    @RequestMapping(value = "/order/findAllAppointOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults findAllAppointOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId){
        return orderService.findAllAppointOrder(page,size,userId);
    }

    @RequestMapping(value = "/order/getOrderDetails/{id}",method = RequestMethod.GET)
    public Order getOrderDetails(@PathVariable("id") Integer id){
        return orderService.getOrderDetails(id);
    }

    @RequestMapping(value = "/order/getOrderItems/{orderId}",method = RequestMethod.GET)
    public List<SimpleOrderItem> getOrderItems(@PathVariable("orderId")  Integer orderId) {
        return orderService.getOrderItems(orderId);
    }
    @RequestMapping(value = "/order/updatePayStatus",method = RequestMethod.POST)
    public Integer updatePayStatus(@RequestParam("id") Integer id,@RequestParam("payWay") Integer payWay){
        return orderService.updatePayStatus(id,payWay);
    }
    @RequestMapping(value = "/order/updateFinishStatus",method = RequestMethod.POST)
    public Integer updateFinishStatus(@RequestParam("id") Integer id){
        return orderService.updateFinishStatus(id);
    }
}
