package com.lanke.foodie.dao;

import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishesDao {

    public int addDishType(DishType dishType);
    public int addDish(Dish dish);

    public List<DishType> findAllDishType(int shopId);
    public Integer checkDishType(@Param("shopId") int shopId,@Param("name") String name);
    public Integer checkDishes(@Param("name") String name);

}
