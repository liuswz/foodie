package com.lanke.foodie.controller;

import com.lanke.foodie.dto.*;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
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

    @RequestMapping(value = "/order/findTotalDishOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult  findTotalDishOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size ){
        return orderService.findTotalDishOrders(page,size);
    }
    @RequestMapping(value = "/order/findTotalProductOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult  findTotalProductOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size ){
        return orderService.findTotalProductOrders(page,size);
    }
    @RequestMapping(value = "/order/findProductNotFinishOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult  findProductNotFinishOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size ){
        return orderService.findProductNotFinishOrders(page,size);
    }

    //商家
    @RequestMapping(value = "/order/findAllDishOrderByShopId/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findAllDishOrderByShopId(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId){
        return orderService.findAllDishOrderByShopId(page,size,shopId);
    }
    @RequestMapping(value = "/order/findGoToShopOrderByShopId/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findGoToShopOrderByShopId(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId){
        return orderService.findGoToShopOrderByShopId(page,size,shopId);

    }
    @RequestMapping(value = "/order/findAppointOrderByShopId/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findAppointOrderByShopId(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId){
        return orderService.findAppointOrderByShopId(page,size,shopId);

    }
    @RequestMapping(value = "/order/findOrderGetProductInShop/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findOrderGetProductInShop(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId){
        return orderService.findOrderGetProductInShop(page,size,shopId);
    }
    @RequestMapping(value = "/order/findNotFinishOrderGetProductInShop/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findNotFinishOrderGetProductInShop(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId){
        return orderService.findNotFinishOrderGetProductInShop(page,size,shopId);
    }
    @RequestMapping(value = "/order/findDishOrderByTime")
    public PageResult findDishOrderByTime(@RequestParam("page")  Integer page, @RequestParam("size") Integer size,@RequestBody FindOrderParamsDto findOrderParamsDto){
        return orderService.findDishOrderByTime(page,size,findOrderParamsDto);
    }//运营商查询
    @RequestMapping(value = "/order/findDishOrderByTimeAndValue")
    public PageResultAndCost findDishOrderByTimeAndValue(@RequestParam("page")  Integer page, @RequestParam("size") Integer size,@RequestBody OrderIndexDto orderIndexDto){
        return orderService.findDishOrderByTimeAndValue(page,size,orderIndexDto);
    }
    @RequestMapping(value = "/order/findProductOrderByTimeAndValue")
    public PageResultAndCost findProductOrderByTimeAndValue(@RequestParam("page")  Integer page, @RequestParam("size") Integer size,@RequestBody OrderIndexDto orderIndexDto){
        return orderService.findProductOrderByTimeAndValue(page,size,orderIndexDto);

    }//    public Double findDishCostByTimeAndValue(OrderIndexDto orderIndexDto);
//    public Double findProductCostByTimeAndValue(OrderIndexDto orderIndexDto);

    @RequestMapping(value = "/order/findOrderItem/{orderId}",method = RequestMethod.GET)
    public List<OrderItem> findOrderItem(@PathVariable("orderId") Integer orderId){
        return orderService.findOrderItem(orderId);
    }
    @RequestMapping(value = "/order/findOrderById/{orderId}",method = RequestMethod.GET)
    public Order findOrderById(@PathVariable("orderId") Integer orderId){
        return orderService.findOrderById(orderId);
    }
    @RequestMapping(value = "/order/updateFinishStatus/{id}",method = RequestMethod.GET)
    public Integer  updateFinishStatus(@PathVariable("id") Integer id){
        return orderService.updateFinishStatus(id);
    }
    @RequestMapping(value = "/order/updatOrderIfTranster",method = RequestMethod.POST)
    public Integer updatOrderIfTranster (@RequestBody FindOrderParamsDto findOrderParamsDto){
        return orderService.updatOrderIfTranster(findOrderParamsDto);
    }
    @RequestMapping(value = "/order/findTotalCost",method = RequestMethod.GET)
    public Double findTotalCost(@RequestBody FindOrderParamsDto findOrderParamsDto){
        return orderService.findTotalCost(findOrderParamsDto);
    }

}
