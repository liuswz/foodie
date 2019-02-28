package com.lanke.foodie.controller;

import com.lanke.foodie.dto.*;
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


    @RequestMapping(value = "/consumer/shoporder/findTotalOrders/{page}/{size}",method = RequestMethod.GET)
    public PageResult findTotalOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size){

        return orderService.findTotalOrders(page,size);
    }
    @RequestMapping(value = "/consumer/shoporder/findOrderByTimeAndValue",method = RequestMethod.POST)
    public PageResultAndCost findOrderByTimeAndValue(@RequestParam("page")  Integer page, @RequestParam("size") Integer size, OrderIndexDto orderIndexDto){
//log.info(page+size+findOrderParamsDto.getFromTime()+findOrderParamsDto.getToTime());
        return orderService.findOrderByTimeAndValue(page,size,orderIndexDto);
    }


    @RequestMapping(value = "/consumer/shoporder/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId) {

        return orderService.findOrderItem(orderId,shopId);
    }



    @RequestMapping(value = "/consumer/shoporder/updatOrderIfTranster",method = RequestMethod.GET)
    public BaseJson  updatOrderIfTranster( FindOrderParamsDto findOrderParamsDto){

        int flag = orderService.updatOrderIfTranster(findOrderParamsDto);

        BaseJson baseJson = new BaseJson();
        if(flag>0){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("更改支付成功");
        }else {

            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("更改支付失败");


        }
        return baseJson;

    }
    @RequestMapping(value = "/consumer/shoporder/findTotalCost",method = RequestMethod.GET)
    public BaseJson findTotalCost( FindOrderParamsDto findOrderParamsDto){
        Double totalcost  = orderService.findTotalCost(findOrderParamsDto);
        if(totalcost==null) totalcost=0.0;

        BaseJson baseJson = new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(totalcost);
        return baseJson;


    }
}
