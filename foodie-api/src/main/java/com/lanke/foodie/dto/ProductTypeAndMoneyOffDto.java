package com.lanke.foodie.dto;

import com.lanke.foodie.entity.MoneyOff;
import com.lanke.foodie.entity.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ProductTypeAndMoneyOffDto {
    private List<ProductType> productTypeList;
    private List<MoneyOff> moneyOffList;

}
