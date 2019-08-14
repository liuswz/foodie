package com.lanke.foodie.userdto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class DishDto   implements Serializable {
    private Integer id;
    private String name;
    private double price;
    private String introduction;
    private String photoUrl;
    private Integer dishSales;
    private Integer shopId;


}
