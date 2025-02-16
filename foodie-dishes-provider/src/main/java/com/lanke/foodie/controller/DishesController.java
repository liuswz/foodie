package com.lanke.foodie.controller;

import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.service.DishesService;
import com.lanke.foodie.userdto.DishDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
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
    public PageResult findAllDishType(@PathVariable("shopId") Integer shopId,@PathVariable("page") Integer page, @PathVariable("size") Integer size) {

        return dishesService.findAllDishType(shopId,page,size);
    }
    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public List<DishType> findAllDishType( @PathVariable("shopId") Integer shopId) {
        return dishesService.findAllDishType(shopId);
    }
    @RequestMapping(value = "/shopdishes/checkDishByShopId/{shopId}",method = RequestMethod.GET)
    public Integer checkDishByShopId(@PathVariable("shopId") Integer shopId) {
        return dishesService.checkDishByShopId(shopId);
    }
    @RequestMapping(value = "/shopdishes/delDishTypeById/{ids}",method = RequestMethod.GET)
    public Integer delDishTypeById(@PathVariable("ids") String ids ) {

        return dishesService.delDishTypeById(ids);
    }

    @RequestMapping(value = "/shopdishes/delDishById/{ids}",method = RequestMethod.GET)
    public Integer delDishById(@PathVariable("ids") String ids ) {
        return dishesService.delDishById(ids);
    }

    @RequestMapping(value = "/shopdishes/delDishTypeByShopId/{shopId}",method = RequestMethod.GET)
    public Integer delDishTypeByShopId(@PathVariable("shopId") Integer shopId ) {
        return dishesService.delDishTypeByShopId(shopId);
    }
    @RequestMapping(value = "/shopdishes/delDishByShopId/{shopId}",method = RequestMethod.GET)
    public Integer delDishByShopId(@PathVariable("shopId") Integer shopId ) {
        return dishesService.delDishByShopId(shopId);
    }


    @RequestMapping(value = "/shopdishes/getDishById/{id}",method = RequestMethod.GET)
    public Dish getDishById(@PathVariable("id") Integer id) {
        return dishesService.getDishById(id);
    }

    @RequestMapping(value = "/shopdishes/getAllDishes",method = RequestMethod.GET)
    public PageResult getAllDishes(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("shopId") Integer shopId,@RequestParam("value") String value) {
        return dishesService.findAllDishes(page,size,shopId,value);
    }

    @RequestMapping(value = "/shopdishes/updateDish",method = RequestMethod.POST)
    public Integer updateDish(@RequestBody Dish dish ){
        return dishesService.updateDish(dish);
    }

//    @RequestMapping(value = "/shopdishes/getIfDishByTypeId",method = RequestMethod.POST)
//    public Integer getIfDishByTypeId(@RequestBody DishesDto dishesDto ){
//        return dishesService.getIfDishByTypeId(dishesDto);
//    }
    @RequestMapping(value = "/shopdishes/updateIfHotDish/{id}/{value}",method = RequestMethod.GET)
    public Integer updateIfHotDish(@PathVariable("id") Integer id,@PathVariable("value") Integer value) {
        return dishesService.updateIfHotDish(id,value);
    }

    @RequestMapping(value = "/shopdishes/getHotDish/{shopId}",method = RequestMethod.GET)
    public List<DishDto> getHotDish(@PathVariable("shopId") Integer shopId) {
        return dishesService.getHotDish(shopId);
    }
    @RequestMapping(value = "/shopdishes/getDishByTypeId/{shopId}/{typeId}",method = RequestMethod.GET)
    public List<DishDto> getDishByTypeId(@PathVariable("shopId") Integer shopId,@PathVariable("typeId") Integer typeId) {
        return dishesService.getDishByTypeId(shopId,typeId);
    }
}
