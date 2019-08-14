package com.lanke.foodie.simpleEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class SimpleShopDetails {

    private Integer id;
    private Integer shopId;
    private String shopIntro;
    private String shopNotice;

    private String shopName;
    private String shopTypeName;
    private String photoUrl;
}
