package com.lanke.foodie.dto;

import com.lanke.foodie.entity.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class OrderItemDto extends OrderItem {

    private String name;
    private String photoUrl;
    private Integer shopId;
}
