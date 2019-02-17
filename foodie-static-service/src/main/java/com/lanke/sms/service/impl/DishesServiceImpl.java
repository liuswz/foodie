package com.lanke.sms.service.impl;


import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.TypeToDishDto;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;

import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.utils.BaseUtils;
import com.lanke.sms.dao.DishesDao;
import com.lanke.sms.service.DishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class DishesServiceImpl  implements DishesService {

    @Autowired
    private DishesDao dishesDao;

    public List<DishType> findAllDishType(Integer shopId) {


        return dishesDao.findAllDishType(shopId);

    }
    public List<DishesDto> findAllDishes( Integer shopId){
        return  dishesDao.findAllDishes(shopId);
    }

    public List<TypeToDishDto> getTypeToDish(Integer shopId) {
        List<TypeToDishDto> typeToDishDtoList = new LinkedList<TypeToDishDto>();
        List<DishType> dishTypeList = dishesDao.findAllDishType(shopId);
        for(DishType dishType:dishTypeList){
            TypeToDishDto typeToDishDto = new TypeToDishDto();
            typeToDishDto.setDishType(dishType);
            typeToDishDto.setDishList(dishesDao.getDishByTypeId(dishType.getId()));
            typeToDishDtoList.add(typeToDishDto);
        }
        return typeToDishDtoList;
    }

    public Shop getShopById(Integer id) {
        return dishesDao.getShopById(id);
    }

    public Dish getDishById(Integer id) {
        return dishesDao.getDishById(id);
    }



}
