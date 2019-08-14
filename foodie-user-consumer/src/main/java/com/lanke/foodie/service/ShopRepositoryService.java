package com.lanke.foodie.service;

import com.lanke.foodie.repository.ShopRepository;
import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.simpleEntity.SimpleDish;
import com.lanke.foodie.userdto.SearchResult;
import com.lanke.foodie.userdto.SearchShopDto;
import com.lanke.foodie.userparams.ShopSearchParam;
import com.lanke.foodie.utils.ESUtils;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShopRepositoryService{
    @Autowired
    private ShopRepository shopRepository;

    public SearchResult<SearchShopDto> getSearchValuesByLocation(ShopSearchParam searchParam){

        String value = searchParam.getValue();
        Double mylat = searchParam.getLatitude();
        Double mylon = searchParam.getLongitude();

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询

        queryBuilder.withQuery(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("shopName",value))
                .should(QueryBuilders.matchQuery("dishName",value))
                .should(QueryBuilders.termQuery("shopTypeName",value))
                .minimumShouldMatch(1)
                .filter( QueryBuilders.termQuery("shopCity", searchParam.getShopCity()))
        );


        GeoDistanceSortBuilder distanceSortBuilder =
                new GeoDistanceSortBuilder("location", mylat,mylon);
        distanceSortBuilder.unit(DistanceUnit.KILOMETERS);
        distanceSortBuilder.order(SortOrder.ASC);
        queryBuilder.withSort(distanceSortBuilder);

        queryBuilder.withPageable(PageRequest.of(searchParam.getPage()-1,searchParam.getSize()*2));

        Page<SearchShop> items = shopRepository.search(queryBuilder.build());

        if(items==null){
            SearchResult<SearchShopDto> searchResult=new SearchResult<>();
            searchResult.setRows(new ArrayList<SearchShopDto>());
            searchResult.setHasMore(false);
            return  searchResult;
        }

        // 总条数
        long total = items.getTotalElements();
        boolean hasMore = true;
        if(total<(searchParam.getSize()*2)){
            hasMore = false;
        }
        return getSearchShopList(items, mylat,  mylon,hasMore);

    }
    public SearchResult<SearchShopDto> getSearchValuesByMark(ShopSearchParam searchParam){

        String value = searchParam.getValue();
        Double mylat = searchParam.getLatitude();
        Double mylon = searchParam.getLongitude();

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("shopName",value))
                .should(QueryBuilders.matchQuery("dishName",value))
                .should(QueryBuilders.termQuery("shopTypeName",value))
                .minimumShouldMatch(1)
                .filter( QueryBuilders.termQuery("shopCity", searchParam.getShopCity()))
        );

        queryBuilder.withSort(SortBuilders.fieldSort("shopMark").order(SortOrder.DESC));

        queryBuilder.withPageable(PageRequest.of(searchParam.getPage()-1,searchParam.getSize()*2));

        Page<SearchShop> items = shopRepository.search(queryBuilder.build());

        if(items==null){
            SearchResult<SearchShopDto> searchResult=new SearchResult<>();
            searchResult.setRows(new ArrayList<SearchShopDto>());
            searchResult.setHasMore(false);
            return  searchResult;
        }
        long total = items.getTotalElements();
        boolean hasMore = true;
        if(total<(searchParam.getSize()*2)){
            hasMore = false;
        }
        return getSearchShopList(items, mylat,  mylon,hasMore);

    }

    public   SearchResult<SearchShopDto> getSearchShopList( Page<SearchShop> items,Double mylat, Double mylon ,boolean hasMore){
        List<SearchShopDto> searchShopDtos = new ArrayList<SearchShopDto>();
        for (SearchShop item : items) {
            if(item.getShopName()==null&&item.getDishName()!=null){
                SimpleDish simpleDish= ESUtils.setSimpleDish(item);
                boolean flag=true;
                for(SearchShopDto searchShopDto:searchShopDtos){
                    if(searchShopDto.getId()==item.getShopId()){
                        if(searchShopDto.getDishList()==null){
                            List<SimpleDish> list = new ArrayList<>();
                            list.add(simpleDish);
                            searchShopDto.setDishList(list);
                        }else{
                            searchShopDto.getDishList().add(simpleDish);
                        }
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    SearchShopDto searchShopDto=new SearchShopDto();
                    List<SimpleDish> list = new ArrayList<>();
                    list.add(simpleDish);
                    searchShopDto.setId(item.getShopId());
                    searchShopDto.setDishList(list);
                    searchShopDtos.add(searchShopDto);
                }

            }else{
                boolean flag=true;
                for(SearchShopDto searchShopDto:searchShopDtos){
                    if(searchShopDto.getShopName()==null&&searchShopDto.getId()==item.getShopId()){
                        searchShopDto = ESUtils.setSearchShopDto(item);
                        double calculateDistance = GeoDistance.ARC.calculate(mylat, mylon, item.getLocation().getLat(), item.getLocation().getLon(), DistanceUnit.KILOMETERS);
                        String formatDistance = ESUtils.formatDistance(calculateDistance);
                        searchShopDto.setDistance(formatDistance);
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    SearchShopDto searchShopDto = ESUtils.setSearchShopDto(item);
                    double calculateDistance = GeoDistance.ARC.calculate(mylat, mylon, item.getLocation().getLat(), item.getLocation().getLon(), DistanceUnit.KILOMETERS);
                    String formatDistance = ESUtils.formatDistance(calculateDistance);
                    searchShopDto.setDistance(formatDistance);
                    searchShopDtos.add(searchShopDto);
                }

            }
            System.out.println(item);
        }
        for(SearchShopDto searchShopDto:searchShopDtos){
            if(searchShopDto.getShopName()==null){
                SearchShop searchShop = shopRepository.findById(ESUtils.getShopId(searchShopDto.getId())).get();
                searchShopDto = ESUtils.setSearchShopDtoByExist(searchShopDto,searchShop);
                double calculateDistance = GeoDistance.ARC.calculate(mylat, mylon, searchShop.getLocation().getLat(), searchShop.getLocation().getLon(), DistanceUnit.KILOMETERS);
                String formatDistance = ESUtils.formatDistance(calculateDistance);
                searchShopDto.setDistance(formatDistance);

            }

        }

        SearchResult<SearchShopDto> searchResult=new SearchResult<>();
        searchResult.setRows(searchShopDtos);
        searchResult.setHasMore(hasMore);
        return  searchResult;
    }
}
