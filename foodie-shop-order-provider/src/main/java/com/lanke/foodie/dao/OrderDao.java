package com.lanke.foodie.dao;

import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {
    public Integer addOrder(Order order);
    public Integer addOrderItem(OrderItem orderItem);

    public List<Order> findAllOrder(@Param("shopId")  Integer shopId);
    public List<Order> findOrderByTime(FindOrderParamsDto findOrderParamsDto);
    public List<OrderItemDto> findOrderItem(@Param("orderId") Integer orderId, @Param("shopId") Integer shopId);


}
