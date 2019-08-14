package com.lanke.foodie.service;

import com.lanke.foodie.dto.*;

import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-ORDERS")
public interface OrderService {

    @RequestMapping(value = "/order/findAllDishOrderByShopId/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findAllDishOrderByShopId(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/order/findGoToShopOrderByShopId/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findGoToShopOrderByShopId(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId);

    @RequestMapping(value = "/order/findAppointOrderByShopId/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findAppointOrderByShopId(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId);

    @RequestMapping(value = "/order/findOrderGetProductInShop/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findOrderGetProductInShop(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId);

    @RequestMapping(value = "/order/findNotFinishOrderGetProductInShop/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findNotFinishOrderGetProductInShop(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/order/findDishOrderByTime")
    public PageResultAndCost findDishOrderByTime(@RequestParam("page")  Integer page, @RequestParam("size") Integer size, @RequestBody FindOrderParamsDto findOrderParamsDto);
    @RequestMapping(value = "/order/findOrderItem/{orderId}",method = RequestMethod.GET)
    public List<OrderItem> findOrderItem(@PathVariable("orderId") Integer orderId);
    @RequestMapping(value = "/order/findOrderById/{orderId}",method = RequestMethod.GET)
    public Order findOrderById(@PathVariable("orderId") Integer orderId);
    @RequestMapping(value = "/order/updateFinishStatus/{id}",method = RequestMethod.GET)
    public Integer  updateFinishStatus(@PathVariable("id") Integer id);
    @RequestMapping(value = "/order/updateIfGoodHadReach/{id}",method = RequestMethod.GET)
    public Integer updateIfGoodHadReach(@PathVariable("id") Integer id);
//    @RequestMapping(value = "/order/addOrder",method = RequestMethod.POST)
//    public Integer addOrder(@RequestBody OrderAndItemDto orderAndItemDto);
//    @RequestMapping(value = "/order/findAllOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
//    public PageResult findAllOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId);
//    @RequestMapping(value = "/order/findOrderByTime",method = RequestMethod.POST)
//    public PageResult findOrderByTime(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestBody FindOrderParamsDto findOrderParamsDto);
//    @RequestMapping(value = "/order/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
//    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId) ;
//
//    @RequestMapping(value = "/order/findNotFinishOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
//    public PageResult findNotFinishOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId);
//    @RequestMapping(value = "/order/updateOrderStatus/{orderStatus}/{id}",method = RequestMethod.GET)
//    public Integer  updateOrderStatus(@PathVariable("orderStatus") Integer orderStatus, @PathVariable("id") Integer id);
//    @RequestMapping(value = "/order/updateDishStatus/{dishStatus}/{id}",method = RequestMethod.GET)
//    public Integer  updateDishStatus( @PathVariable("dishStatus") Integer dishStatus,@PathVariable("id") Integer id);
}
