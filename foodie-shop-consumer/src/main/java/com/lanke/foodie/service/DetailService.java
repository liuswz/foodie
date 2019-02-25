package com.lanke.foodie.service;

import com.lanke.foodie.entity.Shop;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "FOODIE-DETAIL")
public interface DetailService {


    @RequestMapping(value = "/shopdetail/register",method = RequestMethod.POST)
    public Integer regist(@RequestBody Shop shop);

    @RequestMapping(value = "/shopdetail/update",method = RequestMethod.POST)
    public Integer update(@RequestBody Shop shop);

    @RequestMapping(value = "/shopdetail/getById/{id}",method = RequestMethod.GET)
    public Shop getById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/shopdetail/getIdByUsername/{username}",method = RequestMethod.GET)
    public Integer getIdByUsername(@PathVariable("username") String username);
}
