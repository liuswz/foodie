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
    private String shopame;
    private String shopAddress;
    private String shopEmail;
    private String shopPhone;
    private Integer payDetailId;
    private Integer shopStatus;
    private String createTime;

    private Integer id;
    private String mchId;
    private String apiKey;
}
