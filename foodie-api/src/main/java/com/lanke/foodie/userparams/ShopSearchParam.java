package com.lanke.foodie.userparams;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class ShopSearchParam {
    private String value;

    private String shopCity;
    private Double longitude;
    private Double latitude;
    private Integer page;
    private Integer size;
    public void addPage(){
        page++;
    }
}
