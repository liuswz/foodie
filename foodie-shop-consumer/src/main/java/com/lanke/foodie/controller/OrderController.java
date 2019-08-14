package com.lanke.foodie.controller;

import com.alibaba.fastjson.JSONArray;
import com.lanke.foodie.dto.*;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shopconsumer")
public class OrderController {

    @Autowired
    private OrderService orderService;

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
    public PageResultAndCost findDishOrderByTime(@RequestParam("page")  Integer page, @RequestParam("size") Integer size, @RequestBody FindOrderParamsDto findOrderParamsDto){
        return orderService.findDishOrderByTime(page,size,findOrderParamsDto);
    }
    @RequestMapping(value = "/order/findOrderItem/{orderId}",method = RequestMethod.GET)
    public BaseJson findOrderItem(@PathVariable("orderId") Integer orderId){
        BaseJson baseJson = new BaseJson();
        baseJson.setCode(Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(orderService.findOrderItem(orderId));
        return baseJson;
    }
    @RequestMapping(value = "/order/findOrderById/{orderId}",method = RequestMethod.GET)
    public BaseJson findOrderById(@PathVariable("orderId") Integer orderId){
        BaseJson baseJson = new BaseJson();
        baseJson.setCode(Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(orderService.findOrderById(orderId));
        return baseJson;
    }
    @RequestMapping(value = "/order/updateFinishStatus/{id}",method = RequestMethod.GET)
    public BaseJson  updateFinishStatus(@PathVariable("id") Integer id){
        int flag = orderService.updateFinishStatus(id);

        BaseJson baseJson = new BaseJson();
        if(flag >0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");
        }else {
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("更改失败");
        }
        return baseJson;
    }
    @RequestMapping(value = "/order/updateIfGoodHadReach/{id}",method = RequestMethod.GET)
    public BaseJson updateIfGoodHadReach(@PathVariable("id") Integer id) {
        int flag =  orderService.updateIfGoodHadReach(id);

        BaseJson baseJson = new BaseJson();
        if(flag >0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");
        }else {
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("更改失败");
        }
        return baseJson;
    }






//
//
//
//    @RequestMapping(value = "/consumer/order/findAllOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
//    public PageResult findAllOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId) {
//
//        return orderService.findAllOrder(page,size,shopId);
//    }
//    @RequestMapping(value = "/consumer/order/findOrderByTime",method = RequestMethod.POST)
//    public PageResult findOrderByTime(  @RequestParam("page") Integer page,
//                                        @RequestParam("size") Integer size,
//                                        FindOrderParamsDto findOrderParamsDto) {
////log.info(page+size+findOrderParamsDto.getFromTime()+findOrderParamsDto.getToTime());
//        return orderService.findOrderByTime(page,size,findOrderParamsDto);
//    }
//    @RequestMapping(value = "/consumer/order/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
//    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId) {
//
//        return orderService.findOrderItem(orderId,shopId);
//    }
//
//
//    @RequestMapping(value = "/consumer/order/findNotFinishOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
//    public PageResult findNotFinishOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId){
//        return orderService.findNotFinishOrder(page,size,shopId);
//    }
//    @RequestMapping(value = "/consumer/order/updateOrderStatus/{orderStatus}/{id}",method = RequestMethod.GET)
//    public BaseJson  updateOrderStatus( @PathVariable("orderStatus") Integer orderStatus,@PathVariable("id") Integer id){
//
//        int flag = orderService.updateOrderStatus(orderStatus,id);
//
//        BaseJson baseJson = new BaseJson();
//        if(flag == Result.SUCCESS.getIndex()){
//            baseJson.setCode(flag);
//            baseJson.setMessage("成功");
//            baseJson.setResult("更改成功");
//        }else {
//
//            baseJson.setCode(flag);
//            baseJson.setMessage("失败");
//            baseJson.setResult("更改失败");
//
//
//        }
//        return baseJson;
//
//    }
//    @RequestMapping(value = "/consumer/order/updateDishStatus/{dishStatus}/{id}",method = RequestMethod.GET)
//    public BaseJson  updateDishStatus( @PathVariable("dishStatus") Integer dishStatus,@PathVariable("id") Integer id){
//
//        int flag = orderService.updateDishStatus(dishStatus,id);
//
//        BaseJson baseJson = new BaseJson();
//        if(flag == Result.SUCCESS.getIndex()){
//            baseJson.setCode(flag);
//            baseJson.setMessage("成功");
//            baseJson.setResult("更改成功");
//        }else {
//
//            baseJson.setCode(flag);
//            baseJson.setMessage("失败");
//            baseJson.setResult("更改失败");
//
//
//        }
//        return baseJson;
//
//    }

}
