package com.lanke.foodie.dao;

import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.OrderAndShopDto;
import com.lanke.foodie.dto.OrderIndexDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.entity.ShopCar;
import com.lanke.foodie.userdto.DishForAppointOrder;
import com.lanke.foodie.userdto.DishForGoShopOrder;
import com.lanke.foodie.userdto.ProductForDeliveryOrder;
import com.lanke.foodie.userdto.ProductForGoShopOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {

    public List<Order>  findTotalDishOrders();
    public List<Order>  findTotalProductOrders();
    public List<Order>  findProductNotFinishOrders();

    //商家
    public List<Order> findAllDishOrderByShopId(@Param("shopId")  Integer shopId);
    public List<DishForGoShopOrder> findGoToShopOrderByShopId(@Param("shopId")  Integer shopId);
    public List<DishForAppointOrder> findAppointOrderByShopId(@Param("shopId")  Integer shopId);
    public List<ProductForGoShopOrder> findOrderGetProductInShop(@Param("shopId")  Integer shopId);
    public List<ProductForGoShopOrder> findNotFinishOrderGetProductInShop(@Param("shopId")  Integer shopId);

    public List<Order> findDishOrderByTime(FindOrderParamsDto findOrderParamsDto);
//运营商查询

    public List<Order> findDishOrderByTimeAndValue(OrderIndexDto orderIndexDto);
    public List<Order> findProductOrderByTimeAndValue(OrderIndexDto orderIndexDto);
    public Double findDishCostByTimeAndValue(OrderIndexDto orderIndexDto);
    public Double findProductCostByTimeAndValue(OrderIndexDto orderIndexDto);


    public List<OrderItem> findOrderItem(@Param("orderId") Integer orderId);
    public Order findOrderById(@Param("orderId") Integer orderId);

    public Integer  updateFinishStatus(@Param("ifFinish") Integer ifFinish,@Param("id") Integer id);
    public Integer updatOrderIfTranster (FindOrderParamsDto findOrderParamsDto);
    public Double findTotalCost(FindOrderParamsDto findOrderParamsDto);






    public Integer addShopCar(ShopCar shopCar);
}
