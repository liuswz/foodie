package com.lanke.foodie.service;



import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "FOODIE-DISHES")
public interface DishService {



    @RequestMapping(value = "/product/addVoucherForShop",method = RequestMethod.POST)
    public Integer addVoucherForShop(@RequestBody VoucherForShop voucherForShop , @RequestParam("ids") String ids);

    @RequestMapping(value = "/product/getVoucherForShopById/{shopId}",method = RequestMethod.GET)
    public List<VoucherForShop> getVoucherForShopById(@PathVariable("shopId")  Integer shopId);
    @RequestMapping(value = "/product/addProductType",method = RequestMethod.POST)
    public Integer addShopType(@RequestBody ProductType productType);
    @RequestMapping(value = "/product/findAllProductType",method = RequestMethod.GET)
    public List<ProductType> findAllProductType();
    @RequestMapping(value = "/product/delProductTypeById/{ids}",method = RequestMethod.GET)
    public Integer delProductTypeById(@PathVariable("ids") String ids );
    @RequestMapping(value = "/product/getIfProductByTypeId/{ids}",method = RequestMethod.GET)
    public Integer getIfProductByTypeId(@PathVariable("ids") String ids );

    @RequestMapping(value = "/product/findAllMoneyOff",method = RequestMethod.GET)
    public List<MoneyOff> findAllMoneyOff();
    @RequestMapping(value = "/product/addMoneyOff",method = RequestMethod.POST)
    public Integer addMoneyOff(@RequestBody MoneyOff moneyOff );
    @RequestMapping(value = "/product/getIfMoneyOffInProduct/{ids}",method = RequestMethod.GET)
    public Integer getIfMoneyOffInProduct(@PathVariable("ids") String ids );
    @RequestMapping(value = "/product/delMoneyOffById/{ids}",method = RequestMethod.GET)
    public Integer delMoneyOffById(@PathVariable("ids") String ids);


    @RequestMapping(value = "/product/addProduct",method = RequestMethod.POST)
    public Integer addProduct(@RequestBody Product product );

    @RequestMapping(value = "/product/delProductById/{ids}",method = RequestMethod.GET)
    public Integer delProductById(@PathVariable("ids") String ids );

    @RequestMapping(value = "/product/getProductById/{id}",method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/product/findAllProduct",method = RequestMethod.GET)
    public PageResult findAllProduct(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value);

    @RequestMapping(value = "/product/updateProduct",method = RequestMethod.POST)
    public Integer updateProduct(@RequestBody Product product );

    @RequestMapping(value = "/product/getProductNameById/{id}",method = RequestMethod.GET)
    public String getProductNameById(@PathVariable("id") Integer id) ;

    //广告
    @RequestMapping(value = "/advertisement/findAllAdvertisement/{page}/{size}",method = RequestMethod.GET)
    public PageResult findAllAdvertisement(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    @RequestMapping(value = "/advertisement/addAdvertisement",method = RequestMethod.POST)
    public Integer addAdvertisement(@RequestBody Advertisement advertisement );
    @RequestMapping(value = "/advertisement/delAdvertisementById/{id}",method = RequestMethod.GET)
    public Integer delAdvertisementById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/advertisement/getAdvertisementById/{id}",method = RequestMethod.GET)
    public Advertisement getAdvertisementById(@PathVariable("id") Integer id);
    @RequestMapping(value = "/advertisement/updateAdvertisement",method = RequestMethod.POST)
    public Integer updateAdvertisement(@RequestBody Advertisement advertisement);


    //删除菜品
    @RequestMapping(value = "/shopdishes/delDishTypeByShopId/{shopId}",method = RequestMethod.GET)
    public Integer delDishTypeByShopId(@PathVariable("shopId") Integer shopId );
    @RequestMapping(value = "/shopdishes/delDishByShopId/{shopId}",method = RequestMethod.GET)
    public Integer delDishByShopId(@PathVariable("shopId") Integer shopId );
}
