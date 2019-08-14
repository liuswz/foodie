package com.lanke.foodie.service;

import com.lanke.foodie.dto.*;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.userdto.DishForAppointOrder;
import com.lanke.foodie.userdto.DishForGoShopOrder;
import com.lanke.foodie.userdto.ProductForDeliveryOrder;
import com.lanke.foodie.userdto.ProductForGoShopOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {

    public PageResult  findTotalDishOrders(Integer pageNum, Integer pageSize);
    public PageResult  findTotalProductOrders(Integer pageNum, Integer pageSize);
    public PageResult  findProductNotFinishOrders(Integer pageNum, Integer pageSize);

    //商家
    public PageResult findAllDishOrderByShopId(Integer pageNum, Integer pageSize,Integer shopId);
    public PageResult findGoToShopOrderByShopId(Integer pageNum, Integer pageSize, Integer shopId);
    public PageResult findAppointOrderByShopId(Integer pageNum, Integer pageSize, Integer shopId);
    public PageResult findOrderGetProductInShop(Integer pageNum, Integer pageSize, Integer shopId);
    public PageResult findNotFinishOrderGetProductInShop(Integer pageNum, Integer pageSize,Integer shopId);

    public PageResult findDishOrderByTime(Integer pageNum, Integer pageSize,FindOrderParamsDto findOrderParamsDto);
//运营商查询

    public PageResultAndCost findDishOrderByTimeAndValue(Integer pageNum, Integer pageSize,OrderIndexDto orderIndexDto);
    public PageResultAndCost findProductOrderByTimeAndValue(Integer pageNum, Integer pageSize,OrderIndexDto orderIndexDto);
//    public Double findDishCostByTimeAndValue(OrderIndexDto orderIndexDto);
//    public Double findProductCostByTimeAndValue(OrderIndexDto orderIndexDto);


    public List<OrderItem> findOrderItem(Integer orderId);
    public Order findOrderById( Integer orderId);

    public Integer  updateFinishStatus(Integer id);
    public Integer updatOrderIfTranster (FindOrderParamsDto findOrderParamsDto);
    public Double findTotalCost(FindOrderParamsDto findOrderParamsDto);



}
