package com.lanke.foodie.controller;

import com.alibaba.fastjson.JSONArray;
import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.dto.PageResult;
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
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/consumer/shoporder/addOrder",method = RequestMethod.POST)
    public BaseJson addOrder( Order order , String orderItemList){//Order order,

        List<OrderItem> orderItem = (List<OrderItem>)JSONArray.parseArray(orderItemList, OrderItem.class);

     //   log.info(order.getOrderNo());
        OrderAndItemDto orderAndItemDto=new OrderAndItemDto();
        orderAndItemDto.setOrder(order);
        orderAndItemDto.setOrderItemList(orderItem);
        BaseJson baseJson = new BaseJson();

        int flag = orderService.addOrder(orderAndItemDto);
        if(flag == Result.SUCCESS.getIndex()){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
        }else {

            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("添加失败");


        }
        return baseJson;

    }


    @RequestMapping(value = "/consumer/shoporder/findAllOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findAllOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId) {

        return orderService.findAllOrder(page,size,shopId);
    }
    @RequestMapping(value = "/consumer/shoporder/findOrderByTime",method = RequestMethod.POST)
    public PageResult findOrderByTime(  @RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size,
                                        FindOrderParamsDto findOrderParamsDto) {
log.info(page+size+findOrderParamsDto.getFromTime()+findOrderParamsDto.getToTime());
        return orderService.findOrderByTime(page,size,findOrderParamsDto);
    }
    @RequestMapping(value = "/consumer/shoporder/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId) {

        return orderService.findOrderItem(orderId,shopId);
    }


    @RequestMapping(value = "/consumer/shoporder/findNotFinishOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResult findNotFinishOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId){
        return orderService.findNotFinishOrder(page,size,shopId);
    }
    @RequestMapping(value = "/consumer/shoporder/updatOrder/{orderStatus}/{id}",method = RequestMethod.GET)
    public BaseJson  updatOrder( @PathVariable("orderStatus") Integer orderStatus,@PathVariable("id") Integer id){

        int flag = orderService.updatOrder(orderStatus,id);

        BaseJson baseJson = new BaseJson();
        if(flag == Result.SUCCESS.getIndex()){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");
        }else {

            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("更改失败");


        }
        return baseJson;

    }
}
