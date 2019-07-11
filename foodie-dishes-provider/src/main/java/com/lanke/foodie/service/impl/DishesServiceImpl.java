package com.lanke.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.DishesDao;
import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ProductDto;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.service.DishesService;
import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class DishesServiceImpl  implements DishesService {

    @Autowired
    private DishesDao dishesDao;


    public Integer addDishType(DishType dishType) {

        dishType.setCreateTime(BaseUtils.getTime());

        if(dishesDao.checkDishType(dishType.getShopId(),dishType.getTypeName())==0){
            return dishesDao.addDishType(dishType);
        }else{

            return 0;
        }

    }

    public Integer addDish(Dish dish) {
        dish.setDishSales(0);

        dish.setCreateTime(BaseUtils.getTime());

        if(dishesDao.checkDishes(dish.getName(),dish.getShopId()).size()==0){
            return dishesDao.addDish(dish);
        }else{

            return 0;
        }

    }




    public PageResult findAllDishType(Integer shopId,Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<DishType> page=   (Page<DishType>) dishesDao.findAllDishType(shopId);

        return new PageResult(page.getTotal(), page.getResult());

    }
    public List<DishType> findAllDishType(Integer shopId) {


        return dishesDao.findAllDishType(shopId);

    }



    public PageResult findAllDishes(Integer pageNum, Integer pageSize,Integer shopId, String value){

        PageHelper.startPage(pageNum, pageSize);

        Page<DishesDto> page=   (Page<DishesDto>) dishesDao.findAllDishes(shopId,value);
        return new PageResult(page.getTotal(), page.getResult());

    }


    public Integer delDishTypeById(String ids ) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";
        dishesDao.delDishByTypeId(ids);
        return dishesDao.delDishTypeById(ids);
    }



    public Integer delDishById(String ids) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";
        return dishesDao.delDishById(ids);
    }

    public Integer delDishTypeByShopId(Integer shopId) {
        return dishesDao.delDishTypeByShopId(shopId);
    }

    public Integer delDishByShopId(Integer shopId) {
        return dishesDao.delDishByShopId(shopId);
    }

    public Dish getDishById(Integer id) {
        return dishesDao.getDishById(id);
    }

    public Integer updateDish(Dish dish) {
        dish.setCreateTime(BaseUtils.getTime());
      //  return dishesDao.updateDish(dish);

        List<Integer> ids = dishesDao.checkDishes(dish.getName(),dish.getShopId());
        if(ids.size() > 0 && ids.get(0) != dish.getId()){
            return 0;
        }

        int flag = dishesDao.updateDish(dish);
        // int flag2 = detailDao.updatePay(shop);
        //  int flag = flag1 + flag2;
        return flag;


    }

    public Integer getIfDishByTypeId(DishesDto dishesDto) {
        String ids = dishesDto.getIds();
        ids = "("+ids.substring(0,ids.length() - 1)+")";
        dishesDto.setIds(ids);

        return dishesDao.getIfDishByTypeId(dishesDto);
    }

    public Integer checkDishByShopId(Integer shopId) {
        return dishesDao.checkDishByShopId(shopId);
    }


//    public Integer checkDishType(int shopId, String name) {
//        return dishesDao.checkDishType(shopId,name);
//    }
//
//    public Integer checkDishes(String name) {
//        return dishesDao.checkDishes(name);
//    }

}
