package com.lanke.foodie.service;

import com.lanke.foodie.entity.Advertisement;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "FOODIE-DISHES")
public interface AdvertisementService {

    @RequestMapping(value = "/advertisement/getAdvertisementByCity/{city}",method = RequestMethod.GET)
    public List<Advertisement> getAdvertisementByCity(@PathVariable("city") String city);
}
