package com.lanke.foodie.service;

import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.findOrderParamsDto;
import com.lanke.foodie.entity.Order;

import java.util.List;

public interface OrderService {

    public Integer add_order(OrderAndItemDto orderAndItemDto);
    public PageResult findAllOrder(Integer pageNum, Integer pageSize, Integer shopId);
    public PageResult findOrderByTime(Integer pageNum, Integer pageSize,findOrderParamsDto findOrderParamsDto);
    public List<OrderItemDto> findOrderItem(Integer orderId, Integer shopId);
}
