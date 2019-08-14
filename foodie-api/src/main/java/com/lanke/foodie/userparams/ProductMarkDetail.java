package com.lanke.foodie.userparams;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ProductMarkDetail {
    private Double productMark;
    private Integer commentNum;
}
