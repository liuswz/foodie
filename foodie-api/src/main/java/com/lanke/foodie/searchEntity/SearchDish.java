//package com.lanke.foodie.searchEntity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//import org.springframework.data.elasticsearch.annotations.Parent;
//
//import java.io.Serializable;
//
//@NoArgsConstructor
//@Data
//@Accessors(chain=true)
//@AllArgsConstructor
//@Document(indexName = "shop",type = "doc", shards = 1, replicas = 0)
//public class SearchDish implements Serializable {
//    @Id
//    private Integer id;
//
//    @Field(index = false,type = FieldType.Integer,store=true)
//    @Parent(type="shopdetail")
//    private String shopId;
//
//    @Field(type = FieldType.Text, analyzer = "ik_max_word")
//    private String name;
//    @Field(type = FieldType.Double)
//    private double price;
//    @Field(index = false, type = FieldType.Keyword)
//    private String photoUrl;
//
//}
