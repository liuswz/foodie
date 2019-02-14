package com.lanke.foodie.dao;

import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.dto.ShopUpdateDto;
import com.lanke.foodie.entity.PayDetail;
import com.lanke.foodie.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface DetailDao {


    public int addShop(RegistDto registDto);

    public int addPay(RegistDto registDto);

    public int checkUsername(@Param("username") String username);

    public int checkShopName(@Param("shopName") String shopName);

    public int updateShop(ShopUpdateDto shopUpdateDto);

    public int updatePay(ShopUpdateDto shopUpdateDto);

    public Shop getShopById(@Param("id") Integer id);

    public List<Integer> checkShopNameForUpdate(@Param("shopName") String shopName);

    public PayDetail getShopDetailById(@Param("id") Integer payDetailId);
}
