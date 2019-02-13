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
       // log.info("bbbbbbbbbbbbbbbbbbbbbbbbbbbb{}",dishType.getName());
        log.info("测试aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa{}", dishesDao.findDishTypeIdByName(1,dishType.getName()));
        if(dishesDao.findDishTypeIdByName(1,dishType.getName())==null||dishesDao.findDishTypeIdByName(1,dishType.getName())==0){
            return dishesDao.addDishType(dishType);
        }else{

            return 0;
        }

    }

    public int addDish(Dish dish) {
        return dishesDao.addDish(dish);
    }

    public List<String> findAllDishType(int shopId) {
        return dishesDao.findAllDishType(shopId);
    }

    public Integer findDishTypeIdByName(String name, int shopId) {
        return dishesDao.findDishTypeIdByName(shopId,name);
    }
}
