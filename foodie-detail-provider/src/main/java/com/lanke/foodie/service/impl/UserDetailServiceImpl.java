package com.lanke.foodie.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.DetailDao;
import com.lanke.foodie.dao.UserDetailDao;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ShopIdAndCityDto;
import com.lanke.foodie.dto.ShopNameAndIdDto;
import com.lanke.foodie.dto.ShopSearchPropertyDto;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopCollect;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.entity.ShopType;
import com.lanke.foodie.service.DetailService;
import com.lanke.foodie.service.UserDetailService;
import com.lanke.foodie.simpleEntity.EasyShopDetail;
import com.lanke.foodie.userdto.EasyShopDto;
import com.lanke.foodie.userdto.EntireShopDetail;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userdto.ShopDetailDto;
import com.lanke.foodie.userparams.ShopInSiteAndTypeParam;

import com.lanke.foodie.utils.BaseUtils;
import com.lanke.foodie.utils.ESUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailDao userDetailDao;


    public PageResults findAllShopDetails(ShopInSiteAndTypeParam param) {
        PageHelper.startPage(param.getPage(), param.getSize());

        List<ShopDetailDto> shopDetailDtos =  userDetailDao.findAllShopDetails(param);
        for(ShopDetailDto shops:shopDetailDtos){
            shops.setDistanceDetail(ESUtils.formatDistance(shops.getDistance()));
        }
        Page<ShopDetailDto> page=   (Page<ShopDetailDto>)  shopDetailDtos;
        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public PageResults findAllShopDetailsByMark(ShopInSiteAndTypeParam param) {
        PageHelper.startPage(param.getPage(), param.getSize());

        List<ShopDetailDto> shopDetailDtos =  userDetailDao.findAllShopDetailsByMark(param);
        for(ShopDetailDto shops:shopDetailDtos){
            shops.setDistanceDetail(ESUtils.formatDistance(shops.getDistance()));
        }
        Page<ShopDetailDto> page=   (Page<ShopDetailDto>)  shopDetailDtos;

        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public PageResults findAllShopDetailsByTypeId(ShopInSiteAndTypeParam param) {
        PageHelper.startPage(param.getPage(), param.getSize());

        List<ShopDetailDto> shopDetailDtos =  userDetailDao.findAllShopDetailsByTypeId(param);
        for(ShopDetailDto shops:shopDetailDtos){
            shops.setDistanceDetail(ESUtils.formatDistance(shops.getDistance()));
        }
        Page<ShopDetailDto> page=   (Page<ShopDetailDto>)  shopDetailDtos;

        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public PageResults findAllShopDetailsByMarkAndTypeId(ShopInSiteAndTypeParam param) {
        PageHelper.startPage(param.getPage(), param.getSize());
        List<ShopDetailDto> shopDetailDtos =  userDetailDao.findAllShopDetailsByMarkAndTypeId(param);
        for(ShopDetailDto shops:shopDetailDtos){
            shops.setDistanceDetail(ESUtils.formatDistance(shops.getDistance()));
        }
        Page<ShopDetailDto> page=   (Page<ShopDetailDto>)  shopDetailDtos;

        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public EntireShopDetail getEntireShopDetail(Integer shopId) {

        return userDetailDao.getEntireShopDetail(shopId);
    }

    public EasyShopDetail getEasyShopDetail(Integer shopId) {
        return userDetailDao.getEasyShopDetail(shopId);
    }

    public PageResults getShopCollect(Integer pageNum, Integer pageSize,Integer userId,Double lat,Double lon) {
        PageHelper.startPage(pageNum,pageSize);
        List<ShopDetailDto> shopDetailDtos =  userDetailDao.getShopCollect(userId,lat,lon);
        for(ShopDetailDto shops:shopDetailDtos){
            shops.setDistanceDetail(ESUtils.formatDistance(shops.getDistance()));
        }
        Page<ShopDetailDto> page=   (Page<ShopDetailDto>)shopDetailDtos;

        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public Integer checkShopCollect(Integer userId, Integer shopId) {
        return userDetailDao.checkShopCollect(userId,shopId);
    }

    public Integer deleteShopCollect(Integer userId, Integer shopId) {
        return userDetailDao.deleteShopCollect(userId,shopId);
    }

    public Integer addShopCollect(ShopCollect collect) {
        collect.setCreateTime(BaseUtils.getTime());
        return userDetailDao.addShopCollect(collect);
    }

    public EasyShopDto getEasyShop(Integer shopId) {
        return userDetailDao.getEasyShop(shopId);
    }

    public String getShopPhoneById(Integer shopId) {
        return userDetailDao.getShopPhoneById(shopId);
    }
}
