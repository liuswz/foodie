package com.lanke.foodie.dto;

import com.lanke.foodie.entity.Dish;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class DishStaticDto{

    private Integer id;
    private String name;
    private double price;
    private String photoUrl;
    private Integer typeId;
    private Integer num=0;
}
