package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Product {
    private Integer id;
    private String productName;
    private String productIntro;
    private Integer productTypeId;
    private Integer productSales;

    private String moneyOffIds;

    private Double priceForShop;
    private Double priceForUser;
    private String photoUrl;
    private String createTime;
}
