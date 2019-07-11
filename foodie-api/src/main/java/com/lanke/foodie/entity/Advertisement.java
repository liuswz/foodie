package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Advertisement {
    private Integer id;
    private String city;
    private String photoUrl1;
    private String type1;
    private Integer redirectId1;

    private String photoUrl2;
    private String type2;
    private Integer redirectId2;

    private String photoUrl3;
    private String type3;
    private Integer redirectId3;

    private String photoUrl4;
    private String type4;
    private Integer redirectId4;

    private String photoUrl5;
    private String type5;
    private Integer redirectId5;

    private String createTime;
}
