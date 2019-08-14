package com.lanke.foodie.userdto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ProductUserDto  implements Serializable {

    private Integer id;
    private String productName;
    private String productTypeName;
    private Integer productSales;
    private String productIntro;
    private Double priceForShop;
    private Double priceForUser;
    private String photoUrl;
    private String moneyOffIds;
    private String fullNum;
    private String minusNum;
}
