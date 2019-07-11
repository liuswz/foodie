package com.lanke.foodie.service;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Advertisement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertisementService {

    public PageResult findAllAdvertisement(Integer pageNum, Integer pageSize);
    public Integer addAdvertisement(Advertisement advertisement);

    public Integer delAdvertisementById(Integer id);
    public Advertisement getAdvertisementById(Integer id);
    public Integer updateAdvertisement(Advertisement advertisement);
}
