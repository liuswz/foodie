package com.lanke.foodie.dao;



import com.lanke.foodie.dto.ShopIdAndCityDto;
import com.lanke.foodie.dto.ShopNameAndIdDto;
import com.lanke.foodie.dto.ShopSearchPropertyDto;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.entity.ShopType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface DetailDao {


    public Integer addShop(Shop shop);
    public Integer addShopDetails(ShopDetails shopDetails);
    public Integer addShopType(ShopType shopType);
    public Integer checkShopType(@Param("typeName") String typeName);
    public List<ShopType> findAllShopType();

    public Integer updateShop(Shop shop);
    public Integer updateShopDetails(ShopDetails shopDetails);
    public Integer updateDiscountDetails(ShopDetails shopDetails);
    public Integer updateShopStatus(@Param("id") Integer id);
    public Integer updateOperaterStatus(@Param("id") Integer id,@Param("value") Integer value);
    public Integer getOperaterStatus(@Param("id") Integer id);

    public List<Shop> findShopByStatus(ShopSearchPropertyDto shopSearchPropertyDto);
    public List<Shop> findShopByStatusAndCity(ShopSearchPropertyDto shopSearchPropertyDto);


    public Shop getShopById(@Param("id") Integer id);
    public int getShopStatusById(@Param("id") Integer id);
    public ShopDetails getShopDetailsById(@Param("shopId") Integer shopId);
   // public int addPay(RegistDto registDto);

    public int checkUsername(@Param("username") String username);

    public int checkShopName(@Param("shopName") String shopName);
//    public String getAuthorityByUsername(@Param("username") String username);



   // public int updatePay(ShopUpdateDto shopUpdateDto);


    public List<Integer> checkShopNameForUpdate(@Param("shopName") String shopName);
    public ShopNameAndIdDto getNameAndIdByUsername(@Param("username") String username);
  //  public PayDetail getShopDetailById(@Param("id") Integer payDetailId);

    public List<Shop> findAllShop(@Param("value") String value);

    public Integer deleteShop(@Param("id") Integer id);

    public Integer deleteShopDetails(@Param("id") Integer shopId);

    public String findPayPhoto(@Param("id") Integer id);

    public String getPassword(@Param("username") String username);
    public String getShopPassword(@Param("username") String username);

    public Integer delShopTypeById(@Param("ids") String ids);
    public Integer getIfShopByTypeId(@Param("ids") String ids);
    public String getShopNameById(@Param("id") Integer id);

    public ShopIdAndCityDto getIdAndCityByUsername(@Param("username") String username);

}
