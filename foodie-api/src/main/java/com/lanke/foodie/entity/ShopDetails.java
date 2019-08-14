package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ShopDetails implements Serializable {
    private Integer id;
    private Integer shopId;


    private String shopIntro;
    private String shopNotice;
    private Double longitude;
    private Double latitude;
    private Double shopMark;
    private Integer commentNum;
    private Integer shopSales;

    private String moneyOffIds;

    private String shopPhoto1;
    private String shopPhoto2;
    private String shopPhoto3;
    private String shopPhoto4;
    private String shopPhoto5;

    private String createTime;
    private Integer operateStatus;
    private String shopName;
    private String shopCity;
    private String shopTypeName;
    private String photoUrl;
}
