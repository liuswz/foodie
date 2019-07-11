package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
//用户使用商家的代金卷
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class VoucherForUser {
    private Integer id;
    private Integer shopId;
    private Integer userId;

    private Integer voucherId;
    private Double money;
    private String startDate;
    private String deadLine;

    private String createTime;
}
