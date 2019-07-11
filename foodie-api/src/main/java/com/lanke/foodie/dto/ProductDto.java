package com.lanke.foodie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ProductDto {

    private Integer id;
    private String productName;
    private String productIntro;
    private Integer productTypeId;
    private String typeName;
    private Integer productSales;

    private String moneyOffIds;

    private Double priceForShop;
    private Double priceForUser;
    private String photoUrl;

}
