package com.lanke.sms.dao;

import com.lanke.foodie.dto.DishStaticDto;
import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.entity.MoneyOff;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.simpleEntity.SimpleShop;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishesDao {


    public List<DishType> findAllDishType(@Param("shopId") Integer shopId);
    public Dish getDishById(@Param("id") Integer id);
    public List<DishesDto> findAllDishes(@Param("shopId") Integer shopId);
    public List<DishStaticDto> getDishByTypeId(@Param("typeId") Integer typeId);


    public SimpleShop getShopById(@Param("id") Integer id);
    public ShopDetails getShopDetailsById(@Param("shopId") Integer shopId);
    public List<Dish> getDishByShopId(@Param("shopId") Integer shopId);

    public List<MoneyOff> findMoneyOffByIds(@Param("ids") String ids);
    public List<SearchShop> findAllShopDetails();

}
