package com.lanke.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.DishesDao;
import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
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


    public PageResult findAllDishType(Integer pageNum, Integer pageSize,Integer shopId) {

        PageHelper.startPage(pageNum, pageSize);
        Page<DishType> page=   (Page<DishType>) dishesDao.findAllDishType(shopId);
        return new PageResult(page.getTotal(), page.getResult());

    }
    public PageResult findAllDishes(Integer pageNum, Integer pageSize,Integer shopId){

        PageHelper.startPage(pageNum, pageSize);
        Page<DishesDto> page=   (Page<DishesDto>) dishesDao.findAllDishes(shopId);
        return new PageResult(page.getTotal(), page.getResult());

    }


    public Integer delDishTypeById(Integer id) {
        return dishesDao.delDishTypeById(id);
    }
    public Integer delDishById(Integer id) {
        return dishesDao.delDishById(id);
    }

    public Dish getDishById(Integer id) {
        return dishesDao.getDishById(id);
    }

    public Integer updateDish(Dish dish) {
        dish.setCreateTime(BaseUtils.getTime());

        if(dishesDao.checkDishes(dish.getName())==0){
            return dishesDao.updateDish(dish);
        }else{
            return 0;
        }
    }

//    public Integer checkDishType(int shopId, String name) {
//        return dishesDao.checkDishType(shopId,name);
//    }
//
//    public Integer checkDishes(String name) {
//        return dishesDao.checkDishes(name);
//    }

}
