package com.lanke.foodie.service;

import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DishesService {

    public int addDishType(DishType dishType);

    public int addDish(Dish dish);
    public PageResult findAllDishType(Integer pageNum, Integer pageSize, Integer shopId);
    public PageResult findAllDishes(Integer pageNum, Integer pageSize, Integer shopId);

    public Integer delDishTypeById(Integer id);
    public Integer delDishById(Integer id);

    public Dish getDishById(Integer id);


//    public Integer checkDishType(int shopId,String name);
//    public Integer checkDishes(String name);
}
