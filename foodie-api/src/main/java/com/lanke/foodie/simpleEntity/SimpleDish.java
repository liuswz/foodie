package com.lanke.foodie.simpleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
@AllArgsConstructor
public class SimpleDish implements Serializable {
    private Integer id;

    private String dishName;
    private double dishPrice;
    private String dishPhotoUrl;

}
