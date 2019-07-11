package com.lanke.foodie.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ShopIdAndCityDto;
import com.lanke.foodie.dto.ShopNameAndIdDto;
import com.lanke.foodie.dto.ShopSearchPropertyDto;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.entity.ShopType;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.DetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DetailController {


    @Autowired
    private DetailService detailService;

    @RequestMapping(value = "/shopdetail/register",method = RequestMethod.POST)
    public Integer regist(@RequestBody Shop shop  ){
       // log.info("测试{}，日志级别{}，输出{}", "demo1", "info", "info level log");
     /*   BaseJson baseJson = new BaseJson();
        //log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
      */
        return detailService.regist(shop);
    }

    @RequestMapping(value = "/shopdetail/updateShop",method = RequestMethod.POST)
    public Integer updateShop(@RequestBody Shop shop){
        return detailService.updateShop(shop);
    }
    @RequestMapping(value = "/shopdetail/updateShopDetails",method = RequestMethod.POST)
    public Integer updateShopDetails(@RequestBody ShopDetails shopDetails ){
        return detailService.updateShopDetails(shopDetails);
    }
    @RequestMapping(value = "/shopdetail/getShopById/{id}",method = RequestMethod.GET)
    public Shop getShopById(@PathVariable("id") Integer id){
        return detailService.getShopById(id);
    }
    @RequestMapping(value = "/shopdetail/getShopDetailsById/{shopId}",method = RequestMethod.GET)
    public ShopDetails getShopDetailsById(@PathVariable("shopId") Integer shopId){
        return detailService.getShopDetailsById(shopId);
    }

    @RequestMapping(value = "/shopdetail/getNameAndIdByUsername/{username}",method = RequestMethod.GET)
    public ShopNameAndIdDto getNameAndIdByUsername(@PathVariable("username") String username){
        return detailService.getNameAndIdByUsername(username);
    }
//    @RequestMapping(value = "/shopdetail/getAuthorityByUsername/{username}",method = RequestMethod.GET)
//    public String getAuthorityByUsername(@PathVariable("username") String username) {
//        return detailService.getAuthorityByUsername(username);
//    }

    @RequestMapping(value = "/shopdetail/findAllShop",method = RequestMethod.GET)
    public PageResult findAllShop(@RequestParam("page") Integer page, @RequestParam("size") Integer size,@RequestParam("value") String value) {
        return detailService.findAllShop(page,size,value);

    }
    @RequestMapping(value = "/shopdetail/findShopByStatusAndCity",method = RequestMethod.GET)
    public PageResult findShopByStatus( @RequestParam("page") Integer page, @RequestParam("size") Integer size,@RequestBody  ShopSearchPropertyDto shopSearchPropertyDto) {
        return detailService.findShopByStatusAndCity(shopSearchPropertyDto,page,size);
    }

    @RequestMapping(value = "/shopdetail/updateShopStatus/{id}",method = RequestMethod.GET)
    public Integer updateShopStatus(@PathVariable("id") Integer id) {
        return detailService.updateShopStatus(id);
    }

    @RequestMapping(value = "/shopdetail/updateOperaterStatus/{id}/{value}",method = RequestMethod.GET)
    public Integer updateOperaterStatus(@PathVariable("id") Integer id,@PathVariable("value") Integer value) {
        return detailService.updateOperaterStatus(id,value);
    }
    @RequestMapping(value = "/shopdetail/getOperaterStatus/{id}",method = RequestMethod.GET)
    public Integer getOperaterStatus(@PathVariable("id") Integer id) {
        return detailService.getOperaterStatus(id);
    }


    @RequestMapping(value = "/shopdetail/deleteShop/{id}",method = RequestMethod.GET)
    public Integer deleteShop(@PathVariable("id") Integer id) {

        return detailService.deleteShop(id);
    }

    @RequestMapping(value = "/shopdetail/findPayPhoto/{id}",method = RequestMethod.GET)
    public String findPayPhoto(@PathVariable("id") Integer id) {
        return detailService.findPayPhoto(id);
    }

    @RequestMapping(value = "/shopdetail/getPassword/{username}",method = RequestMethod.GET)
    public String getPassword(@PathVariable("username") String username) {
        return detailService.getPassword(username);
    }
    @RequestMapping(value = "/shopdetail/getShopPassword/{username}",method = RequestMethod.GET)
    public String getShopPassword(@PathVariable("username") String username) {
        return detailService.getShopPassword(username);
    }
    @RequestMapping(value = "/shopdetail/addShopType",method = RequestMethod.POST)
    public Integer addShopType(@RequestBody ShopType shopType  ){
        return detailService.addShopType(shopType);
    }
    @RequestMapping(value = "/shopdetail/findAllShopType",method = RequestMethod.GET)
    public List<ShopType> findAllShopType() {
        return detailService.findAllShopType();
    }
    @RequestMapping(value = "/shopdetail/delShopTypeById/{ids}",method = RequestMethod.GET)
    public Integer delShopTypeById(@PathVariable("ids") String ids ) {
        return detailService.delShopTypeById(ids);
    }

    @RequestMapping(value = "/shopdetail/getIfShopByTypeId/{ids}",method = RequestMethod.GET)
    public Integer getIfShopByTypeId(@PathVariable("ids") String ids ){
        return detailService.getIfShopByTypeId(ids);
    }
    @RequestMapping(value = "/shopdetail/getShopNameById/{id}",method = RequestMethod.GET)
    public String getShopNameById(@PathVariable("id") Integer id){
        return detailService.getShopNameById(id);
    }


    @RequestMapping(value = "/shopdetail/getIdAndCityByUsername/{username}",method = RequestMethod.GET)
    public ShopIdAndCityDto getIdAndCityByUsername(@PathVariable("username") String username){
        return detailService.getIdAndCityByUsername(username);
    }




}
