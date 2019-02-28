package com.lanke.foodie.dto;

import com.lanke.foodie.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class OrderAndShopDto extends Order {

    private String shopName;
    private Integer totalCost;
}
