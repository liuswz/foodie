package com.lanke.foodie.userdto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ShopDetailDto {
    private Integer shopId;
    private String shopNotice;
    private Double shopMark;
    private Integer shopSales;
    private String moneyOffIds;

    private String fullNum;
    private String minusNum;
    private Double distance;
    private String distanceDetail;
    private String shopName;
    private String shopCity;
    private String shopTypeName;
    private String photoUrl;
}
