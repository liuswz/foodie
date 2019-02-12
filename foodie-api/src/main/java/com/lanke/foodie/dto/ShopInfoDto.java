package com.lanke.foodie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class ShopInfoDto {

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

    private String mch_id;
    private String api_key;
}
