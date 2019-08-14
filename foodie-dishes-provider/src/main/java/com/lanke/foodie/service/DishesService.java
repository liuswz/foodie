package com.lanke.foodie.service;

import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ProductDto;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.userdto.DishDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DishesService {

    public Integer addDishType(DishType dishType);
    public Integer addDish(Dish dish);

    public PageResult findAllDishType(Integer shopId,Integer pageNum, Integer pageSize );
    public List<DishType>  findAllDishType(Integer shopId);

    public PageResult findAllDishes(Integer pageNum, Integer pageSize, Integer shopId, String value);

    public Integer delDishTypeById(String ids);

    public Integer delDishById(String ids);
    public Integer delDishTypeByShopId(Integer shopId);
    public Integer delDishByShopId(Integer shopId);
    public Dish getDishById(Integer id);

    public Integer updateDish(Dish dish);

    //public Integer getIfDishByTypeId(DishesDto dishesDto);

    public Integer checkDishByShopId(Integer shopId);
    public Integer updateIfHotDish(Integer id,Integer value);
    public List<DishDto> getHotDish(Integer shopId);
    public List<DishDto> getDishByTypeId(Integer shopId,Integer typeId);
}
