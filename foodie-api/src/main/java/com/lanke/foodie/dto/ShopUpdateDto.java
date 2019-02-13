package com.lanke.foodie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class ShopUpdateDto {

    private Integer id;
    private String username;
    private String password;
    private String shopName;
    private String shopAddress;
    private String shopEmail;
    private String shopPhone;
    private Integer payDetailId;
    private Integer shopStatus;
    private String createTime;

    private String mchId;
    private String apiKey;
}
