package com.lanke.foodie.service;


import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ShopNameAndIdDto;
import com.lanke.foodie.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DetailService {
    public int regist(Shop shop);

    public int update(Shop shop);

    public Shop getById(Integer id);
    public ShopNameAndIdDto getNameAndIdByUsername(String username);

    //public String getAuthorityByUsername(String username);

    public PageResult findAllShop(Integer pageNum, Integer pageSize, String value);
    public Integer updateStatus(Integer id);
    public Integer deleteShop(Integer id);
    public String findPayPhoto(Integer id);
}
