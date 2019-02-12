package com.lanke.foodie.dao;

import com.lanke.foodie.dto.RegistDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DetailDao {


    public int addShop(RegistDto registDto);

    public int addPay(RegistDto registDto);

    public int checkUsername(@Param("username") String username);

    public int checkShopName(@Param("shopName") String shopName);
}
