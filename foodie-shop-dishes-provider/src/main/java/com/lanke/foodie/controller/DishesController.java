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
    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public List<DishType> findAllDishType( @PathVariable("shopId") Integer shopId) {

        return dishesService.findAllDishType(shopId);
    }
    @RequestMapping(value = "/shopdishes/delDishTypeById/{ids}",method = RequestMethod.GET)
    public Integer delDishTypeById(@PathVariable("ids") String ids ) {
        return dishesService.delDishTypeById(ids);
    }

    @RequestMapping(value = "/shopdishes/delDishById/{ids}",method = RequestMethod.GET)
    public Integer delDishById(@PathVariable("ids") String ids ) {
        return dishesService.delDishById(ids);
    }

    @RequestMapping(value = "/shopdishes/getDishById/{id}",method = RequestMethod.GET)
    public Dish getDishById(@PathVariable("id") Integer id) {
        return dishesService.getDishById(id);
    }

    @RequestMapping(value = "/shopdishes/getAllDishes/{shopId}/{page}/{size}/{value}",method = RequestMethod.GET)
    public PageResult getAllDishes(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId,@PathVariable("value") String value) {
        return dishesService.findAllDishes(page,size,shopId,value);
    }

    @RequestMapping(value = "/shopdishes/updateDish",method = RequestMethod.POST)
    public Integer updateDish(@RequestBody Dish dish ){
        return dishesService.updateDish(dish);
    }
}
