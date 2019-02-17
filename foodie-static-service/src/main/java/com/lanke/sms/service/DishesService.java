package com.lanke.sms.service;



import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DishesService {
    public List<DishType> findAllDishType( Integer shopId);

    public Dish getDishById(Integer id);
    public List<DishesDto> findAllDishes( Integer shopId);






}
