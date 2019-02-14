package com.lanke.foodie.service.impl;

import com.lanke.foodie.dao.DishesDao;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.service.DishesService;
import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class DishesServiceImpl  implements DishesService {

    @Autowired
    private DishesDao dishesDao;


    public int addDishType(DishType dishType) {

        dishType.setCreateTime(BaseUtils.getTime());

        if(dishesDao.checkDishType(1,dishType.getTypeName())==0){
            return dishesDao.addDishType(dishType);
        }else{

            return 0;
        }

    }

    public int addDish(Dish dish) {

        dish.setCreateTime(BaseUtils.getTime());

        if(dishesDao.checkDishes(dish.getName())==0){
            return dishesDao.addDish(dish);
        }else{

            return 0;
        }

    }

    public List<DishType> findAllDishType(int shopId) {
        return dishesDao.findAllDishType(shopId);
    }

    public Integer delDishTypeById(Integer id) {
        return dishesDao.delDishTypeById(id);
    }

    public Dish getDishById(Integer id) {
        return dishesDao.getDishById(id);
    }

//    public Integer checkDishType(int shopId, String name) {
//        return dishesDao.checkDishType(shopId,name);
//    }
//
//    public Integer checkDishes(String name) {
//        return dishesDao.checkDishes(name);
//    }

}
