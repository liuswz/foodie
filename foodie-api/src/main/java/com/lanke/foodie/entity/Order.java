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
    private String orderNo;
    private Integer orderStatus;
    private double cost;
    private Integer tableNum;
    private Integer shopId;
    private String createTime;
}
