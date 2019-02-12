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
public class Order implements Serializable {

    private Integer id;
    private String order_num;
    private Integer order_status;
    private double cost;
    private Integer shop_id;
    private String create_time;
}
