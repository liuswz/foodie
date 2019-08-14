package com.lanke.foodie.dao;

import com.lanke.foodie.dto.ProductDto;
import com.lanke.foodie.dto.ProductWithMoneyOffDto;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.userdto.ProductUserDto;
import com.lanke.foodie.userdto.VoucherDto;
import com.lanke.foodie.userdto.VoucherForUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ProductDao {
    public Integer addProductType(ProductType productType);
    public Integer checkProductType(@Param("typeName") String typeName);
    public List<ProductType> findAllProductType();
    public Integer getIfProductByTypeIds(@Param("ids") String ids);

    public Integer delProductTypeById(@Param("ids") String ids);
    public Integer addVoucherForShop(List<VoucherForShop> list);
    public List<VoucherForShop> getVoucherForShopById(@Param("shopId") Integer shopId);
    public List<VoucherForUserDto> getVoucherForUserById(@Param("shopId") Integer shopId , @Param("userId") Integer  userId);
    public Integer delVoucherForUserById(@Param("id") Integer id);


    public Integer addMoneyOff(MoneyOff moneyOff);
    public Integer delMoneyOffById(@Param("ids") String ids);
    public List<MoneyOff> findMoneyOffByIds(@Param("ids") String ids);

    public List<MoneyOff> findAllMoneyOff();
    public Integer getIfMoneyOffInProduct(@Param("ids") String ids);
    public Integer checkMoneyOff(@Param("fullNum") Integer fullNum,@Param("minusNum") Integer minusNum);

    public Integer addProduct(Product product);
    public Integer checkProduct(@Param("productName") String productName);
    public List<Integer> checkProductById(@Param("productName") String productName);


    public Integer delProductById(@Param("ids") String ids);
    public Product getProductById(@Param("id") Integer id);
    public List<ProductDto> findAllProduct(@Param("value") String value);
    public List<String> getProductNamesByValue(@Param("value") String value);

    public List<ProductWithMoneyOffDto> findAllProductWithMoneyOff(@Param("value") String value);
    public List<ProductWithMoneyOffDto> findAllProductWithMoneyOffByType(@Param("productType") String productType);

    public Integer updateProduct(Product product);

    public String getProductNameById(@Param("id") Integer id);


    public Integer addShopVoucher(Voucher voucher);
    public Integer updateVoucher(Voucher voucher);
    public List<VoucherDto> getVoucherByCity(@Param("city") String city);
    public List<VoucherDto> getVoucherForUserByUserId(@Param("userId") Integer userId);


    public Voucher getVoucherById(@Param("shopId") Integer shopId);
    public Integer checkVoucher(@Param("shopId") Integer shopId);
    public Integer delVoucherById(@Param("shopId") Integer shopId);
    public Integer addShopVoucherForUser(VoucherForUser voucher);
    public Integer checkVoucherForUser(@Param("shopId") Integer shopId,@Param("userId") Integer userId,@Param("voucherId") Integer voucherId);

    public ProductUserDto getProductDetailForUser(@Param("id") Integer id);
}
