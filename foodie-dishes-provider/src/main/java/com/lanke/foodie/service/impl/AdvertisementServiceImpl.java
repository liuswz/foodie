package com.lanke.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.constant.FoodieConstant;
import com.lanke.foodie.dao.AdvertisementDao;
import com.lanke.foodie.dto.AdvertisementDto;
import com.lanke.foodie.dto.AdvertisementsDto;
import com.lanke.foodie.dto.PageMapResult;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Advertisement;
import com.lanke.foodie.enums.AdType;
import com.lanke.foodie.service.AdvertisementService;
import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementDao advertisementDao;
    public PageMapResult findAllAdvertisement(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdvertisementsDto> advertisementDtos = advertisementDao.findAllAdvertisement();
        Page<AdvertisementsDto> page=   (Page<AdvertisementsDto>) advertisementDtos ;
        Map <String ,List<AdvertisementDto>>  hashMap=new  HashMap <String ,List<AdvertisementDto>>();
        for(AdvertisementsDto ad:advertisementDtos){
            List<AdvertisementDto> adlist= new ArrayList<AdvertisementDto>();
            String[] photoUrls = ad.getPhotoUrls().split(",");
//            String[] redirectIds = ad.getRedirectIds().split(",");
//            String[] typeIds = ad.getTypeIds().split(",");
            for(int i=0;i<photoUrls.length;i++){
                AdvertisementDto adDto = new AdvertisementDto();
                adDto.setCity(ad.getCity());
                adDto.setPhotoUrl(photoUrls[i]);
                adlist.add(adDto);
            }
            for(int i=0;i<FoodieConstant.maxAdNum-photoUrls.length;i++){
                AdvertisementDto adDto = new AdvertisementDto();
                adlist.add(adDto);
            }
            hashMap.put(ad.getCity(),adlist);
        }


        return new PageMapResult(page.getTotal(),hashMap);
    }

    public Integer addAdvertisement(Advertisement advertisement) {

        advertisement.setCreateTime(BaseUtils.getTime());
        int flag = advertisementDao.checkAdvertisement(advertisement.getCity());
        if(flag< FoodieConstant.maxAdNum&&flag>=0){
            return advertisementDao.addAdvertisement(advertisement);
        }else{

            return -1;
        }
    }



    public Integer delAdvertisementById(Integer id) {
        return advertisementDao.delAdvertisementById(id);
    }

    public Advertisement getAdvertisementById(Integer id) {
        return advertisementDao.getAdvertisementById(id);
    }

    public Integer updateAdvertisement(Advertisement advertisement) {
        advertisement.setCreateTime(BaseUtils.getTime());
        if(advertisement.getId()==null){
            return advertisementDao.addAdvertisement(advertisement);
        }else{
            return advertisementDao.updateAdvertisement(advertisement);
        }

    }

    public List<Advertisement> getAdvertisementByCity(String city) {
        List<Advertisement> list  = advertisementDao.getAdvertisementByCity(city);
        final int size = list.size();
        for(int i = 0;i<FoodieConstant.maxAdNum-size;i++){
            Advertisement ad = new Advertisement();
            ad.setCity(city);

            ad.setTypeId(AdType.Shop.getIndex());
            list.add(ad);
        }
        return list;
    }
    @CacheEvict(cacheNames = "advertisement", key="'ads_'+#city")
    public Integer delAdvertisementByCity(String city) {
        return advertisementDao.delAdvertisementByCity(city);
    }
}
