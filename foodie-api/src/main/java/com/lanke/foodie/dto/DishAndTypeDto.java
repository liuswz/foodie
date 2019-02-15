package com.lanke.foodie.dto;

import com.lanke.foodie.entity.Dish;
import com.lanke.foodie.entity.DishType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class DishAndTypeDto {

    private Dish dish;
    private List<DishType> typelist;
}
