package com.lanke.foodie.controller;

import com.lanke.foodie.entity.MoneyOff;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.enums.ShopType;
import com.lanke.foodie.json.BaseJson;

import com.lanke.foodie.repository.ShopRepository;
import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.service.DishService;
import com.lanke.foodie.service.ShopRepositoryService;
import com.lanke.foodie.service.ShopService;
import com.lanke.foodie.simpleEntity.EasyShopDetail;
import com.lanke.foodie.simpleEntity.SimpleDish;
import com.lanke.foodie.userdto.*;
import com.lanke.foodie.userparams.ShopInSiteAndTypeParam;

import com.lanke.foodie.userparams.ShopSearchParam;
import com.lanke.foodie.utils.ESUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopRepositoryService shopRepositoryService;
    @RequestMapping(value = "/usershopdetail/findAllShopDetails",method = RequestMethod.GET)
    public PageResults findAllShopDetails(ShopInSiteAndTypeParam param){

        PageResults pageResults =shopService.findAllShopDetails(param);
        pageResults.setCode(0);
        pageResults.setMessage("成功");
        return  pageResults;

    }

    @RequestMapping(value = "/usershopdetail/findAllShopDetailsByMark",method = RequestMethod.GET)
    public PageResults findAllShopDetailsByMark(ShopInSiteAndTypeParam param){

        PageResults pageResults =shopService.findAllShopDetailsByMark(param);
        pageResults.setCode(0);
        pageResults.setMessage("成功");
        return  pageResults;

    }

    @RequestMapping(value = "/usershopdetail/findAllShopDetailsByTypeId",method = RequestMethod.GET)
    public PageResults findAllShopDetailsByTypeId(ShopInSiteAndTypeParam param){

        PageResults pageResults = shopService.findAllShopDetailsByTypeId(param);
        pageResults.setCode(0);
        pageResults.setMessage("成功");
        return  pageResults;

    }

    @RequestMapping(value = "/usershopdetail/findAllShopDetailsByMarkAndTypeId",method = RequestMethod.GET)
    public PageResults findAllShopDetailsByMarkAndTypeId(ShopInSiteAndTypeParam param){
        PageResults pageResults = shopService.findAllShopDetailsByMarkAndTypeId(param);

        pageResults.setCode(0);
        pageResults.setMessage("成功");

        return  pageResults;
    }
    @RequestMapping(value = "/usershopdetail/getShopPhoneById/{shopId}",method = RequestMethod.GET)
    public BaseJson getShopPhoneById(@PathVariable("shopId") Integer shopId) {
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(shopService.getShopPhoneById(shopId));
        return  baseJson;

    }

