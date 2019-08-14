package com.lanke.foodie.service;

import com.lanke.foodie.dto.*;
import com.lanke.foodie.entity.Order;

import com.lanke.foodie.simpleEntity.SimpleOrderItem;
import com.lanke.foodie.userdto.*;


import java.util.List;

public interface UserOrderService {

    public Integer add_order(OrderAndItemDto<Order> orderAndItemDto);
    public Integer addDishForAppointOrder(OrderAndItemDto<DishForAppointOrder> order);
    public Integer addDishForGoShopOrder(OrderAndItemDto<DishForGoShopOrder> order);
    public Integer addProductForDeliveryOrder(OrderAndItemDto<ProductForDeliveryOrder> order);
    public Integer addProductForGoShopOrder(OrderAndItemDto<ProductForGoShopOrder> order);

    public PageResults findAllSimpleOrder(Integer pageNum, Integer pageSize,Integer userId);
    public PageResults findAllNotPayOrder(Integer pageNum, Integer pageSize,Integer userId);
    public PageResults findAllHadPayOrder(Integer pageNum, Integer pageSize,Integer userId);
    public PageResults findAllAppointOrder(Integer pageNum, Integer pageSize,Integer userId);
    public Order getOrderDetails(Integer id);
    public List<SimpleOrderItem> getOrderItems(Integer orderId);
    public Integer updatePayStatus(Integer id,Integer payWay);
    public Integer updateFinishStatus(Integer id);


}
