package com.lanke.foodie.userdto;

import com.lanke.foodie.simpleEntity.SimpleDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@Data
@Accessors(chain=true)
@AllArgsConstructor
public class SearchShopDto implements Serializable {

    private Integer id;
    private String shopName;
    private String shopCity;
    private String shopTypeName;
    private String photoUrl;
    private Double shopMark;
    private Integer shopSales;
    private String shopNotice;
    private String fullNum;
    private String minusNum;
    private String distance;
 //   private Integer shopId;
    private List<SimpleDish> dishList;
}