//    @RequestMapping(value = "/usershopdetail/addShopSearchName/{searchName}",method = RequestMethod.GET)
//    public void addShopSearchName(@PathVariable("searchName") String searchName){
//
//        redisTemplate.boundZSetOps("shopSearchName").incrementScore(searchName,1L);
//
//    }
    @RequestMapping(value = "/usershopdetail/getShopHotSearchNames",method = RequestMethod.GET)
    public BaseJson getShopHotSearchNames(@RequestParam("city") String city){

        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(redisTemplate.boundZSetOps("shopSearchName_"+city).reverseRange(0,10));
        return  baseJson;

    }

    @RequestMapping(value = "/usershopdetail/getSearchPromptValue",method = RequestMethod.GET)
    public  PageResults getSearchPromptValue(@RequestParam("city") String city,@RequestParam("value") String value){
        List<ShopSearchPrompt> list = new ArrayList<ShopSearchPrompt>();

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("shopName",value))
                .should(QueryBuilders.termQuery("shopTypeName",value))
                .minimumShouldMatch(1)
                .filter( QueryBuilders.termQuery("shopCity", city))
        );

        queryBuilder.withPageable(PageRequest.of(0,20));
        Page<SearchShop> items = shopRepository.search(queryBuilder.build());
        for (SearchShop item : items) {
            ShopSearchPrompt searchPrompt = new ShopSearchPrompt();
            searchPrompt.setShopName(item.getShopName());
            searchPrompt.setShopNotice(item.getShopNotice());
            list.add(searchPrompt);

        }

        PageResults pageResults = new PageResults();
        pageResults.setRows(list);
        pageResults.setCode(0);
        pageResults.setMessage("成功");
        return  pageResults;
    }
    @RequestMapping(value = "/usershopdetail/searchShopByLocation",method = RequestMethod.GET)
    public SearchResult searchShopByLocation(ShopSearchParam searchParam){
        if(!redisTemplate.hasKey("shopSearchName_"+searchParam.getShopCity())){
            redisTemplate.expire("shopSearchName_"+searchParam.getShopCity(),1, TimeUnit.DAYS);
        }
        redisTemplate.boundZSetOps("shopSearchName_"+searchParam.getShopCity()).incrementScore(searchParam.getValue(),1L);

        SearchResult<SearchShopDto> searchResult= shopRepositoryService.getSearchValuesByLocation(searchParam);
        if(searchResult.getHasMore()){
            while(searchResult.getRows().size()<searchParam.getSize()){
                searchParam.addPage();

                SearchResult<SearchShopDto> searchResultNext = shopRepositoryService.getSearchValuesByLocation(searchParam);
                searchResult.getRows().addAll(searchResultNext.getRows());
                if(!searchResult.getHasMore()){
                    break;
                }
            }
        }
        searchResult.setShouldPage(searchParam.getPage());
        searchResult.setCode(0);
        searchResult.setMessage("成功");

        return  searchResult;
    }

    @RequestMapping(value = "/usershopdetail/searchShopByMark",method = RequestMethod.GET)
    public SearchResult searchShopByMark(ShopSearchParam searchParam){
        if(!redisTemplate.hasKey("shopSearchName_"+searchParam.getShopCity())){
            redisTemplate.expire("shopSearchName_"+searchParam.getShopCity(),1, TimeUnit.DAYS);
        }

        redisTemplate.boundZSetOps("shopSearchName_"+searchParam.getShopCity()).incrementScore(searchParam.getValue(),1L);
       // ShopRepositoryService service  = new ShopRepositoryService();
        SearchResult<SearchShopDto> searchResult= shopRepositoryService.getSearchValuesByMark(searchParam);
        if(searchResult.getHasMore()){
            while(searchResult.getRows().size()<searchParam.getSize()){
                searchParam.addPage();
                SearchResult<SearchShopDto> searchResultNext = shopRepositoryService.getSearchValuesByMark(searchParam);
                searchResult.getRows().addAll(searchResultNext.getRows());
                if(!searchResult.getHasMore()){
                    break;
                }
            }
        }
        searchResult.setShouldPage(searchParam.getPage());
        searchResult.setCode(0);
        searchResult.setMessage("成功");

        return  searchResult;
    }
    @RequestMapping(value = "/usershopdetail/getEntireShopDetail/{shopId}",method = RequestMethod.GET)
    public BaseJson getEntireShopDetail(@PathVariable("shopId") Integer shopId){
        EntireShopDetail shopDetail = shopService.getEntireShopDetail(shopId);
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(shopDetail);

        return baseJson;
    }
    @RequestMapping(value = "/usershopdetail/getEasyShopDetail/{shopId}",method = RequestMethod.GET)
    public BaseJson getEasyShopDetail(@PathVariable("shopId")  Integer shopId){

        EasyShopDetail shopDetail = shopService.getEasyShopDetail(shopId);

        List<MoneyOff> moneyOffs = dishService.findMoneyOffByIds(shopDetail.getMoneyOffIds());

        String fullNum="",minusNum="";
        for(MoneyOff m:moneyOffs){
            fullNum+=m.getFullNum().toString()+',';
            minusNum+=m.getMinusNum().toString()+',';
        }
        shopDetail.setFullNum(fullNum);
        shopDetail.setMinusNum(minusNum);
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(shopDetail);

        return baseJson;
    }

    @RequestMapping(value = "/usershopdetail/getEasyShop/{shopId}",method = RequestMethod.GET)
    public BaseJson getEasyShop(@PathVariable("shopId") Integer shopId) {

        EasyShopDto easyShop = shopService.getEasyShop(shopId);

        BaseJson baseJson =new BaseJson();
        baseJson.setCode(Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(easyShop);

        return baseJson;

    }

    @RequestMapping(value = "/usershopdetail/getNearShop",method = RequestMethod.GET)
    public  BaseJson getNearShop(@RequestParam("city") String city,@RequestParam("mylat") Double mylat,@RequestParam("mylon") Double mylon){

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //       以某点为中心，搜索指定范围
        GeoDistanceQueryBuilder distanceQueryBuilder = new GeoDistanceQueryBuilder("location");
        distanceQueryBuilder.point(mylat, mylon);        // 定义查询单位：公里
        distanceQueryBuilder.distance("10", DistanceUnit.KILOMETERS);
        boolQueryBuilder.filter(distanceQueryBuilder);
        queryBuilder.withQuery(
                QueryBuilders.boolQuery().must(QueryBuilders.termQuery("shopCity",city))
                       .must(QueryBuilders.termQuery("typeId", ShopType.Shop.getIndex())
                        ).must(boolQueryBuilder)
        );




        GeoDistanceSortBuilder distanceSortBuilder =  new GeoDistanceSortBuilder("location", mylat, mylon);
        distanceSortBuilder.unit(DistanceUnit.KILOMETERS);
        distanceSortBuilder.order(SortOrder.ASC);
        queryBuilder.withSort(distanceSortBuilder);

        queryBuilder.withPageable(PageRequest.of(0,20));
        Page<SearchShop> items = shopRepository.search(queryBuilder.build());
        List<SearchFindShopDto> list = new ArrayList<>();

        for (SearchShop item : items) {
            SearchFindShopDto shopDto = new SearchFindShopDto();
            shopDto.setShopName(item.getShopName());
            shopDto.setShopNotice(item.getShopNotice());
            shopDto.setShopCity(item.getShopCity());
            shopDto.setPhotoUrl(item.getPhotoUrl());
            shopDto.setShopNotice(item.getShopNotice());
            shopDto.setShopSales(item.getShopSales());
            shopDto.setShopMark(item.getShopMark());
            shopDto.setFullNum(item.getFullNum());
            shopDto.setMinusNum(item.getMinusNum());
            shopDto.setShopId(item.getShopId());
            log.info(item.getShopName());

            double calculateDistance = GeoDistance.ARC.calculate(mylat, mylon, item.getLocation().getLat(), item.getLocation().getLon(), DistanceUnit.KILOMETERS);
            String formatDistance = ESUtils.formatDistance(calculateDistance);
            shopDto.setDistance(formatDistance);
            list.add(shopDto);

        }
        BaseJson baseJson =new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(list);

        return  baseJson;
    }
//    @PostConstruct
//    public void init(){
//        redisTemplate.expire("shopSearchName",1, TimeUnit.DAYS);
//    }

}
