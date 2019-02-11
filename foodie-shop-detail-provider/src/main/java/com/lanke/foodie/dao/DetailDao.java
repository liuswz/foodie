package com.lanke.foodie.dao;

import com.lanke.foodie.dto.RegistDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DetailDao {


    public int addShop(RegistDto registDto);

    public int addPay(RegistDto registDto);
}
