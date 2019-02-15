package com.lanke.foodie.service;


import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "FOODIE-SHOPDISHES")
public interface DishService {


    @RequestMapping(value = "/shopdishes/adddishtype",method = RequestMethod.POST)
    public Integer addDishType(@RequestBody DishType dishType  );

    @RequestMapping(value = "/shopdishes/adddish",method = RequestMethod.POST)
    public Integer addDish(@RequestBody Dish dish );

    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}/{page}/{size}",method = RequestMethod.GET)
    public PageResult findAllDishType(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size,
            @PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public List<DishType> findAllDishType( @PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/shopdishes/getAllDishes/{shopId}/{page}/{size}/{value}",method = RequestMethod.GET)
    public PageResult getAllDishes(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId") Integer shopId,@PathVariable("value") String value);

    @RequestMapping(value = "/shopdishes/delDishTypeById/{ids}",method = RequestMethod.GET)
    public Integer delDishTypeById(@PathVariable("ids") String ids);
    @RequestMapping(value = "/shopdishes/delDishById/{ids}",method = RequestMethod.GET)
    public Integer delDishById(@PathVariable("ids") String ids);

    @RequestMapping(value = "/shopdishes/getDishById/{id}",method = RequestMethod.GET)
    public Dish getDishById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/shopdishes/updateDish",method = RequestMethod.POST)
    public Integer updateDish(@RequestBody Dish dish );


}
