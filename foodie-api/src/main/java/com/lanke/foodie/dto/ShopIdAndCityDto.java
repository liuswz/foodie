package com.lanke.foodie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ShopIdAndCityDto {
    private Integer id;
    private String username;
    private String shopCity;
}
