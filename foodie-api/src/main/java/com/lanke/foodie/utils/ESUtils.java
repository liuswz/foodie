package com.lanke.foodie.utils;

import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.enums.GoodType;
import com.lanke.foodie.enums.ShopType;
import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.simpleEntity.SimpleDish;
import com.lanke.foodie.userdto.SearchShopDto;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class ESUtils {

    public static SearchShop setSearchShop(ShopDetails shopDetails,String fullNum,String minusNum){
        SearchShop searchShop = new SearchShop();
        searchShop.setId(getShopId(shopDetails.getShopId()));
        searchShop.setShopId(shopDetails.getShopId());
        searchShop.setShopName(shopDetails.getShopName());
        searchShop.setShopCity(shopDetails.getShopCity());
        searchShop.setShopTypeName(shopDetails.getShopTypeName());
        searchShop.setPhotoUrl(shopDetails.getPhotoUrl());
        searchShop.setShopNotice(shopDetails.getShopNotice());
        searchShop.setShopSales(shopDetails.getShopSales());
        searchShop.setShopMark(shopDetails.getShopMark());
        searchShop.setLocation(new GeoPoint(shopDetails.getLatitude(),shopDetails.getLongitude()));
        searchShop.setFullNum(fullNum);
        searchShop.setMinusNum(minusNum);
        searchShop.setTypeId(ShopType.Shop.getIndex());
        return searchShop;
    }
    public static SearchShop setSearchDish(ShopDetails shopDetails,Dish dish){
        SearchShop searchShop = new SearchShop();

        searchShop.setId(getDishId(dish.getId()));
        searchShop.setShopId(shopDetails.getShopId());
        searchShop.setShopCity(shopDetails.getShopCity());
        searchShop.setDishName(dish.getName());
        searchShop.setDishPhotoUrl(dish.getPhotoUrl());
        searchShop.setDishPrice(dish.getPrice());
        searchShop.setShopMark(shopDetails.getShopMark());
        searchShop.setLocation(new GeoPoint(shopDetails.getLatitude(),shopDetails.getLongitude()));
        searchShop.setTypeId(ShopType.Dish.getIndex());
        return searchShop;
    }
    public static SearchShop setSearchDishAndId(ShopDetails shopDetails,Dish dish,Integer id){
        SearchShop searchShop = new SearchShop();
        searchShop.setId(getDishId(id));
        searchShop.setDishName(dish.getName());
        searchShop.setDishPhotoUrl(dish.getPhotoUrl());
        searchShop.setDishPrice(dish.getPrice());
        searchShop.setShopId(shopDetails.getShopId());
        searchShop.setShopCity(shopDetails.getShopCity());
        searchShop.setShopMark(shopDetails.getShopMark());
        searchShop.setLocation(new GeoPoint(shopDetails.getLatitude(),shopDetails.getLongitude()));
        searchShop.setTypeId(ShopType.Dish.getIndex());
        return searchShop;
    }

    public static Integer getDishId(Integer id){
        return id*2+2;
    }
    public static Integer getShopId(Integer id){
        return id*2+1;
    }

    public static SearchShopDto setSearchShopDto(SearchShop shop){
        SearchShopDto searchShop = new SearchShopDto();
        searchShop.setId(shop.getShopId());
        searchShop.setShopName(shop.getShopName());
        searchShop.setShopCity(shop.getShopCity());
        searchShop.setShopTypeName(shop.getShopTypeName());
        searchShop.setPhotoUrl(shop.getPhotoUrl());
        searchShop.setShopNotice(shop.getShopNotice());
        searchShop.setShopSales(shop.getShopSales());
        searchShop.setShopMark(shop.getShopMark());
        searchShop.setFullNum(shop.getFullNum());
        searchShop.setMinusNum(shop.getMinusNum());
   //     searchShop.setShopId(shop.getShopId());

        return searchShop;
    }
    public static SearchShopDto setSearchShopDtoByExist(SearchShopDto searchShop,SearchShop shop){

        searchShop.setId(shop.getShopId());
        searchShop.setShopName(shop.getShopName());
        searchShop.setShopCity(shop.getShopCity());
        searchShop.setShopTypeName(shop.getShopTypeName());
        searchShop.setPhotoUrl(shop.getPhotoUrl());
        searchShop.setShopNotice(shop.getShopNotice());
        searchShop.setShopSales(shop.getShopSales());
        searchShop.setShopMark(shop.getShopMark());
        searchShop.setFullNum(shop.getFullNum());
        searchShop.setMinusNum(shop.getMinusNum());
        //     searchShop.setShopId(shop.getShopId());

        return searchShop;
    }
    public static SearchShopDto setSearchShopDtoByShopDetail(ShopDetails shop){
        SearchShopDto searchShop = new SearchShopDto();
        searchShop.setId(shop.getShopId());
        searchShop.setShopName(shop.getShopName());
        searchShop.setShopCity(shop.getShopCity());
        searchShop.setShopTypeName(shop.getShopTypeName());
        searchShop.setPhotoUrl(shop.getPhotoUrl());
        searchShop.setShopNotice(shop.getShopNotice());
        searchShop.setShopSales(shop.getShopSales());
        searchShop.setShopMark(shop.getShopMark());

        return searchShop;
    }
    public static SimpleDish setSimpleDish(SearchShop shop){

        SimpleDish simpleDish = new SimpleDish();
        simpleDish.setDishName(shop.getDishName());
        simpleDish.setDishPhotoUrl(shop.getDishPhotoUrl());
        simpleDish.setDishPrice(shop.getDishPrice());
        return simpleDish;
    }

    public static String formatDistance(double distance) {
        if (distance == 0) {
            return "0m";
        } else if (distance > 1) {
            distance = (double) Math.round(distance * 100) / 100;
            String s = new StringBuilder().append(distance).append("km").toString();
            return s;
        } else {
            distance = distance * 1000;
            String s = new StringBuilder().append((int) distance).append("m").toString();
            return s;
        }
    }
}
