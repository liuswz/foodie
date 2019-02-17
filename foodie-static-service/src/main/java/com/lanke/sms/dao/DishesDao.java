package com.lanke.sms.dao;

import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishesDao {


    public List<DishType> findAllDishType(@Param("shopId") Integer shopId);

    public Dish getDishById(@Param("id") Integer id);
    public List<DishesDto> findAllDishes(@Param("shopId") Integer shopId);

    public List<Dish> getDishByTypeId(@Param("typeId") Integer typeId);

    public Shop getShopById(@Param("username") Integer shopId);

}
