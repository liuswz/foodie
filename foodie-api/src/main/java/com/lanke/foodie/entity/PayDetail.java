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
public class PayDetail implements Serializable {

    private Integer id;
    private String mch_id;
    private String api_key;
    private String create_time;
}
