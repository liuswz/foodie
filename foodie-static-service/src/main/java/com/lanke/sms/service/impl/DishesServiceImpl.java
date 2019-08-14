package com.lanke.sms.service.impl;


import com.lanke.foodie.dto.DishesDto;
import com.lanke.foodie.entity.MoneyOff;
import com.lanke.foodie.entity.ShopDetails;

import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.simpleEntity.SimpleShop;
import com.lanke.foodie.dto.TypeToDishDto;
import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;

import com.lanke.foodie.utils.ESUtils;
import com.lanke.sms.dao.DishesDao;

import com.lanke.sms.repository.ShopRepository;
import com.lanke.sms.service.DishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class DishesServiceImpl  implements DishesService {

    @Autowired
    private DishesDao dishesDao;
    @Autowired
    private ShopRepository shopRepository;
//    @Autowired
//    private DishRepository dishRepository;

    public List<DishType> findAllDishType(Integer shopId) {


        return dishesDao.findAllDishType(shopId);

    }
    public List<DishesDto> findAllDishes( Integer shopId){
        return  dishesDao.findAllDishes(shopId);
    }

    public List<TypeToDishDto> getTypeToDish(Integer shopId) {
        List<TypeToDishDto> typeToDishDtoList = new LinkedList<TypeToDishDto>();
        List<DishType> dishTypeList = dishesDao.findAllDishType(shopId);
        for(DishType dishType:dishTypeList){
            TypeToDishDto typeToDishDto = new TypeToDishDto();
            typeToDishDto.setDishType(dishType);
            typeToDishDto.setDishList(dishesDao.getDishByTypeId(dishType.getId()));
            typeToDishDtoList.add(typeToDishDto);
        }
        return typeToDishDtoList;
    }

    public SimpleShop getShopById(Integer shopId) {
        return dishesDao.getShopById(shopId);
    }

    public void takeToES(Integer shopId) {
        ShopDetails shopDetails = dishesDao.getShopDetailsById(shopId);
        String ids = shopDetails.getMoneyOffIds();
        String fullNum="",minusNum="";

        if(ids!=null){
            ids = "("+ids.substring(0,ids.length() - 1)+")";
            List<MoneyOff> moneyOffs = dishesDao.findMoneyOffByIds(ids);
            for(MoneyOff m:moneyOffs){
                fullNum+=m.getFullNum().toString()+",";
                minusNum+=m.getMinusNum().toString()+",";
                log.info(fullNum);
            }
        }


        SearchShop searchShop = ESUtils.setSearchShop(shopDetails,fullNum,minusNum);

        shopRepository.save(searchShop);
        List<Dish>  dishList= dishesDao.getDishByShopId(shopId);

        List<SearchShop> searchShopList = new ArrayList<SearchShop>();
        for(Dish dish : dishList){
            searchShopList.add( ESUtils.setSearchDish(shopDetails,dish));
        }
        shopRepository.saveAll(searchShopList);
    }

    public void deleteSearchData(Integer shopId) {
        shopRepository.deleteById(ESUtils.getShopId(shopId));
        List<Dish>  dishList= dishesDao.getDishByShopId(shopId);
        List<SearchShop> list= new ArrayList<SearchShop>();
        for(Dish dish : dishList){
            SearchShop searchDish= new SearchShop();
            searchDish.setId(ESUtils.getDishId(dish.getId()));
            list.add(searchDish);
        }
        shopRepository.deleteAll(list);
    }


    public Dish getDishById(Integer id) {
        return dishesDao.getDishById(id);
    }



}
