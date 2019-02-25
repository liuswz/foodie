package com.lanke.foodie.service;


import com.lanke.foodie.dto.ShopNameAndIdDto;
import com.lanke.foodie.entity.Shop;
import org.apache.ibatis.annotations.Param;

public interface DetailService {
    public int regist(Shop shop);

    public int update(Shop shop);

    public Shop getById(Integer id);
    public ShopNameAndIdDto getNameAndIdByUsername(String username);
}
