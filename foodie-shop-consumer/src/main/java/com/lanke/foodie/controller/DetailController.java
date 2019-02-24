package com.lanke.foodie.controller;

import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.DetailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class DetailController {

    @Autowired
    private DetailService detailService;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/gotoAdmin")
    public void enter_admin(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://127.0.0.1:82/admin");
    }
    @RequestMapping(value = "/getUsername",method = RequestMethod.GET)
    public BaseJson getUsername(HttpSession session)  {
        BaseJson baseJson = new BaseJson();


        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(session.getAttribute("loginName").toString());



        return baseJson;
    }
    @RequestMapping("/logout")
    public void logout1(HttpSession session,HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("http://localhost:8088/cas/logout");
        // 直接退出，走默认退出方式

    }


    @ApiOperation(value = "商家入驻", notes = "商家管理")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", value = "用户名", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", value = "密码", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "shopName", dataType = "String", value = "店名", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "shopAddress", dataType = "String", value = "店家地址", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "shopEmail", dataType = "String", value = "店家邮箱", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "shopPhone", dataType = "String", value = "店家电话", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "mchId", dataType = "String", value = "支付id", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "apiKey", dataType = "String", value = "支付key", defaultValue = "")
    })


    @RequestMapping(value = "/consumer/shopdetail/register",method = RequestMethod.POST)
    public BaseJson regist(Shop shop){

     //   log.info(shop.getBusinessPhoto()+"-----------------------------");
        BaseJson baseJson = new BaseJson();
        int flag = detailService.regist(shop);
        if(flag == 1){
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
        }else {
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("注册失败");
        }
        return baseJson;
    }

    @RequestMapping(value = "/consumer/shopdetail/update",method = RequestMethod.POST)
    public BaseJson update(Shop shop){
        BaseJson baseJson = new BaseJson();
        int flag = detailService.update(shop);
        if(flag == 1){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("修改成功");
        }else if(flag == 4){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("店名已存在");
       }
//        else {
//            baseJson.setCode(1);
//            baseJson.setMessage("失败");
//        }
        return baseJson;
    }

    @RequestMapping(value = "/consumer/shopdetail/getById/{id}",method = RequestMethod.GET)
    public BaseJson getById(@PathVariable("id") Integer id){
        BaseJson baseJson = new BaseJson();
        Shop shop = detailService.getById(id);
        if(shop != null){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult(shop);
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;

    }

    @RequestMapping(value = "/consumer/shopdetail/createTemplate/{id}/{username}",method = RequestMethod.GET)
    public BaseJson create_template(@PathVariable("id") String id,@PathVariable("username") String username){

        Map map=new HashMap<String,String>();
        map.put("id", id);
        map.put("username", username);
        jmsMessagingTemplate.convertAndSend("statics", map);
        BaseJson baseJson = new BaseJson();


        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult("生成静态页面成功");

        return baseJson;

    }
}
