package com.lanke.foodie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ShopSearchPropertyDto {
    private String shopCity;
    private Integer shopStatus;
    private Integer operateStatus;

}
