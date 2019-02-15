package com.lanke.foodie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class DishesDto {

    private Integer id;
    private String name;
    private double price;
    private String introduction;
    private String remark;
    private String photoUrl;
    private Integer typeId;
    private String typeName;
    private Integer shopId;
    private String ids;
}
