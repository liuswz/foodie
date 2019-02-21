package com.lanke.foodie.service;

import com.lanke.foodie.dto.OrderAndItemDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(value = "FOODIE-SHOPORDERS")
public interface OrderService {

    @RequestMapping(value = "/shoporder/addOrder",method = RequestMethod.POST)
    public Integer addOrder(@RequestBody OrderAndItemDto orderAndItemDto );

}
