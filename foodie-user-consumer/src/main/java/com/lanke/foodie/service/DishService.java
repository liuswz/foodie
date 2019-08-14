package com.lanke.foodie.service;

import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.entity.MoneyOff;
import com.lanke.foodie.userdto.DishDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "FOODIE-DISHES")
public interface DishService {
    @RequestMapping(value = "/product/findMoneyOffByIds/{ids}",method = RequestMethod.GET)
    public List<MoneyOff> findMoneyOffByIds(@PathVariable("ids") String ids);

    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public List<DishType> findAllDishType(@PathVariable("shopId") Integer shopId);

    @RequestMapping(value = "/shopdishes/getHotDish/{shopId}",method = RequestMethod.GET)
    public List<DishDto> getHotDish(@PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/shopdishes/getDishByTypeId/{shopId}/{typeId}",method = RequestMethod.GET)
    public List<DishDto> getDishByTypeId(@PathVariable("shopId") Integer shopId,@PathVariable("typeId") Integer typeId);
}
