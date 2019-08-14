package com.lanke.foodie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class HotSearchForProduct {

    private Integer id;
    private String searchName;

    private String createTime;
}
