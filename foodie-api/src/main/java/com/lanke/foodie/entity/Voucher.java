package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Voucher {

    private Integer id;
    private Integer shopId;
    private Double money;
    private String startDate;
    private String deadLine;
    private String city;
    private String createTime;
}
