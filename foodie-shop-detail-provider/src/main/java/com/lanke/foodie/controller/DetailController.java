package com.lanke.foodie.controller;

import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailController {


    @Autowired
    private DetailService detailService;

    @RequestMapping(value = "/shop-detail/register",method = RequestMethod.GET)
    public BaseJson regist(RegistDto registDto){
        BaseJson baseJson = new BaseJson();
        int flag = detailService.regist(registDto);
        if(flag > 0){
            baseJson.setCode(0);
            baseJson.setResult("注册成功");
        }else {
            baseJson.setCode(1);
            baseJson.setResult("注册失败");
        }
        return baseJson;
    }
}
