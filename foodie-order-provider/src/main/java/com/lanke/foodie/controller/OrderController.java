package com.lanke.foodie.controller;

import com.lanke.foodie.dto.*;
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
    @RequestMapping(value = "/shoporder/findNotFinishOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findNotFinishOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId){
        return orderService.findNotFinishOrder(page,size,shopId);
    }
    @RequestMapping(value = "/shoporder/updateOrderStatus/{orderStatus}/{id}",method = RequestMethod.GET)
    public Integer  updateOrderStatus( @PathVariable("orderStatus") Integer orderStatus,@PathVariable("id") Integer id){
        return orderService.updateOrderStatus(orderStatus,id);
    }
    @RequestMapping(value = "/shoporder/updateDishStatus/{dishStatus}/{id}",method = RequestMethod.GET)
    public Integer  updateDishStatus( @PathVariable("dishStatus") Integer dishStatus,@PathVariable("id") Integer id){
        return orderService.updateDishStatus(dishStatus,id);
    }

    @RequestMapping(value = "/shoporder/findTotalOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult findTotalOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return orderService.findTotalOrders(page,size);
    }

    @RequestMapping(value = "/shoporder/findOrderByTimeAndValue",method = RequestMethod.POST)
    public PageResultAndCost findOrderByTimeAndValue(@RequestParam("page")  Integer page, @RequestParam("size") Integer size,@RequestBody OrderIndexDto orderIndexDto) {
        log.info(orderIndexDto.getValue()+"---------------------------");
        return orderService.findOrderByTimeAndValue(page,size,orderIndexDto);
    }
    @RequestMapping(value = "/shoporder/updatOrderIfTranster",method = RequestMethod.GET)
    public Integer updatOrderIfTranster (@RequestBody FindOrderParamsDto findOrderParamsDto){

        return orderService.updatOrderIfTranster(findOrderParamsDto);
    }

    @RequestMapping(value = "/shoporder/findTotalCost",method = RequestMethod.GET)
    public Double findTotalCost(@RequestBody FindOrderParamsDto findOrderParamsDto){
        return orderService.findTotalCost(findOrderParamsDto);
    }
}
