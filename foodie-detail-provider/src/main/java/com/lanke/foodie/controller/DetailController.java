package com.lanke.foodie.controller;


import com.lanke.foodie.dto.ShopNameAndIdDto;
import com.lanke.foodie.entity.Shop;
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
    public Integer regist(@RequestBody Shop shop  ){
       // log.info("测试{}，日志级别{}，输出{}", "demo1", "info", "info level log");
     /*   BaseJson baseJson = new BaseJson();
        //log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
      */
        return detailService.regist(shop);
    }

    @RequestMapping(value = "/shopdetail/update",method = RequestMethod.POST)
    public Integer update(@RequestBody Shop shop){
        return detailService.update(shop);
    }

    @RequestMapping(value = "/shopdetail/getById/{id}",method = RequestMethod.GET)
    public Shop getById(@PathVariable("id") Integer id){
        return detailService.getById(id);
    }

    @RequestMapping(value = "/shopdetail/getNameAndIdByUsername/{username}",method = RequestMethod.GET)
    public ShopNameAndIdDto getNameAndIdByUsername(@PathVariable("username") String username){
        return detailService.getNameAndIdByUsername(username);
    }
}
