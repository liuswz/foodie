package com.lanke.foodie.service;


import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ShopIdAndCityDto;
import com.lanke.foodie.dto.ShopNameAndIdDto;
import com.lanke.foodie.dto.ShopSearchPropertyDto;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.entity.ShopType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DetailService {
    public Integer regist(Shop shop);
    public Integer addShopDetails(ShopDetails shopDetails);
    public Integer addShopType(ShopType shopType);
    public List<ShopType>  findAllShopType();

    public Integer updateShop(Shop shop);
    public Integer updateShopDetails(ShopDetails shopDetails);
//    public Integer updateDiscountDetails(ShopDetails shopDetails);
    public Integer updateShopStatus(Integer id);
    public Integer updateOperaterStatus(Integer id,Integer value);
    public Integer getOperaterStatus(@Param("id") Integer id);

    public Shop getShopById(Integer id);
    public Integer getShopStatusById(Integer id);
    public ShopDetails getShopDetailsById( Integer shopId);

    public ShopNameAndIdDto getNameAndIdByUsername(String username);

    //public String getAuthorityByUsername(String username);

    public PageResult findAllShop(Integer pageNum, Integer pageSize, String value);


    //public PageResult findShopByStatus(ShopSearchPropertyDto shopSearchPropertyDto,Integer pageNum, Integer pageSize);
    public PageResult findShopByStatusAndCity(ShopSearchPropertyDto shopSearchPropertyDto,Integer pageNum, Integer pageSize);

    public Integer deleteShop(Integer id);

    public Integer deleteShopDetails( Integer shopId);

    public String findPayPhoto(Integer id);

    public String getPassword( String username);
    public String getShopPassword( String username);

    public Integer delShopTypeById(String ids);
    public Integer getIfShopByTypeIds(String ids);

    public String getShopNameById(Integer id);

    public ShopIdAndCityDto getIdAndCityByUsername(String username);
}
