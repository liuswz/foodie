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
public class Dish implements Serializable {

    private Integer id;
    private String name;
    private double price;
    private String introduction;
    private String remark;
    private String photoUrl;
    private Integer typeId;
    private String createTime;
}
