package com.lanke.foodie.dao;

import com.lanke.foodie.dto.DishesDto;

import com.lanke.foodie.entity.*;
import com.lanke.foodie.userdto.DishDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishesDao {

    public Integer addDishType(DishType dishType);
    public Integer addDish(Dish dish);


    public List<DishType> findAllDishType(@Param("shopId") Integer shopId);


    public Integer checkDishType(@Param("shopId") Integer shopId,@Param("typeName") String typeName);
    public List<Integer> checkDishes(@Param("name") String name,@Param("shopId") Integer shopId);
    public Integer checkDishByShopId(@Param("shopId") Integer shopId);

    public Integer delDishTypeById(@Param("ids") String ids);


    public Integer delDishById(@Param("ids") String ids);
    public Integer delDishByTypeId(@Param("ids") String ids);
    public Integer delDishTypeByShopId(@Param("shopId") Integer shopId);
    public Integer delDishByShopId(@Param("shopId") Integer shopId);

    public Dish getDishById(@Param("id") Integer id);
    public List<DishesDto> findAllDishes(@Param("shopId") Integer shopId,@Param("value") String value);

    public Integer updateDish(Dish dish);

    public Integer getIfDishByTypeId(@Param("ids") String ids);
    public Integer updateIfHotDish(@Param("id") Integer id,@Param("value") Integer value);
    public Integer getIfHotDish(@Param("id") Integer id);

    public List<DishDto> getHotDish(@Param("shopId") Integer shopId);

    public List<DishDto> getDishByTypeId(@Param("shopId") Integer shopId,@Param("typeId") Integer typeId);
}
