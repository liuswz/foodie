package com.lanke.sms.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
@AllArgsConstructor
public class TestDto {
    private String shopName;
    private double distance;

}
