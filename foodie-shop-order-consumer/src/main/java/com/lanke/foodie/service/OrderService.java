package com.lanke.foodie.service;

import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.dto.PageResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-SHOPORDERS")
public interface OrderService {

    @RequestMapping(value = "/shoporder/addOrder",method = RequestMethod.POST)
    public Integer addOrder(@RequestBody OrderAndItemDto orderAndItemDto );
    @RequestMapping(value = "/shoporder/findAllOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findAllOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/shoporder/findOrderByTime",method = RequestMethod.POST)
    public PageResult findOrderByTime(@RequestParam("page") Integer page,  @RequestParam("size")  Integer size, @RequestBody FindOrderParamsDto findOrderParamsDto);
    @RequestMapping(value = "/shoporder/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId) ;

    @RequestMapping(value = "/shoporder/findNotFinishOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findNotFinishOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/shoporder/updatOrder/{orderStatus}/{id}",method = RequestMethod.GET)
    public Integer  updatOrder( @PathVariable("orderStatus") Integer orderStatus,@PathVariable("id") Integer id);
}
