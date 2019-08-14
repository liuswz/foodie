package com.lanke.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.OrderDao;
import com.lanke.foodie.dto.*;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.enums.OrderFinishStatus;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.service.OrderService;
import com.lanke.foodie.userdto.DishForAppointOrder;
import com.lanke.foodie.userdto.DishForGoShopOrder;
import com.lanke.foodie.userdto.ProductForGoShopOrder;
import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    public PageResult findTotalDishOrders(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findTotalDishOrders(),"yyyy-MM-dd HH:mm");
        return new PageResult(page.getTotal(), page.getResult());
    }

    public PageResult findTotalProductOrders(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findTotalProductOrders(),"yyyy-MM-dd HH:mm");
        return new PageResult(page.getTotal(), page.getResult());
    }

    public PageResult findProductNotFinishOrders(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findProductNotFinishOrders(),"yyyy-MM-dd HH:mm");
        return new PageResult(page.getTotal(), page.getResult());
    }

    public PageResult findAllDishOrderByShopId(Integer pageNum, Integer pageSize, Integer shopId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findAllDishOrderByShopId(shopId),"yyyy-MM-dd HH:mm");
        return new PageResult(page.getTotal(), page.getResult());
    }

    public PageResult findGoToShopOrderByShopId(Integer pageNum, Integer pageSize, Integer shopId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<DishForGoShopOrder> page=   (Page<DishForGoShopOrder>) BaseUtils.transformTimeForDishForGoShopOrder(orderDao.findGoToShopOrderByShopId(shopId),"yyyy-MM-dd HH:mm");
        return new PageResult(page.getTotal(), page.getResult());
    }

    public PageResult findAppointOrderByShopId(Integer pageNum, Integer pageSize, Integer shopId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<DishForAppointOrder> page=   (Page<DishForAppointOrder>) BaseUtils.transformTimeForDishForAppointOrder(orderDao.findAppointOrderByShopId(shopId),"yyyy-MM-dd HH:mm");
        return new PageResult(page.getTotal(), page.getResult());
    }

    public PageResult findOrderGetProductInShop(Integer pageNum, Integer pageSize, Integer shopId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ProductForGoShopOrder> page=   (Page<ProductForGoShopOrder>) BaseUtils.transformTimeForProductForGoShopOrder(orderDao.findOrderGetProductInShop(shopId),"yyyy-MM-dd HH:mm");
        return new PageResult(page.getTotal(), page.getResult());
    }

    public PageResult findNotFinishOrderGetProductInShop(Integer pageNum, Integer pageSize, Integer shopId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ProductForGoShopOrder> page=   (Page<ProductForGoShopOrder>) BaseUtils.transformTimeForProductForGoShopOrder(orderDao.findNotFinishOrderGetProductInShop(shopId),"yyyy-MM-dd HH:mm");
        return new PageResult(page.getTotal(), page.getResult());
    }

    public PageResultAndCost findDishOrderByTime(Integer pageNum, Integer pageSize, FindOrderParamsDto findOrderParamsDto) {

        findOrderParamsDto.setFromTime(findOrderParamsDto.getFromTime()+" 00:00:00");
        findOrderParamsDto.setToTime(findOrderParamsDto.getToTime()+" 23:59:59");
        Double totalCost = orderDao.findDishOrderCostByTime(findOrderParamsDto);
        if(totalCost==null) totalCost=0.0;

        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findDishOrderByTime(findOrderParamsDto),"yyyy-MM-dd HH:mm"); ;
        return new PageResultAndCost(page.getTotal(), page.getResult(),totalCost);
    }

    public PageResultAndCost findDishOrderByTimeAndValue(Integer pageNum, Integer pageSize, OrderIndexDto orderIndexDto) {
        orderIndexDto.setFromTime(orderIndexDto.getFromTime()+" 00:00:00");
        orderIndexDto.setToTime(orderIndexDto.getToTime()+" 23:59:59");

        Double totalCost = orderDao.findDishCostByTimeAndValue(orderIndexDto);

        if(totalCost==null) totalCost=0.0;
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findDishOrderByTimeAndValue(orderIndexDto),"yyyy-MM-dd HH:mm");
        //  Page<OrderAndShopDto> page=   (Page<OrderAndShopDto>) orderDao.findOrderByTimeAndValue(orderIndexDto);
        return new PageResultAndCost(page.getTotal(), page.getResult(),totalCost);
    }
    public PageResultAndCost findProductOrderByTimeAndValue(Integer pageNum, Integer pageSize, OrderIndexDto orderIndexDto) {
        orderIndexDto.setFromTime(orderIndexDto.getFromTime()+" 00:00:00");
        orderIndexDto.setToTime(orderIndexDto.getToTime()+" 23:59:59");

        Double totalCost = orderDao.findProductCostByTimeAndValue(orderIndexDto);

        if(totalCost==null) totalCost=0.0;
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page=   (Page<Order>) BaseUtils.transformTime(orderDao.findProductOrderByTimeAndValue(orderIndexDto),"yyyy-MM-dd HH:mm");
        //  Page<OrderAndShopDto> page=   (Page<OrderAndShopDto>) orderDao.findOrderByTimeAndValue(orderIndexDto);
        return new PageResultAndCost(page.getTotal(), page.getResult(),totalCost);
    }



    public List<OrderItem> findOrderItem(Integer orderId) {
        return orderDao.findOrderItem(orderId);
    }

    public Order findOrderById(Integer orderId) {
        return orderDao.findOrderById(orderId);
    }

    public Integer updateFinishStatus( Integer id) {
        return orderDao.updateFinishStatus(OrderFinishStatus.HadFinish.getIndex(),id);
    }

    public Integer updateIfGoodHadReach(Integer id) {
        return orderDao.updateIfGoodHadReach(id);
    }

    public Integer updatOrderIfTranster(FindOrderParamsDto findOrderParamsDto) {
        return orderDao.updatOrderIfTranster(findOrderParamsDto);
    }

    public Double findTotalCost(FindOrderParamsDto findOrderParamsDto) {
        return orderDao.findTotalCost(findOrderParamsDto);
    }
}
