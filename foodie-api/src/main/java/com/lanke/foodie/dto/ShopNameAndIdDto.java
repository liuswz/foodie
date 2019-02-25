package com.lanke.foodie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class ShopNameAndIdDto {
    private String shopName;
    private Integer id;
}
