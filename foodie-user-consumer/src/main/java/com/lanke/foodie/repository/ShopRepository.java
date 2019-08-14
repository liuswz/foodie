package com.lanke.foodie.repository;

import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.userdto.SearchResult;
import com.lanke.foodie.userdto.SearchShopDto;
import com.lanke.foodie.userparams.ShopSearchParam;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ShopRepository extends ElasticsearchRepository<SearchShop,Integer> {


}
