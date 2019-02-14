package com.lanke.foodie.controller;

import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.dto.ShopInfoDto;
import com.lanke.foodie.dto.ShopUpdateDto;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.DetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DetailController {


    @Autowired
    private DetailService detailService;

    @RequestMapping(value = "/shopdetail/register",method = RequestMethod.POST)
    public Integer regist(@RequestBody RegistDto registDto  ){
       // log.info("测试{}，日志级别{}，输出{}", "demo1", "info", "info level log");
     /*   BaseJson baseJson = new BaseJson();
        //log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
      */
        return detailService.regist(registDto);
    }

    @RequestMapping(value = "/shopdetail/update",method = RequestMethod.POST)
    public Integer update(@RequestBody ShopUpdateDto shopUpdateDto){
        return detailService.update(shopUpdateDto);
    }

    @RequestMapping(value = "/shopdetail/getById",method = RequestMethod.GET)
    public ShopInfoDto getById(Integer id){
        return detailService.getById(id);
    }
}
