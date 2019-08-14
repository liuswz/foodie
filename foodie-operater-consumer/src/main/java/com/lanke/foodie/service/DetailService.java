package com.lanke.foodie.service;

import com.lanke.foodie.dto.DishesDto;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ShopSearchPropertyDto;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopType;
import feign.Param;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-DETAIL")
public interface DetailService {



    @RequestMapping(value = "/shopdetail/getShopById/{id}",method = RequestMethod.GET)
    public Shop getShopById(@PathVariable("id") Integer id);
    @RequestMapping(value = "/shopdetail/findAllShop",method = RequestMethod.GET)
    public PageResult findAllShop(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value);

    @RequestMapping(value = "/shopdetail/findShopByStatusAndCity",method = RequestMethod.GET)
    public PageResult findShopByStatus(@RequestParam("page") Integer page, @RequestParam("size") Integer size,@RequestBody ShopSearchPropertyDto shopSearchPropertyDto) ;
    @RequestMapping(value = "/shopdetail/updateShopStatus/{id}",method = RequestMethod.GET)
    public Integer updateShopStatus(@PathVariable("id") Integer id);

    @RequestMapping(value = "/shopdetail/deleteShop/{id}",method = RequestMethod.GET)
    public Integer deleteShop(@PathVariable("id") Integer id);

    @RequestMapping(value = "/shopdetail/findPayPhoto/{id}",method = RequestMethod.GET)
    public String findPayPhoto(@PathVariable("id") Integer id);
    @RequestMapping(value = "/shopdetail/getPassword/{username}",method = RequestMethod.GET)
    public String getPassword(@PathVariable("username") String username);

    @RequestMapping(value = "/shopdetail/addShopType",method = RequestMethod.POST)
    public Integer addShopType(@RequestBody ShopType shopType);

    @RequestMapping(value = "/shopdetail/findAllShopType",method = RequestMethod.GET)
    public List<ShopType> findAllShopType();
    @RequestMapping(value = "/shopdetail/delShopTypeById/{ids}",method = RequestMethod.GET)
    public Integer delShopTypeById(@PathVariable("ids") String ids ) ;

    @RequestMapping(value = "/shopdetail/getIfShopByTypeIds/{ids}",method = RequestMethod.GET)
    public Integer getIfShopByTypeIds(@PathVariable("ids") String ids );

    @RequestMapping(value = "/shopdetail/getShopNameById/{id}",method = RequestMethod.GET)
    public String getShopNameById(@PathVariable("id") Integer id);
}
