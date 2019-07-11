package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class MoneyOff {

    private Integer id;
    private Double fullNum;
    private Double minusNum;

    private String createTime;
}
