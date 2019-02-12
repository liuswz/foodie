package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class OrderItem implements Serializable {

    private Integer id;
    private Integer dish_id;
    private Integer dish_num;
    private double total_cost;
    private Integer order_id;
    private String create_time;
}
