package com.lanke.foodie.service;

import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.OrderIndexDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.dto.PageResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-ORDERS")
public interface OrderService {

    @RequestMapping(value = "/shoporder/findTotalOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult findTotalOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size);
    @RequestMapping(value = "/shoporder/findOrderByTimeAndValue",method = RequestMethod.POST)
    public PageResult findOrderByTimeAndValue(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestBody OrderIndexDto orderIndexDto);
    @RequestMapping(value = "/shoporder/updatOrderIfTranster",method = RequestMethod.GET)
    public Integer updatOrderIfTranster(@RequestBody FindOrderParamsDto findOrderParamsDto);
    @RequestMapping(value = "/shoporder/findTotalCost",method = RequestMethod.GET)
    public Double findTotalCost(@RequestBody FindOrderParamsDto findOrderParamsDto);
    @RequestMapping(value = "/shoporder/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId);
}
