package com.lanke.foodie.service;

import com.lanke.foodie.dto.*;
import com.lanke.foodie.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {

    public Integer add_order(OrderAndItemDto orderAndItemDto);
    public PageResult findAllOrder(Integer pageNum, Integer pageSize, Integer shopId);
    public PageResult findOrderByTime(Integer pageNum, Integer pageSize, FindOrderParamsDto findOrderParamsDto);
    public List<OrderItemDto> findOrderItem(Integer orderId, Integer shopId);

    public PageResult findNotFinishOrder(Integer pageNum, Integer pageSize, Integer shopId);
    public Integer  updateOrderStatus( Integer orderStatus, Integer id);
    public Integer  updateDishStatus( Integer dishStatus, Integer id);
    public PageResult findTotalOrders(Integer pageNum, Integer pageSize);
    public PageResultAndCost findOrderByTimeAndValue(Integer pageNum, Integer pageSize,OrderIndexDto orderIndexDto);

    public Integer updatOrderIfTranster (FindOrderParamsDto findOrderParamsDto);
    public Double findTotalCost(FindOrderParamsDto findOrderParamsDto);
}
