package com.lanke.foodie.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.DetailDao;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ShopIdAndCityDto;
import com.lanke.foodie.dto.ShopNameAndIdDto;

import com.lanke.foodie.dto.ShopSearchPropertyDto;
import com.lanke.foodie.entity.DishType;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.entity.ShopType;
import com.lanke.foodie.enums.Authority;
import com.lanke.foodie.service.DetailService;

import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Slf4j
@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailDao detailDao;

    public Integer regist(Shop shop) {

        //获取当前时间
        shop.setCreateTime(BaseUtils.getTime());

     //   registDto.setCreate_time(dateStr);

        //注册时商家设为未审核状态
        shop.setShopStatus(0);
        shop.setOperateStatus(0);
        //判断用户名是否存在
        int checkUsername = detailDao.checkUsername(shop.getUsername());
        if(checkUsername > 0){
            return 3;
        }

        //判断店名是否存在
        int checkShopName = detailDao.checkShopName(shop.getShopName());
        if(checkShopName > 0){
            return 4;
        }
        //初始化status为0
        shop.setShopStatus(0);
      //  shop.setAuthority(Authority.Common.getName());
       // log.info(DigestUtils.md5Hex(shop.getPassword())+"-------------------");
        shop.setPassword(DigestUtils.md5Hex(shop.getPassword()));
     //   int flag2 = detailDao.addPay(shop);
        int flag = detailDao.addShop(shop);
    //    int flag = flag1 + flag2;
        return flag;
    }

    public Integer addShopDetails(ShopDetails shopDetails) {
        return detailDao.addShopDetails(shopDetails);
    }

    public Integer addShopType(ShopType shopType) {
        shopType.setCreateTime(BaseUtils.getTime());

        if(detailDao.checkShopType(shopType.getTypeName())==0){
            return detailDao.addShopType(shopType);
        }else{

            return 0;
        }

    }

    public List<ShopType> findAllShopType() {
        return detailDao.findAllShopType();
    }


    public Integer updateShop(Shop shop) {

        //校验店名是否存在
        List<Integer> ids = detailDao.checkShopNameForUpdate(shop.getShopName());
        if(ids.size() > 0 && ids.get(0) != shop.getId()){
            return 4;
        }
        shop.setPassword(DigestUtils.md5Hex(shop.getPassword()));
        int flag = detailDao.updateShop(shop);
       // int flag2 = detailDao.updatePay(shop);
      //  int flag = flag1 + flag2;
        return flag;
    }

    public Integer updateShopDetails(ShopDetails shopDetails) {

        return detailDao.updateShopDetails(shopDetails);
    }


    public Shop getShopById(Integer id) {
       // Shop shopInfoDto = new Shop();
        Shop shop = detailDao.getShopById(id);
      //  BeanUtils.copyProperties(shop,shopInfoDto);
     //   PayDetail payDetail = detailDao.getShopDetailById(shop.getPayDetailId());
      //  shopInfoDto.setMchId(payDetail.getMchId());
     //   shopInfoDto.setApiKey(payDetail.getApiKey());
        return shop;
    }

    public Integer getShopStatusById(Integer id) {
        return detailDao.getShopStatusById(id);
    }

    public ShopDetails getShopDetailsById(Integer shopId) {

        return detailDao.getShopDetailsById(shopId);
    }

    public ShopNameAndIdDto getNameAndIdByUsername(String username) {
        return detailDao.getNameAndIdByUsername(username);
    }

//    public String getAuthorityByUsername(String username) {
//        return detailDao.getAuthorityByUsername(username);
//    }

    public PageResult findAllShop(Integer pageNum, Integer pageSize,String value) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Shop> page=   (Page<Shop>) detailDao.findAllShop(value);
        return new PageResult(page.getTotal(), page.getResult());

    }

//    public PageResult findShopByStatus(ShopSearchPropertyDto shopSearchPropertyDto, Integer pageNum, Integer pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        Page<Shop> page=   (Page<Shop>) detailDao.findShopByStatus(shopSearchPropertyDto);
//        return new PageResult(page.getTotal(), page.getResult());
//
//    }

    public PageResult findShopByStatusAndCity(ShopSearchPropertyDto shopSearchPropertyDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<Shop> page=null;
        if("".equals(shopSearchPropertyDto.getShopCity())||shopSearchPropertyDto.getShopCity()==null){
            page =   (Page<Shop>) detailDao.findShopByStatus(shopSearchPropertyDto);

        }else{
            page=   (Page<Shop>) detailDao.findShopByStatusAndCity(shopSearchPropertyDto);

        }


        return new PageResult(page.getTotal(), page.getResult());

    }

    public Integer updateShopStatus(Integer id) {
        ShopDetails shopDetails = new ShopDetails();
        shopDetails.setCommentNum(0);
        shopDetails.setShopSales(0);
        shopDetails.setLatitude(0d);
        shopDetails.setLongitude(0d);
        shopDetails.setShopId(id);
        shopDetails.setCreateTime(BaseUtils.getTime());

        return detailDao.updateShopStatus(id)+detailDao.addShopDetails(shopDetails);
    }

    public Integer updateOperaterStatus(Integer id, Integer value) {
        return detailDao.updateOperaterStatus(id,value);
    }

    public Integer getOperaterStatus(Integer id) {
        return detailDao.getOperaterStatus(id);
    }

    public Integer deleteShop(Integer id) {
        detailDao.deleteShopDetails(id);
        return detailDao.deleteShop(id);
    }



    public Integer deleteShopDetails(Integer shopId) {
        return detailDao.deleteShopDetails(shopId);
    }

    public String findPayPhoto(Integer id) {
        return detailDao.findPayPhoto(id);
    }

    public String getPassword(String username) {
        return detailDao.getPassword(username);
    }

    public String getShopPassword(String username) {
        return detailDao.getShopPassword(username);
    }

    public Integer delShopTypeById(String ids) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";

        return detailDao.delShopTypeById(ids);
    }

    public Integer getIfShopByTypeId(String ids) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";
        return detailDao.getIfShopByTypeId(ids);
    }

    public String getShopNameById(Integer id) {
        return detailDao.getShopNameById(id);
    }

    public ShopIdAndCityDto getIdAndCityByUsername(String username) {
        return detailDao.getIdAndCityByUsername(username);
    }
}
