package com.lanke.foodie.dao;

import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.ShopCollect;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.simpleEntity.EasyShopDetail;
import com.lanke.foodie.userdto.DishDto;
import com.lanke.foodie.userdto.EasyShopDto;
import com.lanke.foodie.userdto.EntireShopDetail;
import com.lanke.foodie.userdto.ShopDetailDto;
import com.lanke.foodie.userparams.ShopInSiteAndTypeParam;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDetailDao {

    public List<ShopDetailDto> findAllShopDetails(ShopInSiteAndTypeParam param);
    public List<ShopDetailDto> findAllShopDetailsByMark(ShopInSiteAndTypeParam param);
    public List<ShopDetailDto> findAllShopDetailsByTypeId(ShopInSiteAndTypeParam param);
    public List<ShopDetailDto> findAllShopDetailsByMarkAndTypeId(ShopInSiteAndTypeParam param);

    public EntireShopDetail getEntireShopDetail(@Param("shopId") Integer shopId);
    public EasyShopDetail getEasyShopDetail(@Param("shopId") Integer shopId);

    public List<ShopDetailDto> getShopCollect(@Param("userId") Integer userId,@Param("lat") Double lat,@Param("lon") Double lon);
    public Integer checkShopCollect(@Param("userId") Integer userId,@Param("shopId") Integer shopId);
    public Integer deleteShopCollect(@Param("userId") Integer userId,@Param("shopId") Integer shopId);
    public Integer addShopCollect(ShopCollect collect);
    public EasyShopDto getEasyShop(@Param("shopId") Integer shopId);

    public String getShopPhoneById(@Param("shopId") Integer shopId);
}
