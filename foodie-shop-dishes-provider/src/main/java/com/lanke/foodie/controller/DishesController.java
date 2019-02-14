package com.lanke.foodie.controller;

import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
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

    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}/{page}/{size}",method = RequestMethod.GET)
    public PageResult findAllDishType(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId) {

        return dishesService.findAllDishType(page,size,shopId);
    }

    @RequestMapping(value = "/shopdishes/delDishTypeById/{id}",method = RequestMethod.DELETE)
    public Integer delDishTypeById(@PathVariable("id") Integer id) {
        return dishesService.delDishTypeById(id);
    }

    @RequestMapping(value = "/shopdishes/delDishById/{id}",method = RequestMethod.DELETE)
    public Integer delDishById(@PathVariable("id") Integer id) {
        return dishesService.delDishById(id);
    }

    @RequestMapping(value = "/shopdishes/getDishById/{id}",method = RequestMethod.GET)
    public Dish getDishById(@PathVariable("id") Integer id) {
        return dishesService.getDishById(id);
    }

    @RequestMapping(value = "/shopdishes/getAllDishes/{shopId}/{page}/{size}",method = RequestMethod.GET)
    public PageResult getAllDishes(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId) {
        return dishesService.findAllDishes(page,size,shopId);
    }
}
