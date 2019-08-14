package com.lanke.foodie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class AdvertisementsDto {
    private String city;
    private String photoUrls;
//    private String typeIds;
//    private String redirectIds;
}
