package com.lanke.foodie.dao;

import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishesDao {

    public int addDishType(DishType dishType);
    public int addDish(Dish dish);

    public List<DishType> findAllDishType(@Param("shopId") Integer shopId);
    public Integer checkDishType(@Param("shopId") Integer shopId,@Param("name") String name);
    public Integer checkDishes(@Param("name") String name);

    public Integer delDishTypeById(@Param("id") Integer id);
    public Integer delDishById(@Param("id") Integer id);

    public Dish getDishById(@Param("id") Integer id);
    public List<DishesDto> findAllDishes(@Param("shopId") Integer shopId);

    public Integer updateDish(Dish dish);

}
