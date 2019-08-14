package com.lanke.foodie.simpleEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class SimpleDishType implements Serializable {

    private Integer id;
    private String typeName;
}
