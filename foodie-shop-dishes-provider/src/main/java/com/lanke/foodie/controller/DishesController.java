package com.lanke.foodie.controller;

import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.service.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DishesController {

    @Autowired
    private DishesService dishesService;

    @RequestMapping(value = "/shopdishes/adddishtype",method = RequestMethod.POST)
    public Integer addDishType(@RequestBody DishType dishType  ){

        return dishesService.addDishType(dishType);
    }

    @RequestMapping(value = "/shopdishes/adddish",method = RequestMethod.POST)
    public Integer addDish(@RequestBody Dish dish ){

        return dishesService.addDish(dish);
    }

    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public List<DishType> findAllDishType(@PathVariable("shopId") int shopId) {

        return dishesService.findAllDishType(shopId);
    }

    @RequestMapping(value = "/shopdishes/delDishTypeById/{id}",method = RequestMethod.GET)
    public Integer delDishTypeById(@PathVariable("id") Integer id) {
        return dishesService.delDishTypeById(id);
    }
}
