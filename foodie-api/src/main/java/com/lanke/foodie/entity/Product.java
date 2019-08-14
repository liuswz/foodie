package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Product implements Serializable {
    private Integer id;
    private String productName;
    private String productIntro;

    private Integer productTypeId;
    private String productTypeName;

    private Integer productSales;
    private Double productMark;
    private Integer commentNum;

    private String moneyOffIds;

    private Double priceForShop;
    private Double priceForUser;
    private String photoUrl;
    private String createTime;
}
