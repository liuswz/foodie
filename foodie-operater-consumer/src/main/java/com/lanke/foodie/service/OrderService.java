package com.lanke.foodie.service;

import com.lanke.foodie.dto.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-ORDERS")
public interface OrderService {

    @RequestMapping(value = "/shoporder/findTotalOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult findTotalOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size);
    @RequestMapping(value = "/shoporder/findOrderByTimeAndValue")
    public PageResultAndCost findOrderByTimeAndValue(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestBody OrderIndexDto orderIndexDto);
    @RequestMapping(value = "/shoporder/updatOrderIfTranster",method = RequestMethod.GET)
    public Integer updatOrderIfTranster( @RequestBody FindOrderParamsDto findOrderParamsDto);
    @RequestMapping(value = "/shoporder/findTotalCost")
    public Double findTotalCost(@RequestBody FindOrderParamsDto findOrderParamsDto);

    @RequestMapping(value = "/shoporder/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId);
}
