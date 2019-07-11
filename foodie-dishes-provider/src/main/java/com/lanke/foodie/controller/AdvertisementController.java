package com.lanke.foodie.controller;


import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Advertisement;
import com.lanke.foodie.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;


    @RequestMapping(value = "/advertisement/findAllAdvertisement/{page}/{size}",method = RequestMethod.GET)
    public PageResult findAllAdvertisement(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return advertisementService.findAllAdvertisement(page,size);
    }

    @RequestMapping(value = "/advertisement/addAdvertisement",method = RequestMethod.POST)
    public Integer addAdvertisement(@RequestBody Advertisement advertisement ){
        return advertisementService.addAdvertisement(advertisement);
    }
    @RequestMapping(value = "/advertisement/delAdvertisementById/{id}",method = RequestMethod.GET)
    public Integer delAdvertisementById(@PathVariable("id") Integer id) {
        return advertisementService.delAdvertisementById(id);
    }

    @RequestMapping(value = "/advertisement/getAdvertisementById/{id}",method = RequestMethod.GET)
    public Advertisement getAdvertisementById(@PathVariable("id") Integer id) {
        return advertisementService.getAdvertisementById(id);
    }
    @RequestMapping(value = "/advertisement/updateAdvertisement",method = RequestMethod.POST)
    public Integer updateAdvertisement(@RequestBody Advertisement advertisement){
        return advertisementService.updateAdvertisement(advertisement);
    }

}
