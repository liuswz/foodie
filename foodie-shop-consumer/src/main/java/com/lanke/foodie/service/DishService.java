package com.lanke.foodie.service;


import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;

import com.lanke.foodie.entity.MoneyOff;
import com.lanke.foodie.entity.Voucher;
import com.lanke.foodie.userdto.PageResults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-DISHES")
public interface DishService {


    @RequestMapping(value = "/shopdishes/adddishtype",method = RequestMethod.POST)
    public Integer addDishType(@RequestBody DishType dishType  );

    @RequestMapping(value = "/shopdishes/adddish",method = RequestMethod.POST)
    public Integer addDish(@RequestBody Dish dish );

    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}/{page}/{size}",method = RequestMethod.GET)
    public PageResult findAllDishType(
            @PathVariable("shopId") Integer shopId,
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
         );
    @RequestMapping(value = "/shopdishes/getAllDishType/{shopId}",method = RequestMethod.GET)
    public List<DishType> findAllDishType( @PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/shopdishes/getAllDishes",method = RequestMethod.GET)
    public PageResult getAllDishes(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("shopId") Integer shopId, @RequestParam("value") String value);

    @RequestMapping(value = "/shopdishes/delDishTypeById/{ids}",method = RequestMethod.GET)
    public Integer delDishTypeById(@PathVariable("ids") String ids);
    @RequestMapping(value = "/shopdishes/delDishById/{ids}",method = RequestMethod.GET)
    public Integer delDishById(@PathVariable("ids") String ids);

    @RequestMapping(value = "/shopdishes/getDishById/{id}",method = RequestMethod.GET)
    public Dish getDishById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/shopdishes/updateDish",method = RequestMethod.POST)
    public Integer updateDish(@RequestBody Dish dish );

//    @RequestMapping(value = "/shopdishes/getIfDishByTypeId",method = RequestMethod.POST)
//    public Integer getIfDishByTypeId(@RequestBody DishesDto dishesDto );


    @RequestMapping(value = "/product/findAllMoneyOff",method = RequestMethod.GET)
    public List<MoneyOff> findAllMoneyOff();
    @RequestMapping(value = "/product/findMoneyOffByIds/{ids}",method = RequestMethod.GET)
    public List<MoneyOff> findMoneyOffByIds(@PathVariable("ids") String ids);
    //商品
    @RequestMapping(value = "/product/findAllProductWithMoneyOff",method = RequestMethod.GET)
    public PageResult findAllProductWithMoneyOff(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value);
    @RequestMapping(value = "/product/findAllProductWithMoneyOffByType",method = RequestMethod.GET)
    public PageResult findAllProductWithMoneyOffByType(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("productType") String productType);
    //优惠卷
    @RequestMapping(value = "/product/addShopVoucher",method = RequestMethod.POST)
    public Integer addShopVoucher(@RequestBody Voucher voucher  );
    @RequestMapping(value = "/product/getVoucherById/{shopId}",method = RequestMethod.GET)
    public Voucher getVoucherById(@PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/product/delVoucherById/{shopId}",method = RequestMethod.GET)
    public Integer delVoucherById(@PathVariable("shopId") Integer shopId );

    @RequestMapping(value = "/shopdishes/checkDishByShopId/{shopId}",method = RequestMethod.GET)
    public Integer checkDishByShopId(@PathVariable("shopId") Integer shopId);
    @RequestMapping(value = "/shopdishes/updateIfHotDish/{id}/{value}",method = RequestMethod.GET)
    public Integer updateIfHotDish(@PathVariable("id") Integer id,@PathVariable("value") Integer value);
}
