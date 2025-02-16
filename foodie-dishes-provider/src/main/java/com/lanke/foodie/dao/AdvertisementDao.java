package com.lanke.foodie.dao;

import com.lanke.foodie.dto.AdvertisementsDto;
import com.lanke.foodie.entity.Advertisement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdvertisementDao {
    public List<AdvertisementsDto> findAllAdvertisement();
    public Integer addAdvertisement(Advertisement advertisement);

    public Integer checkAdvertisement(@Param("city") String city);
    public Integer delAdvertisementById(@Param("id") Integer id);
    public Advertisement getAdvertisementById(@Param("id") Integer id);
    public Integer updateAdvertisement(Advertisement advertisement);

    public List<Advertisement> getAdvertisementByCity(@Param("city")  String city);
    public Integer delAdvertisementByCity(@Param("city")  String city);

}
