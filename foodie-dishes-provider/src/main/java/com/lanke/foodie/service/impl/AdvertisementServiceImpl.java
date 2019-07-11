package com.lanke.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.AdvertisementDao;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Advertisement;
import com.lanke.foodie.service.AdvertisementService;
import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementDao advertisementDao;
    public PageResult findAllAdvertisement(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Advertisement> page=   (Page<Advertisement>) advertisementDao.findAllAdvertisement();
        return new PageResult(page.getTotal(), page.getResult());
    }

    public Integer addAdvertisement(Advertisement advertisement) {

        advertisement.setCreateTime(BaseUtils.getTime());

        if(advertisementDao.checkAdvertisement(advertisement.getCity())==0){
            return advertisementDao.addAdvertisement(advertisement);
        }else{

            return 0;
        }
    }



    public Integer delAdvertisementById(Integer id) {
        return advertisementDao.delAdvertisementById(id);
    }

    public Advertisement getAdvertisementById(Integer id) {
        return advertisementDao.getAdvertisementById(id);
    }

    public Integer updateAdvertisement(Advertisement advertisement) {
        return advertisementDao.updateAdvertisement(advertisement);
    }
}
