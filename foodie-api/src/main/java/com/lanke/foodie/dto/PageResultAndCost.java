package com.lanke.foodie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResultAndCost {

    private long total;;
    private List rows;
    private Double totalCost;

}
