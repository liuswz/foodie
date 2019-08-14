package com.lanke.foodie.searchEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
@AllArgsConstructor
@Document(indexName = "shop",type = "doc", shards = 1, replicas = 0)
public class SearchShop implements Serializable {
    //ik_smart
    @Id
    private Integer id;

//    @Field(type = FieldType.Integer)
//    private Integer shopId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String shopName;

    @Field(type = FieldType.Keyword)
    private String shopCity;
    @Field(type = FieldType.Keyword)
    private String shopTypeName;
    @Field(index = false, type = FieldType.Keyword)
    private String photoUrl;
    @Field(index = false,type = FieldType.Integer)
    private Double shopMark;
    @Field(index = false,type = FieldType.Integer)
    private Integer shopSales;
    @Field(index = false, type = FieldType.Text)
    private String shopNotice;
    @GeoPointField
    private GeoPoint location;

    private String fullNum;
    private String minusNum;
    private String moneyOffIds;

    private Double longitude;
    private Double latitude;


    @Field(type = FieldType.Integer)
    private Integer shopId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String dishName;
    @Field(type = FieldType.Double)
    private double dishPrice;
    @Field(index = false, type = FieldType.Keyword)
    private String dishPhotoUrl;

    @Field(type = FieldType.Integer)
    private Integer typeId;

    private String distance;
}
