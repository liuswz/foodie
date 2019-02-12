package com.lanke.foodie.service;

import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.dto.ShopUpdateDto;

public interface DetailService {
    public int regist(RegistDto registDto);

    public int update(ShopUpdateDto shopUpdateDto);
}
