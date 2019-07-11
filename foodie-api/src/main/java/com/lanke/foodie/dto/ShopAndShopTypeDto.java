package com.lanke.foodie.dto;

import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ShopAndShopTypeDto {
    private Shop shop;
    private List<ShopType> shopTypeList;
}
