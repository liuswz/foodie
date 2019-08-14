package com.lanke.sms.test;


import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.simpleEntity.SimpleDish;
import com.lanke.foodie.userdto.SearchResult;
import com.lanke.foodie.userdto.SearchShopDto;
import com.lanke.foodie.utils.ESUtils;
import com.lanke.sms.repository.ShopRepository;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @org.junit.Test
    public void testTTT() throws Exception {
        double lat = 0.9D;
        double lon = 0.8D;
        String value ="汉山";
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

            // 添加基本分词查询
//            queryBuilder.withQuery(QueryBuilders.matchQuery("shopName", value));
//            queryBuilder.withQuery(QueryBuilders.matchQuery("shopName", value));
            GeoDistanceSortBuilder distanceSortBuilder =
                    new GeoDistanceSortBuilder("location", lat, lon);
            distanceSortBuilder.unit(DistanceUnit.KILOMETERS);

//            GeoDistanceQueryBuilder builder =
//                QueryBuilders.geoDistanceQuery("location")//查询字段
//                        .point(lat, lon)//设置经纬度
//                        .distance("100000", DistanceUnit.KILOMETERS)//设置距离和单位（米）
//                        .geoDistance(GeoDistance.ARC);

            distanceSortBuilder.order(SortOrder.ASC);
            queryBuilder.withSort(distanceSortBuilder);
//                    .withFilter(builder);
            // shopRepository.d
            // 搜索，获取结果
            queryBuilder.withPageable(PageRequest.of(0,20));

            Page<SearchShop> items = shopRepository.search(queryBuilder.build());

            // 总条数
            long total = items.getTotalElements();
            System.out.println("总条数 = " + total);


        List<SearchShopDto> searchShopDtos = new ArrayList<SearchShopDto>();
        for (SearchShop item : items) {
            if(item.getShopName()==null&&item.getDishName()!=null){
                SimpleDish simpleDish= ESUtils.setSimpleDish(item);
                boolean flag=true;
                for(SearchShopDto searchShopDto:searchShopDtos){
                    if(searchShopDto.getId()==item.getShopId()){
                        if(searchShopDto.getDishList()==null){
                            List<SimpleDish> list = new ArrayList<SimpleDish>();
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
                    List<SimpleDish> list = new ArrayList<SimpleDish>();
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
                        double calculateDistance = GeoDistance.ARC.calculate(lat, lon, item.getLocation().getLat(), item.getLocation().getLon(), DistanceUnit.KILOMETERS);
                        String formatDistance = ESUtils.formatDistance(calculateDistance);
                        searchShopDto.setDistance(formatDistance);
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    SearchShopDto searchShopDto = ESUtils.setSearchShopDto(item);
                    double calculateDistance = GeoDistance.ARC.calculate(lat, lon, item.getLocation().getLat(), item.getLocation().getLon(), DistanceUnit.KILOMETERS);
                    String formatDistance = ESUtils.formatDistance(calculateDistance);
                    searchShopDto.setDistance(formatDistance);
                    searchShopDtos.add(searchShopDto);
                }

            }
            System.out.println(item);
        }
//        for(SearchShopDto searchShopDto:searchShopDtos){
//            if(searchShopDto.getShopName()==null){
//                SearchShop searchShop = shopRepository.findById(ESUtils.getShopId(searchShopDto.getId())).get();
//                searchShopDto = ESUtils.setSearchShopDto(searchShop);
//                double calculateDistance = GeoDistance.ARC.calculate(mylat, mylon, searchShop.getLocation().getLat(), searchShop.getLocation().getLon(), DistanceUnit.KILOMETERS);
//                String formatDistance = ESUtils.formatDistance(calculateDistance);
//                searchShopDto.setDistance(formatDistance);
//
//            }
        //       }


            for (SearchShopDto item : searchShopDtos) {
             //   double calculateDistance = GeoDistance.ARC.calculate(lat, lon, item.getLocation().getLat(), item.getLocation().getLon(), DistanceUnit.KILOMETERS);
             //   String formatDistance = formatDistance(calculateDistance);
              //  item.setDistance(formatDistance);
                System.out.println(item);
            }

//        AggregatedPage<SearchShop> aggPage = (AggregatedPage<SearchShop>) items;
//        // 总条数
//        LongTerms agg = (LongTerms) aggPage.getAggregation("shopId");
//        // 3.2、获取桶
//        List<LongTerms.Bucket> buckets = agg.getBuckets();
//        // 3.3、遍历
//        for (LongTerms.Bucket bucket : buckets) {
//            // 3.4、获取桶中的key，即品牌名称
//
//            System.out.println(bucket.getKeyAsString());
//            // 3.5、获取桶中的文档数量
//            System.out.println(bucket.getDocCount());
//
//        }


    }


}
