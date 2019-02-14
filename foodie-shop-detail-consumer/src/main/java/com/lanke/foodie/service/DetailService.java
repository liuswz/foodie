package com.lanke.foodie.service;

import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.dto.ShopInfoDto;
import com.lanke.foodie.dto.ShopUpdateDto;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "FOODIE-SHOPDETAIL")
public interface DetailService {


    @RequestMapping(value = "/shopdetail/register",method = RequestMethod.POST)
    public Integer regist(@RequestBody RegistDto registDto  );

    @RequestMapping(value = "/shopdetail/update",method = RequestMethod.POST)
    public Integer update(@RequestBody ShopUpdateDto shopUpdateDto);

    @RequestMapping(value = "/shopdetail/getById",method = RequestMethod.GET)
    public ShopInfoDto getById(@RequestParam("id") Integer id);
}
