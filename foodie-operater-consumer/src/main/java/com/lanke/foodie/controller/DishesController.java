package com.lanke.foodie.controller;

import com.lanke.foodie.dto.*;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.enums.AdType;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.DetailService;
import com.lanke.foodie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/operaterconsumer")
public class DishesController {
   // private static final String REST_URL_PREFIX = "http://FOODIE-SHOPDISHES";
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private DishService dishService;
    @Autowired
    private DetailService detailService;

    @RequestMapping(value = "/product/addVoucherForShop",method = RequestMethod.POST)
    public BaseJson addVoucherForShop( VoucherForShop voucherForShop,@RequestParam("ids") String ids ){

        int flag = dishService.addVoucherForShop(voucherForShop,ids);
        BaseJson baseJson = new BaseJson();
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("代金卷给予失败");

        }else {
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("代金卷给予成功");

        }
        return baseJson;
    }

    @RequestMapping(value = "/product/getVoucherForShopById/{shopId}",method = RequestMethod.GET)
    public List<VoucherForShop> getVoucherForShopById(@PathVariable("shopId")  Integer shopId) {
        return dishService.getVoucherForShopById(shopId);
    }
    @RequestMapping(value = "/product/addProductType/{typeName}",method = RequestMethod.GET)
    public BaseJson addProductType(@PathVariable("typeName") String typeName ){
        ProductType productType=new ProductType();
        productType.setTypeName(typeName);

        BaseJson baseJson = new BaseJson();

        int flag = dishService.addShopType(productType);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("商户类型已存在");

        }else {
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");

        }
        return baseJson;
    }

    @RequestMapping(value = "/product/findAllProductType",method = RequestMethod.GET)
    public List<ProductType> findAllProductType() {

        return dishService.findAllProductType();
    }
    @RequestMapping(value = "/product/delProductTypeById/{ids}",method = RequestMethod.GET)
    public BaseJson delProductTypeById(@PathVariable("ids") String ids) {

        BaseJson baseJson = new BaseJson();
        if(dishService.delProductTypeById(ids) > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/product/getIfProductByTypeIds/{ids}",method = RequestMethod.GET)
    public Integer getIfProductByTypeIds(@PathVariable("ids") String ids ){
        return dishService.getIfProductByTypeIds(ids);
    }






    @RequestMapping(value = "/product/findAllMoneyOff",method = RequestMethod.GET)
    public List<MoneyOff> findAllMoneyOff() {

        return dishService.findAllMoneyOff();
    }
    @RequestMapping(value = "/product/delMoneyOffById/{ids}",method = RequestMethod.GET)
    public BaseJson delMoneyOffById(@PathVariable("ids") String ids) {

        BaseJson baseJson = new BaseJson();
        if(dishService.delMoneyOffById(ids) > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/product/getIfMoneyOffInProduct/{ids}",method = RequestMethod.GET)
    public Integer getIfMoneyOffInProduct(@PathVariable("ids") String ids ){
        return dishService.getIfMoneyOffInProduct(ids);
    }




    @RequestMapping(value = "/product/addMoneyOff",method = RequestMethod.POST)
    public BaseJson addMoneyOff(@RequestBody MoneyOff moneyOff){

        BaseJson baseJson = new BaseJson();

        int flag = dishService.addMoneyOff(moneyOff);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("满减类型已存在");

        }else {
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
        }
        return baseJson;
    }


    @RequestMapping(value = "/product/addProduct",method = RequestMethod.POST)
    public BaseJson addProduct(@RequestBody Product product){

        BaseJson baseJson = new BaseJson();
        product.setProductSales(0);
        int flag = dishService.addProduct(product);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("商品已存在");

        }else {
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
        }
        return baseJson;
    }
    @RequestMapping(value = "/product/delProductById/{ids}",method = RequestMethod.GET)
    public BaseJson delProductById(@PathVariable("ids") String ids) {

        BaseJson baseJson = new BaseJson();
        if(dishService.delProductById(ids) > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;
    }
    @RequestMapping(value = "/product/updateProduct",method = RequestMethod.POST)
    public BaseJson updateProduct(@RequestBody Product product){

        BaseJson baseJson = new BaseJson();

        int flag = dishService.updateProduct(product);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("商品已存在");
        }else {
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");
        }
        return baseJson;
    }

    @RequestMapping(value = "/product/findAllProduct",method = RequestMethod.GET)
    public PageResult findAllProduct(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(value="value",defaultValue="") String value) {

        return dishService.findAllProduct(page,size,value);
    }
    @RequestMapping(value = "/product/getProductById/{id}",method = RequestMethod.GET)
    public ProductAndMoneyOffDto getProductById(@PathVariable("id") Integer id) {
        //   BaseJson baseJson = new BaseJson();
        ProductAndMoneyOffDto productAndMoneyOffDto = new ProductAndMoneyOffDto();

        productAndMoneyOffDto.setProduct(dishService.getProductById(id));
        productAndMoneyOffDto.setProductTypeList(dishService.findAllProductType());

        productAndMoneyOffDto.setMoneyOffList( dishService.findAllMoneyOff());

        return productAndMoneyOffDto;

    }
    @RequestMapping(value = "/product/getProductTypeAndMoneyOff",method = RequestMethod.GET)
    public ProductTypeAndMoneyOffDto getProductTypeAndMoneyOff() {
        //   BaseJson baseJson = new BaseJson();
        ProductTypeAndMoneyOffDto productTypeAndMoneyOffDto = new ProductTypeAndMoneyOffDto();


        productTypeAndMoneyOffDto.setProductTypeList(dishService.findAllProductType());

        productTypeAndMoneyOffDto.setMoneyOffList( dishService.findAllMoneyOff());

        return productTypeAndMoneyOffDto;

    }
    @RequestMapping(value = "/product/getProductNameById/{id}",method = RequestMethod.GET)
    public BaseJson getProductNameById(@PathVariable("id") Integer id) {
        BaseJson baseJson = new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(dishService.getProductNameById(id));
        return baseJson;


    }

//广告
    @RequestMapping(value = "/advertisement/findAllAdvertisement/{page}/{size}",method = RequestMethod.GET)
    public PageMapResult findAllAdvertisement(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return dishService.findAllAdvertisement(page,size);
    }

    @RequestMapping(value = "/advertisement/addAdvertisement",method = RequestMethod.POST)
    public BaseJson addAdvertisement(@RequestBody Advertisement advertisement ){
        BaseJson baseJson = new BaseJson();
        if(CheckShopAndProductId(advertisement)){
            int flag = dishService.addAdvertisement(advertisement);
            if(flag == -1){
                baseJson.setCode(1);
                baseJson.setMessage("失败");
                baseJson.setResult("对不起一个城市只能有5个广告");
            }else {
                baseJson.setCode(0);
                baseJson.setMessage("成功");
                baseJson.setResult("添加成功");
            }
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("店铺或商品Id找不到");
        }

        return baseJson;



    }
    @RequestMapping(value = "/advertisement/delAdvertisementById/{id}",method = RequestMethod.GET)
    public BaseJson delAdvertisementById(@PathVariable("id") Integer id) {
        BaseJson baseJson = new BaseJson();

        int flag = dishService.delAdvertisementById(id);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("删除失败");
        }else {
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("删除成功");
        }
        return baseJson;

    }
    @RequestMapping(value = "/advertisement/delAdvertisementByCity/{city}",method = RequestMethod.GET)
    public BaseJson delAdvertisementByCity(@PathVariable("city") String city){
        BaseJson baseJson = new BaseJson();

        int flag = dishService.delAdvertisementByCity(city);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("删除失败");
        }else {
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("删除成功");
        }
        return baseJson;
    }

    @RequestMapping(value = "/advertisement/getAdvertisementById/{id}",method = RequestMethod.GET)
    public Advertisement getAdvertisementById(@PathVariable("id") Integer id) {
        return dishService.getAdvertisementById(id);
    }
    @RequestMapping(value = "/advertisement/getAdvertisementByCity/{city}",method = RequestMethod.GET)
    public List<Advertisement> getAdvertisementByCity(@PathVariable("city") String city){
        return dishService.getAdvertisementByCity(city);
    }

    @RequestMapping(value = "/advertisement/updateAdvertisement",method = RequestMethod.POST)
    public BaseJson updateAdvertisement(@RequestBody Advertisement advertisement){

        BaseJson baseJson = new BaseJson();
        if(CheckShopAndProductId(advertisement)){
            int flag = dishService.updateAdvertisement(advertisement);
            if(flag == 0){
                baseJson.setCode(1);
                baseJson.setMessage("失败");
                baseJson.setResult("更改失败");
            }else {
                baseJson.setCode(0);
                baseJson.setMessage("成功");
                baseJson.setResult("更改成功");
            }
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("店铺或商品Id找不到");
        }

        return baseJson;

    }


    public boolean CheckShopAndProductId(Advertisement advertisement) {


        if(AdType.Shop.getIndex()==advertisement.getTypeId()){
            if(detailService.getShopNameById(advertisement.getRedirectId())==null){
                return false;
            }
        }else if(AdType.Product.getIndex()==advertisement.getTypeId()){
            if( dishService.getProductNameById(advertisement.getRedirectId())==null){
                return false;
            }
        }

        return true;


    }
}
