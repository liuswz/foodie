package com.lanke.foodie.dao;

import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
    public Integer addOrder(Order order);
    public Integer addOrderItem(OrderItem orderItem);
}
