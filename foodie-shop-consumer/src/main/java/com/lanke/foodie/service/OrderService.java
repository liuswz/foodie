package com.lanke.foodie.service;

import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.dto.PageResult;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-ORDERS")
public interface OrderService {

//    @RequestMapping(value = "/order/addOrder",method = RequestMethod.POST)
//    public Integer addOrder(@RequestBody OrderAndItemDto orderAndItemDto);
    @RequestMapping(value = "/order/findAllOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findAllOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/order/findOrderByTime",method = RequestMethod.POST)
    public PageResult findOrderByTime(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestBody FindOrderParamsDto findOrderParamsDto);
    @RequestMapping(value = "/order/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId) ;

    @RequestMapping(value = "/order/findNotFinishOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findNotFinishOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/order/updateOrderStatus/{orderStatus}/{id}",method = RequestMethod.GET)
    public Integer  updateOrderStatus(@PathVariable("orderStatus") Integer orderStatus, @PathVariable("id") Integer id);
    @RequestMapping(value = "/order/updateDishStatus/{dishStatus}/{id}",method = RequestMethod.GET)
    public Integer  updateDishStatus( @PathVariable("dishStatus") Integer dishStatus,@PathVariable("id") Integer id);
}
