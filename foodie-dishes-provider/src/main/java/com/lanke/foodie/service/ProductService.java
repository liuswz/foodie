package com.lanke.foodie.service;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ProductAndMoneyOffDto;
import com.lanke.foodie.dto.ProductWithMoneyOffDto;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userdto.ProductUserDto;
import com.lanke.foodie.userdto.VoucherDto;
import com.lanke.foodie.userdto.VoucherForUserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {
    public Integer addProductType(ProductType productType);
    public List<ProductType> findAllProductType();
    public Integer delProductTypeById(String ids);
    public Integer getIfProductByTypeIds(String ids);


    public Integer addVoucherForShop(String ids, VoucherForShop voucherForShop);

    public List<VoucherForShop> getVoucherForShopById( Integer shopId);
    public List<VoucherForUserDto> getVoucherForUserById(Integer shopId,Integer  userId);
    public Integer delVoucherForUserById(Integer id);

    public Integer addMoneyOff(MoneyOff moneyOff);
    public Integer delMoneyOffById(String ids);
    public List<MoneyOff> findMoneyOffByIds(String ids);
    public List<MoneyOff> findAllMoneyOff();
    public Integer getIfMoneyOffInProduct(String ids);

    public Integer addProduct(Product product);
    public Integer delProductById(String ids);
    public Product getProductById(Integer id);
    public PageResult findAllProduct(Integer pageNum, Integer pageSize, String value);
    public List<String> getProductNamesByValue(String value);

    public PageResults findAllProductWithMoneyOff(Integer pageNum, Integer pageSize, String value);
    public PageResults findAllProductWithMoneyOffByType(Integer pageNum, Integer pageSize,String productType);

    public Integer updateProduct(Product product);

    public String getProductNameById(Integer id);


    public Integer addShopVoucher(Voucher voucher);
    public PageResults getVoucherByCity(Integer pageNum, Integer pageSize, String city);
    public PageResults getVoucherForUserByUserId(Integer pageNum, Integer pageSize, Integer userId);
    public Voucher getVoucherById(Integer shopId);
    public Integer delVoucherById(Integer shopId);
    public Integer addShopVoucherForUser(VoucherForUser voucher);
    public Integer checkVoucherForUser(Integer shopId,Integer userId,Integer voucherId);


    public ProductUserDto getProductDetailForUser(Integer id);
}
