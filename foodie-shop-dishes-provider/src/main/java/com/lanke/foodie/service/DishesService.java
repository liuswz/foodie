package com.lanke.foodie.service;

import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;

import java.util.List;

public interface DishesService {

    public int addDishType(DishType dishType);
    public int addDish(Dish dish);

    public List<String> findAllDishType(int shopId);
    public Integer findDishTypeIdByName(String name,int shopId);
}
