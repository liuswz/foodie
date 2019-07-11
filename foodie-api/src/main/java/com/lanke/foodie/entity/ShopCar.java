package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ShopCar {

    private Integer id;
    private Integer goodId;
    private Integer goodNum;
    private Double totalCost;
    private Integer userType;
    private Integer userId;
    private String createTime;
}
