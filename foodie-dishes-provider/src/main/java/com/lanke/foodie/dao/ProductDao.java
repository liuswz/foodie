package com.lanke.foodie.dao;

import com.lanke.foodie.dto.ProductDto;
import com.lanke.foodie.dto.ProductWithMoneyOffDto;
import com.lanke.foodie.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ProductDao {
    public Integer addProductType(ProductType productType);
    public Integer checkProductType(@Param("typeName") String typeName);
    public List<ProductType> findAllProductType();
    public Integer getIfProductByTypeId(@Param("ids") String ids);

    public Integer delProductTypeById(@Param("ids") String ids);
    public Integer addVoucherForShop(List<VoucherForShop> list);
    public List<VoucherForShop> getVoucherForShopById(@Param("shopId") Integer shopId);

    public Integer addMoneyOff(MoneyOff moneyOff);
    public Integer delMoneyOffById(@Param("ids") String ids);
    public List<MoneyOff> findAllMoneyOff();
    public Integer getIfMoneyOffInProduct(@Param("ids") String ids);
    public Integer checkMoneyOff(@Param("fullNum") Double fullNum,@Param("minusNum") Double minusNum);

    public Integer addProduct(Product product);
    public Integer checkProduct(@Param("productName") String productName);
    public List<Integer> checkProductById(@Param("productName") String productName);


    public Integer delProductById(@Param("ids") String ids);
    public Product getProductById(@Param("id") Integer id);
    public List<ProductDto> findAllProduct(@Param("value") String value);

    public List<ProductWithMoneyOffDto> findAllProductWithMoneyOff(@Param("value") String value);
    public List<ProductWithMoneyOffDto> findAllProductWithMoneyOffByType(@Param("productTypeId") Integer productTypeId);

    public Integer updateProduct(Product product);

    public String getProductNameById(@Param("id") Integer id);


    public Integer addShopVoucher(Voucher voucher);
    public Integer updateVoucher(Voucher voucher);
    public List<Voucher> getVoucherByCity(@Param("city") String city);
    public Voucher getVoucherById(@Param("shopId") Integer shopId);
    public Integer checkVoucher(@Param("shopId") Integer shopId);
    public Integer delVoucherById(@Param("shopId") Integer shopId);



}
