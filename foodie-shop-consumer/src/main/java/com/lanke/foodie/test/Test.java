package com.lanke.foodie.test;


import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
//    @Autowired
//    private ShopRepository shopRepository;
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

    @org.junit.Test
    public void testTTT() throws Exception {
//        double lat = 0.9D;
//        double lon = 0.8D;
//        String value ="拿";
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        // 添加基本分词查询
//        queryBuilder.withQuery(QueryBuilders.matchQuery("shopName", value));
//        // 搜索，获取结果
//        Page<SearchShop> items = shopRepository.search(queryBuilder.build());
//        // 总条数
//        long total = items.getTotalElements();
//        System.out.println("total = " + total);
//        for (SearchShop item : items) {
//            System.out.println(item);
//        }
        System.out.println(getPhotoName());
    }

    public static String getPhotoName(){
        return  new Date().getTime()+""+((int)(new Random().nextDouble()*(99999-10000 + 1))+ 10000)+"";
    }
}
