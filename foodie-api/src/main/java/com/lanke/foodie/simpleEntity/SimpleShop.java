package com.lanke.foodie.simpleEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class SimpleShop {

    private Integer id;
    private String username;
    private String shopName;
    private String shopTypeId;
    private String photoUrl;
}
