package com.lanke.foodie.repository;

import com.lanke.foodie.searchEntity.SearchShop;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ShopRepository extends ElasticsearchRepository<SearchShop,Integer> {


}
