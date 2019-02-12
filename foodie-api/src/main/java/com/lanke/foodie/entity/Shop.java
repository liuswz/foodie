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
public class Shop implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String shop_name;
    private String shop_address;
    private String shop_email;
    private String shop_phone;
    private Integer pay_detail_id;
    private Integer shop_status;
    private String create_time;
}
