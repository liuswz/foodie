package com.lanke.foodie.service;

import com.lanke.foodie.dto.*;

import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-ORDERS")
public interface OrderService {

    @RequestMapping(value = "/order/findTotalDishOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult  findTotalDishOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size );
    @RequestMapping(value = "/order/findTotalProductOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult  findTotalProductOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size );
    @RequestMapping(value = "/order/findProductNotFinishOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult  findProductNotFinishOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size );

    @RequestMapping(value = "/order/findDishOrderByTimeAndValue")
    public PageResultAndCost findDishOrderByTimeAndValue(@RequestParam("page")  Integer page, @RequestParam("size") Integer size,@RequestBody OrderIndexDto orderIndexDto);
    @RequestMapping(value = "/order/findProductOrderByTimeAndValue")
    public PageResultAndCost findProductOrderByTimeAndValue(@RequestParam("page")  Integer page, @RequestParam("size") Integer size,@RequestBody OrderIndexDto orderIndexDto);

    @RequestMapping(value = "/order/findOrderItem/{orderId}",method = RequestMethod.GET)
    public List<OrderItem> findOrderItem(@PathVariable("orderId") Integer orderId);
    @RequestMapping(value = "/order/findOrderById/{orderId}",method = RequestMethod.GET)
    public Order findOrderById(@PathVariable("orderId") Integer orderId);
    @RequestMapping(value = "/order/updateFinishStatus/{id}",method = RequestMethod.GET)
    public Integer  updateFinishStatus(@PathVariable("id") Integer id);
    @RequestMapping(value = "/order/updatOrderIfTranster",method = RequestMethod.POST)
    public Integer updatOrderIfTranster (@RequestBody FindOrderParamsDto findOrderParamsDto);
    @RequestMapping(value = "/order/findTotalCost",method = RequestMethod.GET)
    public Double findTotalCost(@RequestBody FindOrderParamsDto findOrderParamsDto);

//    @RequestMapping(value = "/order/findTotalOrders/{page}/{size}",method = RequestMethod.GET)
//    public PageResult findTotalOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size);
//    @RequestMapping(value = "/order/findOrderByTimeAndValue")
//    public PageResultAndCost findOrderByTimeAndValue(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestBody OrderIndexDto orderIndexDto);
//    @RequestMapping(value = "/order/updatOrderIfTranster",method = RequestMethod.GET)
//    public Integer updatOrderIfTranster( @RequestBody FindOrderParamsDto findOrderParamsDto);
//    @RequestMapping(value = "/order/findTotalCost")
//    public Double findTotalCost(@RequestBody FindOrderParamsDto findOrderParamsDto);
//
//    @RequestMapping(value = "/order/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
//    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId);
}
