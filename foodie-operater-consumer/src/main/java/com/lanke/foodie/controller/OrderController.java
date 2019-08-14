package com.lanke.foodie.controller;

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
@RequestMapping("/operaterconsumer")
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

    @RequestMapping(value = "/order/findDishOrderByTimeAndValue")
    public PageResultAndCost findDishOrderByTimeAndValue(@RequestParam("page")  Integer page, @RequestParam("size") Integer size,@RequestBody OrderIndexDto orderIndexDto){
        return orderService.findDishOrderByTimeAndValue(page,size,orderIndexDto);
    }
    @RequestMapping(value = "/order/findProductOrderByTimeAndValue")
    public PageResultAndCost findProductOrderByTimeAndValue(@RequestParam("page")  Integer page, @RequestParam("size") Integer size,@RequestBody OrderIndexDto orderIndexDto){
        return orderService.findProductOrderByTimeAndValue(page,size,orderIndexDto);

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
    @RequestMapping(value = "/order/updatOrderIfTranster",method = RequestMethod.POST)
    public BaseJson updatOrderIfTranster (@RequestBody FindOrderParamsDto findOrderParamsDto){
        int flag = orderService.updatOrderIfTranster(findOrderParamsDto);
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
    @RequestMapping(value = "/order/findTotalCost",method = RequestMethod.GET)
    public BaseJson findTotalCost(@RequestBody FindOrderParamsDto findOrderParamsDto){
        BaseJson baseJson = new BaseJson();
        baseJson.setCode(Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(orderService.findTotalCost(findOrderParamsDto));
        return baseJson;
    }





















//restful
//    @RequestMapping(value = "/consumer/order/findTotalOrders/{page}/{size}",method = RequestMethod.GET)
//    public PageResult findTotalOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
//
//        return orderService.findTotalOrders(page,size);
//    }
//    @RequestMapping(value = "/consumer/order/findOrderByTimeAndValue",method = RequestMethod.GET)
//    public PageResultAndCost findOrderByTimeAndValue(@RequestParam("page")  Integer page, @RequestParam("size") Integer size, OrderIndexDto orderIndexDto){
////log.info(page+size+findOrderParamsDto.getFromTime()+findOrderParamsDto.getToTime());
//       // log.info(orderIndexDto.getValue()+"---------------------------");
//        return orderService.findOrderByTimeAndValue(page,size,orderIndexDto);
//    }
//
//
//    @RequestMapping(value = "/consumer/order/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
//    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId) {
//
//        return orderService.findOrderItem(orderId,shopId);
//    }
//
//
//
//    @RequestMapping(value = "/consumer/order/updatOrderIfTranster",method = RequestMethod.GET)
//    public BaseJson  updatOrderIfTranster( FindOrderParamsDto findOrderParamsDto){
//
//        int flag = orderService.updatOrderIfTranster(findOrderParamsDto);
//
//        BaseJson baseJson = new BaseJson();
//        if(flag>0){
//            baseJson.setCode(flag);
//            baseJson.setMessage("成功");
//            baseJson.setResult("更改支付成功");
//        }else {
//
//            baseJson.setCode(flag);
//            baseJson.setMessage("失败");
//            baseJson.setResult("更改支付失败");
//
//
//        }
//        return baseJson;
//
//    }
//    @RequestMapping(value = "/consumer/order/findTotalCost")
//    public BaseJson findTotalCost(FindOrderParamsDto findOrderParamsDto){
//
//     //  log.info(findOrderParamsDto.getFromTime()+findOrderParamsDto.getToTime()+findOrderParamsDto.getShopId());
//        Double totalcost  = orderService.findTotalCost(findOrderParamsDto);
//        if(totalcost==null) totalcost=0.0;
//
//        BaseJson baseJson = new BaseJson();
//        baseJson.setCode(0);
//        baseJson.setMessage("成功");
//        baseJson.setResult(totalcost);
//        return baseJson;
//
//
//    }
}
