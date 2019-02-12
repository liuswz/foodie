package com.lanke.foodie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class RegistDto {

    private String username;
    private String password;
    private String shop_name;
    private String shop_address;
    private String shop_email;
    private String shop_phone;
    private Integer pay_detail_id;
    private Integer shop_status;
    private String create_time;

    private Integer id;
    private String mch_id;
    private String api_key;
}
