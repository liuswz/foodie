package com.lanke.foodie.controller;


import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.service.ProductService;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userdto.ProductUserDto;
import com.lanke.foodie.userdto.VoucherForUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/addProductType",method = RequestMethod.POST)
    public Integer addProductType(@RequestBody ProductType productType  ){
        return productService.addProductType(productType);
    }
    @RequestMapping(value = "/product/findAllProductType",method = RequestMethod.GET)
    public List<ProductType> findAllProductType() {

        return productService.findAllProductType();
    }
    @RequestMapping(value = "/product/delProductTypeById/{ids}",method = RequestMethod.GET)
    public Integer delProductTypeById(@PathVariable("ids") String ids ) {

        return productService.delProductTypeById(ids);
    }
    @RequestMapping(value = "/product/getIfProductByTypeIds/{ids}",method = RequestMethod.GET)
    public Integer getIfProductByTypeIds(@PathVariable("ids") String ids ){
        return productService.getIfProductByTypeIds(ids);
    }


    @RequestMapping(value = "/product/addVoucherForShop",method = RequestMethod.POST)
    public Integer addVoucherForShop(@RequestBody VoucherForShop voucherForShop , String ids){

        return productService.addVoucherForShop(ids,voucherForShop);
    }

    @RequestMapping(value = "/product/getVoucherForShopById/{shopId}",method = RequestMethod.GET)
    public List<VoucherForShop> getVoucherForShopById(@PathVariable("shopId")  Integer shopId) {
        return productService.getVoucherForShopById(shopId);
    }
    @RequestMapping(value = "/product/getVoucherForUserById/{shopId}/{userId}",method = RequestMethod.GET)
    public List<VoucherForUserDto> getVoucherForUserById(@PathVariable("shopId")  Integer shopId,@PathVariable("userId")  Integer userId) {
        return productService.getVoucherForUserById(shopId,userId);
    }
    @RequestMapping(value = "/product/delVoucherForUserById/{id}",method = RequestMethod.GET)
    public Integer delVoucherForUserById(@PathVariable("id")  Integer id) {
        return productService.delVoucherForUserById(id);
    }

    @RequestMapping(value = "/product/findAllMoneyOff",method = RequestMethod.GET)
    public List<MoneyOff> findAllMoneyOff() {

        return productService.findAllMoneyOff();
    }
    @RequestMapping(value = "/product/addMoneyOff",method = RequestMethod.POST)
    public Integer addMoneyOff(@RequestBody MoneyOff moneyOff  ){
        return productService.addMoneyOff(moneyOff);
    }
    @RequestMapping(value = "/product/getIfMoneyOffInProduct/{ids}",method = RequestMethod.GET)
    public Integer getIfMoneyOffInProduct(@PathVariable("ids") String ids ){
        return productService.getIfMoneyOffInProduct(ids);
    }
    @RequestMapping(value = "/product/delMoneyOffById/{ids}",method = RequestMethod.GET)
    public Integer delMoneyOffById(@PathVariable("ids") String ids) {
        return productService.delMoneyOffById(ids);
    }
    @RequestMapping(value = "/product/findMoneyOffByIds/{ids}",method = RequestMethod.GET)
    public List<MoneyOff> findMoneyOffByIds(@PathVariable("ids") String ids) {
        return productService.findMoneyOffByIds(ids);
    }


    @RequestMapping(value = "/product/addProduct",method = RequestMethod.POST)
    public Integer addProduct(@RequestBody Product product ){

        return productService.addProduct(product);
    }
    @RequestMapping(value = "/product/delProductById/{ids}",method = RequestMethod.GET)
    public Integer delProductById(@PathVariable("ids") String ids ) {
        return productService.delProductById(ids);
    }

    @RequestMapping(value = "/product/getProductById/{id}",method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/product/findAllProduct",method = RequestMethod.GET)
    public PageResult findAllProduct(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value) {
        return productService.findAllProduct(page,size,value);
    }
    @RequestMapping(value = "/product/findAllProductWithMoneyOff",method = RequestMethod.GET)
    public PageResults findAllProductWithMoneyOff(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value) {
      //  System.out.println(value+"----------------------");
        return productService.findAllProductWithMoneyOff(page,size,value);
    }
    @RequestMapping(value = "/product/findAllProductWithMoneyOffByType",method = RequestMethod.GET)
    public PageResults findAllProductWithMoneyOffByType(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("productType") String productType) {
        return productService.findAllProductWithMoneyOffByType(page,size,productType);
    }
    @RequestMapping(value = "/product/updateProduct",method = RequestMethod.POST)
    public Integer updateProduct(@RequestBody Product product ){
        return productService.updateProduct(product);
    }

    @RequestMapping(value = "/product/getProductNameById/{id}",method = RequestMethod.GET)
    public String getProductNameById(@PathVariable("id") Integer id) {
        return  productService.getProductNameById(id);
    }


    @RequestMapping(value = "/product/addShopVoucher",method = RequestMethod.POST)
    public Integer addShopVoucher(@RequestBody Voucher voucher  ){
        return productService.addShopVoucher(voucher);
    }

    @RequestMapping(value = "/product/getVoucherByCity/{page}/{size}/{city}",method = RequestMethod.GET)
    public PageResults getVoucherByCity(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("city") String city) {
        return productService.getVoucherByCity(page,size,city);
    }
    @RequestMapping(value = "/product/getVoucherForUserByUserId/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults getVoucherForUserByUserId(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("userId") Integer userId) {
        return productService.getVoucherForUserByUserId(page,size,userId);
    }

    @RequestMapping(value = "/product/getVoucherById/{shopId}",method = RequestMethod.GET)
    public Voucher getVoucherById(@PathVariable("shopId") Integer shopId) {
        return productService.getVoucherById(shopId);
    }
    @RequestMapping(value = "/product/delVoucherById/{shopId}",method = RequestMethod.GET)
    public Integer delVoucherById(@PathVariable("shopId") Integer shopId ) {
        return productService.delVoucherById(shopId);
    }

    @RequestMapping(value = "/product/getProductNamesByValue/{value}",method = RequestMethod.GET)
    public List<String> getProductNamesByValue(@PathVariable("value") String value) {
        return productService.getProductNamesByValue(value);
    }
    @RequestMapping(value = "/product/addShopVoucherForUser",method = RequestMethod.POST)
    public Integer addShopVoucherForUser(@RequestBody VoucherForUser voucher){
        return productService.addShopVoucherForUser(voucher);
    }

    @RequestMapping(value = "/product/checkVoucherForUser/{shopId}/{userId}/{voucherId}",method = RequestMethod.GET)
    public Integer checkVoucherForUser(@PathVariable("shopId") Integer shopId,@PathVariable("userId") Integer userId,@PathVariable("voucherId") Integer voucherId) {
        return productService.checkVoucherForUser(shopId,userId,voucherId);
    }

    @RequestMapping(value = "/product/getProductDetailForUser/{id}",method = RequestMethod.GET)
    public ProductUserDto getProductDetailForUser(@PathVariable("id") Integer id) {
        return productService.getProductDetailForUser(id);
    }

}

