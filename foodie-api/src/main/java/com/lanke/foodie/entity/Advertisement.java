package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Advertisement implements Serializable {
    private Integer id;
    private String city;
    private String photoUrl;
    private Integer typeId;
    private Integer redirectId;

//    private String photoUrl2;
//    private String type2;
//    private Integer redirectId2;
//
//    private String photoUrl3;
//    private String type3;
//    private Integer redirectId3;
//
//    private String photoUrl4;
//    private String type4;
//    private Integer redirectId4;
//
//    private String photoUrl5;
//    private String type5;
//    private Integer redirectId5;

    private String createTime;
}
