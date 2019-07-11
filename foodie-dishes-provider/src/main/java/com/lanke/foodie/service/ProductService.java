package com.lanke.foodie.service;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ProductWithMoneyOffDto;
import com.lanke.foodie.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {
    public Integer addProductType(ProductType productType);
    public List<ProductType> findAllProductType();
    public Integer delProductTypeById(String ids);
    public Integer getIfProductByTypeId(String ids);


    public Integer addVoucherForShop(String ids, VoucherForShop voucherForShop);

    public List<VoucherForShop> getVoucherForShopById( Integer shopId);

    public Integer addMoneyOff(MoneyOff moneyOff);
    public Integer delMoneyOffById(String ids);
    public List<MoneyOff> findAllMoneyOff();
    public Integer getIfMoneyOffInProduct(String ids);

    public Integer addProduct(Product product);
    public Integer delProductById(String ids);
    public Product getProductById(Integer id);
    public PageResult findAllProduct(Integer pageNum, Integer pageSize, String value);
    public PageResult findAllProductWithMoneyOff(Integer pageNum, Integer pageSize,String value);
    public PageResult findAllProductWithMoneyOffByType(Integer pageNum, Integer pageSize,Integer productTypeId);

    public Integer updateProduct(Product product);

    public String getProductNameById(Integer id);


    public Integer addShopVoucher(Voucher voucher);
    public PageResult getVoucherByCity(Integer pageNum, Integer pageSize, String city);
    public Voucher getVoucherById(Integer shopId);
    public Integer delVoucherById(Integer shopId);
}
