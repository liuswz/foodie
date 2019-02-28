package com.lanke.foodie.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dto.PageResult;
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
//    @RequestMapping(value = "/shopdetail/getAuthorityByUsername/{username}",method = RequestMethod.GET)
//    public String getAuthorityByUsername(@PathVariable("username") String username) {
//        return detailService.getAuthorityByUsername(username);
//    }

    @RequestMapping(value = "/shopdetail/findAllShop",method = RequestMethod.GET)
    public PageResult findAllShop(@RequestParam("page") Integer page, @RequestParam("size") Integer size,@RequestParam("value") String value) {
        return detailService.findAllShop(page,size,value);

    }
    @RequestMapping(value = "/shopdetail/updateStatus/{id}",method = RequestMethod.GET)
    public Integer updateStatus(@PathVariable("id") Integer id) {
        return detailService.updateStatus(id);
    }

    @RequestMapping(value = "/shopdetail/deleteShop/{id}",method = RequestMethod.GET)
    public Integer deleteShop(@PathVariable("id") Integer id) {
        return detailService.deleteShop(id);
    }

    @RequestMapping(value = "/shopdetail/findPayPhoto/{id}",method = RequestMethod.GET)
    public String findPayPhoto(@PathVariable("id") Integer id) {
        return detailService.findPayPhoto(id);
    }

    @RequestMapping(value = "/shopdetail/getPassword/{username}",method = RequestMethod.GET)
    public String getPassword(@PathVariable("username") String username) {
        return detailService.getPassword(username);
    }
}
