package com.lanke.foodie.controller;

import com.lanke.foodie.dto.RegistDto;
import com.lanke.foodie.json.BaseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RestController
public class DetailController {


    private static final String REST_URL_PREFIX = "http://FOODIE-SHOPDETAIL";

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/consumer/shopdetail/register")
    public BaseJson regist(RegistDto registDto){
      //  log.info("测试{}，日志级别{}，输出{}", "demo1aaaaaaaaaaaaaaaaaaaaaa", "info", "info level log");
        BaseJson baseJson = new BaseJson();
       // log.info("测试{}，日志级别{}，输出{}", registDto.getMchId(), "info", "info level log");
       // int flag =detailService.regist(registDto);

        int flag = restTemplate.postForObject(REST_URL_PREFIX + "/shopdetail/register", registDto, Integer.class);
        if(flag == 2){
            baseJson.setCode(0);
            baseJson.setResult("注册成功");
        }else if(flag == 3){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("用户名已存在");
        }else if(flag == 4){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("店名已存在");
        }
        return baseJson;
    }

}
