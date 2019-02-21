package com.lanke.foodie.service.impl;

import com.lanke.foodie.dao.OrderDao;
import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.service.OrderService;
import com.lanke.foodie.utils.BaseUtils;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
            orderItem.setOrderId(orderId);
            orderItem.setCreateTime(BaseUtils.getTime());
            int flag = orderDao.addOrderItem(orderItem);
            if(flag<1) return Result.FAIL.getIndex();
        }

        return Result.SUCCESS.getIndex();
    }


}
