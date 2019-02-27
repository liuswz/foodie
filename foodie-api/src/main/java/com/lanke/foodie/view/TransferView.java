package com.lanke.foodie.view;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain=true)
public class TransferView {
    private String  payPhoto;
    private Double totalCost;
}
