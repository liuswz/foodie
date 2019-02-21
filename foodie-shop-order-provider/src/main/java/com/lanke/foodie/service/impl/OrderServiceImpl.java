package com.lanke.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.OrderDao;
import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.findOrderParamsDto;
import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.service.OrderService;
import com.lanke.foodie.utils.BaseUtils;
import com.sun.xml.internal.rngom.parse.host.Base;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public Integer add_order(OrderAndItemDto orderAndItemDto) {
        Order order = orderAndItemDto.getOrder();
        order.setCreateTime(BaseUtils.getTime());
        order.setOrderNo(BaseUtils.createOrderNo());



        int orderId = orderDao.addOrder(order);

        if(orderId<1) return Result.FAIL.getIndex();
        List<OrderItem> orderItemList = orderAndItemDto.getOrderItemList();
        for(OrderItem orderItem:orderItemList){
            orderItem.setOrderId(order.getId());

            orderItem.setCreateTime(BaseUtils.getTime());
            int flag = orderDao.addOrderItem(orderItem);
            if(flag<1) return Result.FAIL.getIndex();
        }

        return Result.SUCCESS.getIndex();
    }

    public PageResult findAllOrder(Integer pageNum, Integer pageSize, Integer shopId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page=   (Page<Order>) orderDao.findAllOrder(shopId);
        return new PageResult(page.getTotal(), page.getResult());

    }

    public PageResult findOrderByTime(Integer pageNum, Integer pageSize, findOrderParamsDto findOrderParamsDto) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page=   (Page<Order>) orderDao.findOrderByTime(findOrderParamsDto);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public List<OrderItemDto> findOrderItem(Integer orderId, Integer shopId) {
        return orderDao.findOrderItem(orderId,shopId);
    }


}
