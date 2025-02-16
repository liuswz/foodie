//package com.lanke.foodie.service.impl;
//
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.lanke.foodie.dao.OrderDao;
//import com.lanke.foodie.dto.*;
//import com.lanke.foodie.entity.Order;
//import com.lanke.foodie.entity.OrderItem;
//import com.lanke.foodie.enums.*;
//import com.lanke.foodie.service.OrderService;
//import com.lanke.foodie.userdto.DishForAppointOrder;
//import com.lanke.foodie.userdto.DishForGoShopOrder;
//import com.lanke.foodie.userdto.ProductForDeliveryOrder;
//import com.lanke.foodie.userdto.ProductForGoShopOrder;
//import com.lanke.foodie.utils.BaseUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//
//import java.util.List;
//@Slf4j
//@Service
//public class OrderServiceImpl2  {
//
//    @Autowired
//    private OrderDao orderDao;
//
//
//    public PageResult findAllOrder(Integer pageNum, Integer pageSize, Integer shopId) {
//        PageHelper.startPage(pageNum, pageSize);
//        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findAllOrder(shopId),"yyyy-MM-dd HH:mm");
//        return new PageResult(page.getTotal(), page.getResult());
//
//    }
//
//    public PageResult findOrderByTime(Integer pageNum, Integer pageSize, FindOrderParamsDto findOrderParamsDto) {
////        String fromTime = FindOrderParamsDto.getFromTime();
////        String toTime = FindOrderParamsDto.getToTime();
//     //   log.info(FindOrderParamsDto.getFromTime());
//        findOrderParamsDto.setFromTime(findOrderParamsDto.getFromTime()+" 00:00:00");
//        findOrderParamsDto.setToTime(findOrderParamsDto.getToTime()+" 23:59:59");
//        PageHelper.startPage(pageNum, pageSize);
//        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findOrderByTime(findOrderParamsDto),"yyyy-MM-dd HH:mm"); ;
//        return new PageResult(page.getTotal(), page.getResult());
//    }
//
//    public List<OrderItemDto> findOrderItem(Integer orderId, Integer shopId) {
//        return orderDao.findOrderItem(orderId,shopId);
//    }
//
//    public PageResult findNotFinishOrder(Integer pageNum, Integer pageSize, Integer shopId) {
//
//        PageHelper.startPage(pageNum, pageSize);
//        Page<Order> page= (Page<Order>) BaseUtils.transformTime(orderDao.findNotFinishOrder(shopId),"yyyy-MM-dd HH:mm");
//        return new PageResult(page.getTotal(), page.getResult());
//
//    }
//
//    public Integer updateOrderStatus(Integer orderStatus, Integer id) {
//        if(orderDao.updateOrderStatus(orderStatus,id)>0)
//            return Result.SUCCESS.getIndex();
//        else
//            return Result.FAIL.getIndex();
//    }
//
//    public Integer updateDishStatus(Integer dishStatus, Integer id) {
//        if(orderDao.updateDishStatus(dishStatus,id)>0)
//            return Result.SUCCESS.getIndex();
//        else
//            return Result.FAIL.getIndex();
//    }
//
//    public PageResult findTotalOrders(Integer pageNum, Integer pageSize) {
//
//        PageHelper.startPage(pageNum, pageSize);
//        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findTotalOrders(),"yyyy-MM-dd HH:mm");
//        return new PageResult(page.getTotal(), page.getResult());
//
//    }
//
//    public PageResultAndCost findOrderByTimeAndValue(Integer pageNum, Integer pageSize, OrderIndexDto orderIndexDto) {
//
//
//        orderIndexDto.setFromTime(orderIndexDto.getFromTime()+" 00:00:00");
//        orderIndexDto.setToTime(orderIndexDto.getToTime()+" 23:59:59");
//
//        Double totalCost = orderDao.findCostByTimeAndValue(orderIndexDto);
//
//        if(totalCost==null) totalCost=0.0;
//        PageHelper.startPage(pageNum, pageSize);
//        Page<OrderAndShopDto> page=   (Page<OrderAndShopDto>) BaseUtils.transformTimeToOrderAndShopDto(orderDao.findOrderByTimeAndValue(orderIndexDto),"yyyy-MM-dd HH:mm");
//      //  Page<OrderAndShopDto> page=   (Page<OrderAndShopDto>) orderDao.findOrderByTimeAndValue(orderIndexDto);
//        return new PageResultAndCost(page.getTotal(), page.getResult(),totalCost);
//
//    }
//
//    public Integer updatOrderIfTranster(FindOrderParamsDto findOrderParamsDto) {
//        findOrderParamsDto.setFromTime(findOrderParamsDto.getFromTime()+" 00:00:00");
//        findOrderParamsDto.setToTime(findOrderParamsDto.getToTime()+" 23:59:59");
//        return orderDao.updatOrderIfTranster(findOrderParamsDto);
//    }
//
//    public Double findTotalCost(FindOrderParamsDto findOrderParamsDto) {
//        findOrderParamsDto.setFromTime(findOrderParamsDto.getFromTime()+" 00:00:00");
//        findOrderParamsDto.setToTime(findOrderParamsDto.getToTime()+" 23:59:59");
//
//        return orderDao.findTotalCost(findOrderParamsDto);
//    }
//
//
//}
