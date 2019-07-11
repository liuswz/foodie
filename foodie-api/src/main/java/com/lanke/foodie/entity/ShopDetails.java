package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ShopDetails {
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


  //  private Double voucher;

    private String createTime;
}
