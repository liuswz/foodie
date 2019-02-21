package com.lanke.foodie.dto;

import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class OrderAndItemDto {

    private Order order;
    private List<OrderItem> orderItemList;
}
