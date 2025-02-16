package com.lanke.foodie.simpleEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class EasyShopDetail {

    private Integer shopId;
    private String shopNotice;
    private String shopName;
    private String shopPhoto1;
    private String photoUrl;
    private String fullNum;
    private String minusNum;
    private Double shopMark;
    private Integer shopSales;
    private String moneyOffIds;

    private Double longitude;
    private Double latitude;
}
