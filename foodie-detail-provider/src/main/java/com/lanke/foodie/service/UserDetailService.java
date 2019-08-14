package com.lanke.foodie.service;



import com.lanke.foodie.entity.ShopCollect;
import com.lanke.foodie.simpleEntity.EasyShopDetail;
import com.lanke.foodie.userdto.EasyShopDto;
import com.lanke.foodie.userdto.EntireShopDetail;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userdto.ShopDetailDto;
import com.lanke.foodie.userparams.ShopInSiteAndTypeParam;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface UserDetailService {
    public PageResults findAllShopDetails(ShopInSiteAndTypeParam param);
    public PageResults findAllShopDetailsByMark(ShopInSiteAndTypeParam param);
    public PageResults findAllShopDetailsByTypeId(ShopInSiteAndTypeParam param);
    public PageResults findAllShopDetailsByMarkAndTypeId(ShopInSiteAndTypeParam param);

    public EntireShopDetail getEntireShopDetail(Integer shopId);
    public EasyShopDetail getEasyShopDetail(Integer shopId);

    public PageResults getShopCollect(Integer pageNum, Integer pageSize,Integer userId,Double lat,Double lon);
    public Integer checkShopCollect(Integer userId,Integer shopId);
    public Integer deleteShopCollect(Integer userId,Integer shopId);
    public Integer addShopCollect(ShopCollect collect);

    public EasyShopDto getEasyShop(Integer shopId);

    public String getShopPhoneById(Integer shopId);
}
