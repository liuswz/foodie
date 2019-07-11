package com.lanke.foodie.service;

import com.lanke.foodie.dto.ShopIdAndCityDto;
import com.lanke.foodie.dto.ShopNameAndIdDto;
import com.lanke.foodie.entity.Shop;

import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.entity.ShopType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOODIE-DETAIL")
public interface DetailService {


    @RequestMapping(value = "/shopdetail/register",method = RequestMethod.POST)
    public Integer regist(@RequestBody Shop shop);

    @RequestMapping(value = "/shopdetail/updateShop",method = RequestMethod.POST)
    public Integer updateShop(@RequestBody Shop shop);

    @RequestMapping(value = "/shopdetail/getShopById/{id}",method = RequestMethod.GET)
    public Shop getShopById(@PathVariable("id") Integer id);


    @RequestMapping(value = "/shopdetail/updateShopDetails",method = RequestMethod.POST)
    public Integer updateShopDetails(@RequestBody ShopDetails shopDetails );
    @RequestMapping(value = "/shopdetail/getShopDetailsById/{shopId}",method = RequestMethod.GET)
    public ShopDetails getShopDetailsById(@PathVariable("shopId") Integer shopId);

    @RequestMapping(value = "/shopdetail/getNameAndIdByUsername/{username}",method = RequestMethod.GET)
    public ShopNameAndIdDto getNameAndIdByUsername(@PathVariable("username") String username);

    @RequestMapping(value = "/shopdetail/getShopPassword/{username}",method = RequestMethod.GET)
    public String getShopPassword(@PathVariable("username") String username);

    @RequestMapping(value = "/shopdetail/findAllShopType",method = RequestMethod.GET)
    public List<ShopType> findAllShopType();

    @RequestMapping(value = "/shopdetail/updateOperaterStatus/{id}/{value}",method = RequestMethod.GET)
    public Integer updateOperaterStatus(@PathVariable("id") Integer id,@PathVariable("value") Integer value);

    @RequestMapping(value = "/shopdetail/getOperaterStatus/{id}",method = RequestMethod.GET)
    public Integer getOperaterStatus(@PathVariable("id") Integer id);

    @RequestMapping(value = "/shopdetail/getIdAndCityByUsername/{username}",method = RequestMethod.GET)
    public ShopIdAndCityDto getIdAndCityByUsername(@PathVariable("username") String username);

}
