package com.lanke.foodie.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
@AllArgsConstructor
public class ShopSearchPrompt implements Serializable {
    private String shopName;
    private String shopNotice;
}
