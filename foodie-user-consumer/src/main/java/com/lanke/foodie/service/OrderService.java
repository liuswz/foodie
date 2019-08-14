package com.lanke.foodie.service;

import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.dto.PageResult;

import com.lanke.foodie.entity.Order;
import com.lanke.foodie.simpleEntity.SimpleOrderItem;
import com.lanke.foodie.userdto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-ORDERS")
public interface OrderService {

    @RequestMapping(value = "/order/addDishForAppointOrder",method = RequestMethod.POST)
    public Integer addDishForAppointOrder(@RequestBody OrderAndItemDto<DishForAppointOrder> order);
    @RequestMapping(value = "/order/addDishForGoShopOrder",method = RequestMethod.POST)
    public Integer addDishForGoShopOrder(@RequestBody OrderAndItemDto<DishForGoShopOrder> order);
    @RequestMapping(value = "/order/addProductForDeliveryOrder",method = RequestMethod.POST)
    public Integer addProductForDeliveryOrder(@RequestBody OrderAndItemDto<ProductForDeliveryOrder> order);
    @RequestMapping(value = "/order/addProductForGoShopOrder",method = RequestMethod.POST)
    public Integer addProductForGoShopOrder(@RequestBody OrderAndItemDto<ProductForGoShopOrder> order);


    @RequestMapping(value = "/order/addOrder",method = RequestMethod.POST)
    public Integer addOrder(@RequestBody OrderAndItemDto orderAndItemDto );
//    @RequestMapping(value = "/shoporder/findAllOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
//    public PageResult findAllOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId);
//    @RequestMapping(value = "/shoporder/findOrderByTime",method = RequestMethod.POST)
//    public PageResult findOrderByTime(@RequestParam("page") Integer page,  @RequestParam("size")  Integer size, @RequestBody FindOrderParamsDto findOrderParamsDto);
//    @RequestMapping(value = "/shoporder/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
//    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId) ;
//
//    @RequestMapping(value = "/shoporder/findNotFinishOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
//    public PageResult findNotFinishOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/order/updateOrderStatus/{orderStatus}/{id}",method = RequestMethod.GET)
    public Integer  updateOrderStatus( @PathVariable("orderStatus") Integer orderStatus,@PathVariable("id") Integer id);


    @RequestMapping(value = "/order/findAllSimpleOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults findAllSimpleOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("userId")  Integer userId);
    @RequestMapping(value = "/order/findAllNotPayOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults findAllNotPayOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId);
    @RequestMapping(value = "/order/findAllHadPayOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults findAllHadPayOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId);
    @RequestMapping(value = "/order/findAllAppointOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults findAllAppointOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId);

    @RequestMapping(value = "/order/getOrderDetails/{id}",method = RequestMethod.GET)
    public Order getOrderDetails(@PathVariable("id")  Integer id);
    @RequestMapping(value = "/order/getOrderItems/{orderId}",method = RequestMethod.GET)
    public List<SimpleOrderItem> getOrderItems(@PathVariable("orderId")  Integer orderId);
    @RequestMapping(value = "/order/updatePayStatus",method = RequestMethod.POST)
    public Integer updatePayStatus(@RequestParam("id") Integer id,@RequestParam("payWay") Integer payWay);
    @RequestMapping(value = "/order/updateFinishStatus",method = RequestMethod.POST)
    public Integer updateFinishStatus(@RequestParam("id") Integer id);
}
