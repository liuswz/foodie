package com.lanke.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.OrderDao;
import com.lanke.foodie.dao.UserOrderDao;
import com.lanke.foodie.dto.*;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.enums.*;
import com.lanke.foodie.service.OrderService;
import com.lanke.foodie.service.UserOrderService;
import com.lanke.foodie.simpleEntity.SimpleOrder;
import com.lanke.foodie.simpleEntity.SimpleOrderItem;
import com.lanke.foodie.userdto.*;
import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderDao orderDao;

    @Transactional
    public Integer add_order(OrderAndItemDto<Order> orderAndItemDto) {
        Order order = orderAndItemDto.getOrder();
        order.setCreateTime(BaseUtils.getTime());
        order.setOrderNo(BaseUtils.createOrderNo());

        order.setIfTransfer(TransferStatus.NotFinish.getIndex());
//        order.setOrderStatus(PayStatus.NotPay.getIndex());
//        order.setGoodStatus(DishStatus.NotFinish.getIndex());

        int orderFlag = orderDao.addOrder(order);

        if(orderFlag<1) return Result.FAIL.getIndex();
        List<OrderItem> orderItemList = orderAndItemDto.getOrderItemList();
        for(OrderItem orderItem:orderItemList){
            orderItem.setOrderId(order.getId());
            orderItem.setCreateTime(BaseUtils.getTime());
        }
        int flag = orderDao.addOrderItem(orderItemList);
        if(flag<1) return Result.FAIL.getIndex();
        return Result.SUCCESS.getIndex();
    }
    @Transactional
    public Integer addDishForAppointOrder(OrderAndItemDto<DishForAppointOrder> order) {
        DishForAppointOrder dishOrder = order.getOrder();
        dishOrder.setCreateTime(BaseUtils.getTime());
        dishOrder.setOrderNo(BaseUtils.createOrderNo());
        dishOrder.setIfTransfer(TransferStatus.NotFinish.getIndex());
        dishOrder.setGoodType(GoodType.Dish.getIndex());
        dishOrder.setIfFinish(OrderFinishStatus.NotFinish.getIndex());
        dishOrder.setGetWay(GoodGetWayType.Appoint.getIndex());
        dishOrder.setIfComment(CommentStatus.NotComment.getIndex());

        int orderFlag = orderDao.addDishForAppointOrder(dishOrder);

        if(orderFlag<1){
            return Result.FAIL.getIndex();
        }else{
            orderDao.updateShopSales(dishOrder.getGoodId(),dishOrder.getNum());
        }
        List<OrderItem> orderItemList = order.getOrderItemList();
        for(OrderItem orderItem:orderItemList){
            orderItem.setOrderId(dishOrder.getId());
            orderItem.setGoodType(GoodType.Dish.getIndex());
            orderItem.setCreateTime(BaseUtils.getTime());
            orderDao.updateDishSales(dishOrder.getGoodId(),orderItem.getGoodNum());
        }

        int flag = orderDao.addOrderItem(orderItemList);
        if(flag<1) return Result.FAIL.getIndex();
        return Result.SUCCESS.getIndex();
    }
    @Transactional
    public Integer addDishForGoShopOrder(OrderAndItemDto<DishForGoShopOrder> order) {
        DishForGoShopOrder dishOrder = order.getOrder();
        dishOrder.setCreateTime(BaseUtils.getTime());
        dishOrder.setOrderNo(BaseUtils.createOrderNo());
        dishOrder.setIfTransfer(TransferStatus.NotFinish.getIndex());
        dishOrder.setGoodType(GoodType.Dish.getIndex());
        dishOrder.setIfFinish(OrderFinishStatus.NotFinish.getIndex());
        dishOrder.setGetWay(GoodGetWayType.GoToShop.getIndex());
        dishOrder.setIfComment(CommentStatus.NotComment.getIndex());
        int orderFlag = orderDao.addDishForGoShopOrder(dishOrder);

        if(orderFlag<1){
            return Result.FAIL.getIndex();
        }else{
            orderDao.updateShopSales(dishOrder.getGoodId(),dishOrder.getNum());
        }

        List<OrderItem> orderItemList = order.getOrderItemList();
        for(OrderItem orderItem:orderItemList){
            orderItem.setOrderId(dishOrder.getId());
            orderItem.setGoodType(GoodType.Dish.getIndex());
            orderItem.setCreateTime(BaseUtils.getTime());
            orderDao.updateDishSales(dishOrder.getGoodId(),orderItem.getGoodNum());
        }
        int flag = orderDao.addOrderItem(orderItemList);
        if(flag<1) return Result.FAIL.getIndex();
        return Result.SUCCESS.getIndex();
    }
    @Transactional
    public Integer addProductForDeliveryOrder(OrderAndItemDto<ProductForDeliveryOrder> order) {
        ProductForDeliveryOrder dishOrder = order.getOrder();
        dishOrder.setCreateTime(BaseUtils.getTime());
        dishOrder.setOrderNo(BaseUtils.createOrderNo());
        dishOrder.setIfTransfer(TransferStatus.NotFinish.getIndex());
        dishOrder.setGoodType(GoodType.Product.getIndex());
        dishOrder.setIfFinish(OrderFinishStatus.NotFinish.getIndex());
        dishOrder.setGetWay(GoodGetWayType.Delivery.getIndex());
        dishOrder.setIfComment(CommentStatus.NotComment.getIndex());
        int orderFlag = orderDao.addProductForDeliveryOrder(dishOrder);

        if(orderFlag<1){
            return Result.FAIL.getIndex();
        }

        List<OrderItem> orderItemList = order.getOrderItemList();
        for(OrderItem orderItem:orderItemList){
            orderItem.setOrderId(dishOrder.getId());
            orderItem.setGoodType(GoodType.Dish.getIndex());
            orderItem.setCreateTime(BaseUtils.getTime());
            orderDao.updateProductSales(orderItem.getGoodId(),orderItem.getGoodNum());
        }
        int flag = orderDao.addOrderItem(orderItemList);
        if(flag<1) return Result.FAIL.getIndex();
        return Result.SUCCESS.getIndex();
    }
    @Transactional
    public Integer addProductForGoShopOrder(OrderAndItemDto<ProductForGoShopOrder> order) {
        ProductForGoShopOrder dishOrder = order.getOrder();
        dishOrder.setCreateTime(BaseUtils.getTime());
        dishOrder.setOrderNo(BaseUtils.createOrderNo());
        dishOrder.setIfTransfer(TransferStatus.NotFinish.getIndex());
        dishOrder.setGoodType(GoodType.Product.getIndex());
        dishOrder.setIfFinish(OrderFinishStatus.NotFinish.getIndex());
        dishOrder.setGetWay(GoodGetWayType.GetInShop.getIndex());
        dishOrder.setIfComment(CommentStatus.NotComment.getIndex());
        dishOrder.setIfGoodHadReach(GoodReachStatus.NotReach.getIndex());
        int orderFlag = orderDao.addProductForGoShopOrder(dishOrder);

        if(orderFlag<1){
            return Result.FAIL.getIndex();
        }

        List<OrderItem> orderItemList = order.getOrderItemList();
        for(OrderItem orderItem:orderItemList){
            orderItem.setOrderId(dishOrder.getId());
            orderItem.setGoodType(GoodType.Dish.getIndex());
            orderItem.setCreateTime(BaseUtils.getTime());
            orderDao.updateProductSales(orderItem.getGoodId(),orderItem.getGoodNum());
        }
        int flag = orderDao.addOrderItem(orderItemList);
        if(flag<1) return Result.FAIL.getIndex();
        return Result.SUCCESS.getIndex();
    }

    public PageResults findAllSimpleOrder(Integer pageNum, Integer pageSize,Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<SimpleOrder> page=   (Page<SimpleOrder>) BaseUtils.transformTimeForSimpleOrder(orderDao.findAllSimpleOrder(userId));
        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public PageResults findAllNotPayOrder(Integer pageNum, Integer pageSize,Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<SimpleOrder> page=   (Page<SimpleOrder>)BaseUtils.transformTimeForSimpleOrder(orderDao.findAllNotPayOrder(userId));
        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public PageResults findAllHadPayOrder(Integer pageNum, Integer pageSize,Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<SimpleOrder> page=   (Page<SimpleOrder>) BaseUtils.transformTimeForSimpleOrder(orderDao.findAllHadPayOrder(userId));
        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public PageResults findAllAppointOrder(Integer pageNum, Integer pageSize,Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<SimpleOrder> page=   (Page<SimpleOrder>) BaseUtils.transformTimeForSimpleOrder(orderDao.findAllAppointOrder(userId));
        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;

    }

    public Order getOrderDetails(Integer id) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        Order order = orderDao.getOrderDetails(id);
        Date d = null;
        try {
            d = sdf1.parse(order.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setCreateTime(ft.format(d));
        return order;
    }

    public List<SimpleOrderItem> getOrderItems(Integer orderId) {
        return orderDao.getOrderItems(orderId);
    }

    public Integer updatePayStatus(Integer id, Integer payWay) {
        return orderDao.updatePayStatus(id,PayStatus.HadPay.getIndex(),payWay);
    }

    public Integer updateFinishStatus(Integer id) {
        return orderDao.updateFinishStatus(id,OrderFinishStatus.HadFinish.getIndex());
    }


}
