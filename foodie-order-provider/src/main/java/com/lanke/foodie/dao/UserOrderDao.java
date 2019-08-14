package com.lanke.foodie.dao;

import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.OrderAndShopDto;
import com.lanke.foodie.dto.OrderIndexDto;
import com.lanke.foodie.dto.OrderItemDto;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.entity.ShopCar;
import com.lanke.foodie.simpleEntity.SimpleOrder;
import com.lanke.foodie.simpleEntity.SimpleOrderItem;
import com.lanke.foodie.userdto.DishForAppointOrder;
import com.lanke.foodie.userdto.DishForGoShopOrder;
import com.lanke.foodie.userdto.ProductForDeliveryOrder;
import com.lanke.foodie.userdto.ProductForGoShopOrder;
import com.lanke.foodie.userparams.ProductMarkDetail;
import com.lanke.foodie.userparams.ShopMarkDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserOrderDao {
    public Integer addOrder(Order order);
    public Integer addOrderItem(List<OrderItem> list);
    public Integer addDishForAppointOrder(DishForAppointOrder order);
    public Integer addDishForGoShopOrder(DishForGoShopOrder order);
    public Integer addProductForDeliveryOrder(ProductForDeliveryOrder order);
    public Integer addProductForGoShopOrder(ProductForGoShopOrder order);

    public Integer updateShopSales(@Param("id") Integer id, @Param("num") Integer num);
    public Integer updateDishSales(@Param("id") Integer id, @Param("num") Integer num);
    public Integer updateProductSales(@Param("id") Integer id, @Param("num") Integer num);

    public List<SimpleOrder> findAllSimpleOrder(@Param("userId") Integer userId);
    public List<SimpleOrder> findAllNotPayOrder(@Param("userId") Integer userId);
    public List<SimpleOrder> findAllHadPayOrder(@Param("userId") Integer userId);
    public List<SimpleOrder> findAllAppointOrder(@Param("userId") Integer userId);
    public Order getOrderDetails(@Param("id") Integer id);
    public List<SimpleOrderItem> getOrderItems(@Param("orderId") Integer orderId);

    public Integer updatePayStatus(@Param("id") Integer id,@Param("payStatus") Integer payStatus, @Param("payWay") Integer payWay);
    public Integer updateFinishStatus(@Param("id") Integer id,@Param("ifFinish") Integer ifFinish);

    public Integer updateCommentStatus(@Param("id") Integer id);
    public Integer updateShopMark(@Param("shopId") Integer shopId,@Param("shopMark") Double shopMark);
    public ShopMarkDetail getShopMarkDetail(@Param("shopId") Integer shopId);
    public Integer updateProductMark(@Param("id") Integer id,@Param("productMark") Double productMark);
    public ProductMarkDetail getProductMarkDetail(@Param("id") Integer id);
}
