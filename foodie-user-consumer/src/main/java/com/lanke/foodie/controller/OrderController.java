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
import com.lanke.foodie.service.VoucherService;
import com.lanke.foodie.simpleEntity.SimpleOrderItem;
import com.lanke.foodie.userdto.*;
import com.lanke.foodie.userparams.OrderAddParam;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private VoucherService voucherService;

    @HystrixCommand(fallbackMethod = "processHystrix_Get2")
    @RequestMapping(value = "/consumer/order/addOrder",method = RequestMethod.POST)
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
//    @RequestMapping(value = "/order/addDishForAppointOrder",method = RequestMethod.POST)
//    public BaseJson addDishForAppointOrder(OrderAddParam<DishForAppointOrder> param){
//        List<OrderItem> orderItem = (List<OrderItem>)JSONArray.parseArray(param.getOrderItemList(), OrderItem.class);
//        OrderAndItemDto<DishForAppointOrder> orderAndItemDto=new OrderAndItemDto<DishForAppointOrder>();
//        orderAndItemDto.setOrder(param.getOrder());
//        orderAndItemDto.setOrderItemList(orderItem);
//
//        BaseJson baseJson = new BaseJson();
//        int flag = orderService.addDishForAppointOrder(orderAndItemDto);
//        if(flag == Result.SUCCESS.getIndex()){
//            baseJson.setCode(flag);
//            baseJson.setMessage("成功");
//            baseJson.setResult("添加成功");
//            Integer voucherId= param.getVoucherId();
//            if(voucherId!=null){
//                voucherService.delVoucherForUserById(voucherId);
//            }
//        }else {
//
//            baseJson.setCode(flag);
//            baseJson.setMessage("失败");
//            baseJson.setResult("添加失败");
//
//
//        }
//        return baseJson;
//
//
//    }
//    @RequestMapping(value = "/order/addDishForGoShopOrder",method = RequestMethod.POST)
//    public BaseJson addDishForGoShopOrder(@RequestBody  OrderAddParam<DishForGoShopOrder> param  ){
//
//        List<OrderItem> orderItem = (List<OrderItem>)JSONArray.parseArray(param.getOrderItemList(), OrderItem.class);
//        OrderAndItemDto<DishForGoShopOrder> orderAndItemDto=new OrderAndItemDto<>();
//        orderAndItemDto.setOrder(param.getOrder());
//        orderAndItemDto.setOrderItemList(orderItem);
//        log.info(param.getOrderItemList()+"------------------");
//        log.info(orderItem.get(0).getGoodName()+"------------------");
//        log.info(param.getOrder().getShopName()+"*******************");
//        BaseJson baseJson = new BaseJson();
//        int flag = orderService.addDishForGoShopOrder(orderAndItemDto);
//        if(flag == Result.SUCCESS.getIndex()){
//            baseJson.setCode(flag);
//            baseJson.setMessage("成功");
//            baseJson.setResult("添加成功");
//            Integer voucherId= param.getVoucherId();
//            if(voucherId!=null){
//                voucherService.delVoucherForUserById(voucherId);
//            }
//        }else {
//
//            baseJson.setCode(flag);
//            baseJson.setMessage("失败");
//            baseJson.setResult("添加失败");
//
//
//        }
//        return baseJson;
//
//
//
//    }
    @RequestMapping(value = "/order/addDishForAppointOrder",method = RequestMethod.POST)
    public BaseJson addDishForAppointOrder(@RequestBody DishForAppointOrder order , String orderItemList ,Integer voucherId){
        List<OrderItem> orderItem = (List<OrderItem>)JSONArray.parseArray(orderItemList, OrderItem.class);
        OrderAndItemDto<DishForAppointOrder> orderAndItemDto=new OrderAndItemDto<DishForAppointOrder>();
        orderAndItemDto.setOrder(order);
        orderAndItemDto.setOrderItemList(orderItem);

        BaseJson baseJson = new BaseJson();
        int flag = orderService.addDishForAppointOrder(orderAndItemDto);
        if(flag == Result.SUCCESS.getIndex()){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
            if(voucherId!=null){
                voucherService.delVoucherForUserById(voucherId);
            }
        }else {

            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("添加失败");


        }
        return baseJson;


    }
    @RequestMapping(value = "/order/addDishForGoShopOrder",method = RequestMethod.POST)
    public BaseJson addDishForGoShopOrder(@RequestBody DishForGoShopOrder order , String orderItemList ,Integer voucherId ){

        List<OrderItem> orderItem = (List<OrderItem>)JSONArray.parseArray(orderItemList, OrderItem.class);
        OrderAndItemDto<DishForGoShopOrder> orderAndItemDto=new OrderAndItemDto<>();
        orderAndItemDto.setOrder(order);
        orderAndItemDto.setOrderItemList(orderItem);

        BaseJson baseJson = new BaseJson();
        int flag = orderService.addDishForGoShopOrder(orderAndItemDto);
        if(flag == Result.SUCCESS.getIndex()){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
            if(voucherId!=null){
                voucherService.delVoucherForUserById(voucherId);
            }
        }else {

            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("添加失败");


        }
        return baseJson;

    }


    @RequestMapping(value = "/order/addProductForDeliveryOrder",method = RequestMethod.POST)
    public BaseJson addProductForDeliveryOrder(@RequestBody ProductForDeliveryOrder order , String orderItemList  ){
        List<OrderItem> orderItem = (List<OrderItem>)JSONArray.parseArray(orderItemList, OrderItem.class);
        OrderAndItemDto<ProductForDeliveryOrder> orderAndItemDto=new OrderAndItemDto<>();
        orderAndItemDto.setOrder(order);
        orderAndItemDto.setOrderItemList(orderItem);

        BaseJson baseJson = new BaseJson();
        int flag = orderService.addProductForDeliveryOrder(orderAndItemDto);
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
    @RequestMapping(value = "/order/addProductForGoShopOrder",method = RequestMethod.POST)
    public BaseJson addProductForGoShopOrder(@RequestBody ProductForGoShopOrder order , String orderItemList ){
        List<OrderItem> orderItem = (List<OrderItem>)JSONArray.parseArray(orderItemList, OrderItem.class);
        OrderAndItemDto<ProductForGoShopOrder> orderAndItemDto=new OrderAndItemDto<>();
        orderAndItemDto.setOrder(order);
        orderAndItemDto.setOrderItemList(orderItem);

        BaseJson baseJson = new BaseJson();
        int flag = orderService.addProductForGoShopOrder(orderAndItemDto);
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

//    @RequestMapping(value = "/consumer/order/findAllOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
//    public PageResult findAllOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId) {
//
//        return orderService.findAllOrder(page,size,shopId);
//    }
//    @RequestMapping(value = "/consumer/order/findOrderByTime",method = RequestMethod.POST)
//    public PageResult findOrderByTime(  @RequestParam("page") Integer page,
//                                        @RequestParam("size") Integer size,
//                                        FindOrderParamsDto findOrderParamsDto) {
//log.info(page+size+findOrderParamsDto.getFromTime()+findOrderParamsDto.getToTime());
//        return orderService.findOrderByTime(page,size,findOrderParamsDto);
//    }
//    @RequestMapping(value = "/consumer/order/findOrderItem/{orderId}/{shopId}",method = RequestMethod.GET)
//    public List<OrderItemDto> findOrderItem(@PathVariable("orderId") Integer orderId, @PathVariable("shopId") Integer shopId) {
//
//        return orderService.findOrderItem(orderId,shopId);
//    }


//    @RequestMapping(value = "/consumer/order/findNotFinishOrder/{page}/{size}/{shopId}",method = RequestMethod.GET)
//    public PageResult findNotFinishOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId){
//        return orderService.findNotFinishOrder(page,size,shopId);
//    }
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    @RequestMapping(value = "/consumer/order/updateOrderStatus/{orderStatus}/{id}",method = RequestMethod.GET)
    public BaseJson  updateOrderStatus( @PathVariable("orderStatus") Integer orderStatus,@PathVariable("id") Integer id){

        int flag = orderService.updateOrderStatus(orderStatus,id);

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

    public BaseJson processHystrix_Get( @PathVariable("orderStatus") Integer orderStatus,@PathVariable("id") Integer id)
    {
        BaseJson baseJson = new BaseJson();
        baseJson.setMessage("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand") ;
        return baseJson;

    }
    public BaseJson processHystrix_Get2( Order order , String orderItemList)
    {
        BaseJson baseJson = new BaseJson();
        baseJson.setMessage("添加订单失败") ;
        return baseJson;

    }



    @RequestMapping(value = "/order/findAllSimpleOrder/{page}/{size}/{userId}/{flag}",method = RequestMethod.GET)
    public PageResults findAllSimpleOrder(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("userId")  Integer userId,@PathVariable("flag")  Integer flag){
        PageResults pageResults = null;
        switch (flag){
            case 0:
                 pageResults =orderService.findAllSimpleOrder(page,size,userId);
                break;
            case 1:
                 pageResults =orderService.findAllNotPayOrder(page,size,userId);
                break;
            case 2:
                 pageResults =orderService.findAllHadPayOrder(page,size,userId);
                break;
            case 3:
                 pageResults =orderService.findAllAppointOrder(page,size,userId);
                break;
        }

        pageResults.setCode(Result.SUCCESS.getIndex());
        pageResults.setMessage("成功");
        return  pageResults;

    }
//    @RequestMapping(value = "/order/findAllNotPayOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
//    public PageResults findAllNotPayOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId){
//        PageResults pageResults =orderService.findAllNotPayOrder(page,size,userId);
//        pageResults.setCode(Result.SUCCESS.getIndex());
//        pageResults.setMessage("成功");
//        return  pageResults;
//    }
//    @RequestMapping(value = "/order/findAllHadPayOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
//    public PageResults findAllHadPayOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId){
//        PageResults pageResults =orderService.findAllHadPayOrder(page,size,userId);
//        pageResults.setCode(Result.SUCCESS.getIndex());
//        pageResults.setMessage("成功");
//        return  pageResults;
//
//    }
//    @RequestMapping(value = "/order/findAllAppointOrder/{page}/{size}/{userId}",method = RequestMethod.GET)
//    public PageResults findAllAppointOrder(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("userId")  Integer userId){
//        PageResults pageResults =orderService.findAllAppointOrder(page,size,userId);
//        pageResults.setCode(Result.SUCCESS.getIndex());
//        pageResults.setMessage("成功");
//        return  pageResults;
//    }

    @RequestMapping(value = "/order/getOrderDetails/{id}",method = RequestMethod.GET)
    public BaseJson getOrderDetails(@PathVariable("id")  Integer id){
        BaseJson baseJson = new BaseJson();
        baseJson.setCode( Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(orderService.getOrderDetails(id));
       return baseJson;
    }

    @RequestMapping(value = "/order/getOrderItems/{orderId}",method = RequestMethod.GET)
    public BaseJson getOrderItems(@PathVariable("orderId")  Integer orderId) {
        BaseJson baseJson = new BaseJson();
        baseJson.setCode( Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(orderService.getOrderItems(orderId));
        return baseJson;

    }
    @RequestMapping(value = "/order/updatePayStatus",method = RequestMethod.POST)
    public BaseJson updatePayStatus(@RequestParam("id") Integer id,@RequestParam("payWay") Integer payWay){
        int flag =  orderService.updatePayStatus(id,payWay);
        BaseJson baseJson = new BaseJson();
        if(flag >0){
            baseJson.setCode( Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");
        }else {
            baseJson.setCode( Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("更改失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/order/updateFinishStatus",method = RequestMethod.POST)
    public BaseJson updateFinishStatus(@RequestParam("id") Integer id){

        int flag = orderService.updateFinishStatus(id);
        BaseJson baseJson = new BaseJson();
        if(flag >0){
            baseJson.setCode( Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");
        }else {
            baseJson.setCode( Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("更改失败");
        }
        return baseJson;

    }
}
