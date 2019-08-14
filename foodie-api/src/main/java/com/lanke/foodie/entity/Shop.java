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
    private String shopName;
    private String shopTypeId;
    private String shopTypeName;
    private String shopCity;
    private String shopAddress;
    private String shopEmail;
    private String businessPhoto;
    private String idcardPhoto;
    private String payPhoto;
    private String shopPhone;
  //  private Integer payDetailId;
    private Integer shopStatus;
    private Integer operateStatus;
    private String createTime;
    private String photoUrl;




  //  private String authority;
}
