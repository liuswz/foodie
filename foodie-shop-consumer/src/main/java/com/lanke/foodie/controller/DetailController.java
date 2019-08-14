package com.lanke.foodie.controller;


import com.lanke.foodie.dto.ShopAndShopTypeDto;
import com.lanke.foodie.dto.ShopDetailsAndMoneyOffDto;
import com.lanke.foodie.dto.ShopIdAndCityDto;
import com.lanke.foodie.dto.ShopNameAndIdDto;

import com.lanke.foodie.entity.MoneyOff;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.entity.ShopType;
import com.lanke.foodie.enums.OperateStatus;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.repository.ShopRepository;
import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.service.DetailService;
import com.lanke.foodie.service.DishService;
import com.lanke.foodie.utils.ESUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/shopconsumer")
public class DetailController {

    @Autowired
    private DetailService detailService;
    @Autowired
    private DishService dishService;

//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private ShopRepository shopRepository;

    @RequestMapping(value = "/shopdetail/login",method = RequestMethod.POST)
    public BaseJson login(HttpSession session,@RequestBody Shop shop){
        BaseJson baseJson = new BaseJson();


        String password  =detailService.getShopPassword(shop.getUsername());

        if(password!=null){
            if(password.equals(DigestUtils.md5Hex(shop.getPassword()))){
                baseJson.setCode(0);
                baseJson.setMessage("成功");
                baseJson.setResult("登入成功");

                session.setAttribute("username", shop.getUsername());
            }else{
                baseJson.setCode(1);
                baseJson.setMessage("失败");
                baseJson.setResult("用户名或密码错误");
            }
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("用户名或密码错误");

        }

//||
        return baseJson;
    }
    @RequestMapping(value = "/shopdetail/logout",method = RequestMethod.GET)
    public BaseJson logout(HttpSession session){
        BaseJson baseJson = new BaseJson();

        session.invalidate();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult("登出成功");

        return baseJson;
    }
    //@CrossOrigin(origins="http://localhost:82",allowCredentials = "true")
    @RequestMapping(value = "/shopdetail/getDetails",method = RequestMethod.GET)
    public BaseJson getDetails(HttpSession session){
        BaseJson baseJson = new BaseJson();


        baseJson.setCode(0);
        baseJson.setMessage("成功");

        baseJson.setResult(detailService.getIdAndCityByUsername(session.getAttribute("username").toString()));

        return baseJson;
    }
//    @RequestMapping(value = "/shopdetail/getUsername",method = RequestMethod.GET)
//    public ShopIdAndCityDto getUsername(HttpSession session){
//        return  detailService.getIdAndCityByUsername(session.getAttribute("username").toString());
//    }

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
    @RequestMapping(value = "/shopdetail/register",method = RequestMethod.POST)
    public BaseJson regist(Shop shop){

        //log.info(shop.getBusinessPhoto()+"-----------------------------");
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

    @RequestMapping(value = "/shopdetail/updateShop",method = RequestMethod.POST)
    public BaseJson updateShop(@RequestBody  Shop shop){
        BaseJson baseJson = new BaseJson();
        int flag = detailService.updateShop(shop);
        if(flag == 1){
            if(detailService.getOperaterStatus(shop.getId())==1){
                ShopDetails shopDetails = detailService.getShopDetailsById(shop.getId());

                List<MoneyOff> moneyOffs = dishService.findMoneyOffByIds(shopDetails.getMoneyOffIds());
                String fullNum="",minusNum="";
                for(MoneyOff m:moneyOffs){
                    fullNum+=m.getFullNum().toString()+',';
                    minusNum+=m.getMinusNum().toString()+',';
                }
                SearchShop searchShop = ESUtils.setSearchShop(shopDetails,fullNum,minusNum);
                shopRepository.save(searchShop);

                Map<String,Integer> map = new HashMap<String, Integer>();
                map.put("id",shop.getId());
                map.put("flag",0);
                rabbitTemplate.convertAndSend("statics", map);
            }




            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("修改成功");
        }else if(flag == 4){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("店名已存在");
       }
        return baseJson;
    }

    @RequestMapping(value = "/shopdetail/getShopById/{id}",method = RequestMethod.GET)
    public ShopAndShopTypeDto getShopById(@PathVariable("id") Integer id){

        ShopAndShopTypeDto shopAndShopTypeDto = new ShopAndShopTypeDto();
        shopAndShopTypeDto.setShop(detailService.getShopById(id));
        shopAndShopTypeDto.setShopTypeList(detailService.findAllShopType());
        return  shopAndShopTypeDto;
    }

    @RequestMapping(value = "/shopdetail/updateShopDetails",method = RequestMethod.POST)
    public BaseJson updateShopDetails(@RequestBody  ShopDetails shopDetails){
        BaseJson baseJson = new BaseJson();
        int flag = detailService.updateShopDetails(shopDetails);
        if(flag >0){
            if(detailService.getOperaterStatus(shopDetails.getShopId())==OperateStatus.HadOperate.getIndex()){
                ShopDetails shopDetails2 = detailService.getShopDetailsById(shopDetails.getShopId());
                List<MoneyOff> moneyOffs = dishService.findMoneyOffByIds(shopDetails.getMoneyOffIds());
                String fullNum="",minusNum="";
                for(MoneyOff m:moneyOffs){
                    fullNum+=m.getFullNum().toString()+',';
                    minusNum+=m.getMinusNum().toString()+',';
                }
                SearchShop searchShop = ESUtils.setSearchShop(shopDetails2,fullNum,minusNum);
                shopRepository.save(searchShop);

                Map<String,Integer> map = new HashMap<String, Integer>();
                map.put("id",shopDetails.getShopId());
                map.put("flag",0);
                rabbitTemplate.convertAndSend("statics", map);
            }




            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("修改成功");
        }else {
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("修改失败");
        }
        return baseJson;
    }

    @RequestMapping(value = "/shopdetail/getShopDetailsById/{shopId}",method = RequestMethod.GET)
    public ShopDetailsAndMoneyOffDto getShopDetailsById(@PathVariable("shopId") Integer shopId){
        ShopDetailsAndMoneyOffDto shopDetailsAndMoneyOffDto = new ShopDetailsAndMoneyOffDto();
        shopDetailsAndMoneyOffDto.setMoneyOffList(dishService.findAllMoneyOff());
        shopDetailsAndMoneyOffDto.setShopDetails(detailService.getShopDetailsById(shopId));

        return shopDetailsAndMoneyOffDto;
    }


    @RequestMapping(value = "/shopdetail/createTemplate/{id}",method = RequestMethod.GET)
    public BaseJson create_template(@PathVariable("id") Integer id){

        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("id",id);
        map.put("flag",0);
        rabbitTemplate.convertAndSend("statics", map);
        BaseJson baseJson = new BaseJson();


        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult("生成静态页面成功");

        return baseJson;

    }

    @RequestMapping(value = "/shopdetail/findAllShopType",method = RequestMethod.GET)
    public List<ShopType> findAllShopType() {

        return detailService.findAllShopType();
    }


    @RequestMapping(value = "/shopdetail/startOperater/{id}",method = RequestMethod.GET)
    public BaseJson startOperater(@PathVariable("id") Integer id) {

        BaseJson baseJson = new BaseJson();
        int operaterStatus = detailService.getOperaterStatus(id);   //
        if(operaterStatus == OperateStatus.HadOperate.getIndex()){
            baseJson.setCode(0);
            baseJson.setMessage("失败");
            baseJson.setResult("您已经运营了");
        }else {
            int num = dishService.checkDishByShopId(id);   //
            if(num>0){
                int flag = detailService.updateOperaterStatus(id,OperateStatus.HadOperate.getIndex());
                if(flag >0){

                    Map<String,Integer> map = new HashMap<String, Integer>();
                    map.put("id",id);
                    map.put("flag",1);
                    rabbitTemplate.convertAndSend("statics", map);


                    baseJson.setCode(0);
                    baseJson.setMessage("成功");
                    baseJson.setResult("运营成功");
                }else {
                    baseJson.setCode(1);
                    baseJson.setMessage("失败");
                    baseJson.setResult("运营失败");
                }
            }else{
                baseJson.setCode(0);
                baseJson.setMessage("失败");
                baseJson.setResult("您没上传任何菜品，无法运营");
            }

        }
        return baseJson;

    }

    @RequestMapping(value = "/shopdetail/cancelOperater/{id}",method = RequestMethod.GET)
    public BaseJson cancelOperater(@PathVariable("id") Integer id) {

        BaseJson baseJson = new BaseJson();
        int operaterStatus = detailService.getOperaterStatus(id);   //
        if(operaterStatus == OperateStatus.NotOperate.getIndex()){
            baseJson.setCode(0);
            baseJson.setMessage("失败");
            baseJson.setResult("您还没开始运营");
        }else {

            int flag = detailService.updateOperaterStatus(id,0);
            if(flag >0){

                rabbitTemplate.convertAndSend("delete_searchdata", id);
                baseJson.setCode(0);
                baseJson.setMessage("成功");
                baseJson.setResult("取消运营成功");
            }else {
                baseJson.setCode(1);
                baseJson.setMessage("失败");
                baseJson.setResult("取消运营失败");
            }
        }
        return baseJson;

    }


    @RequestMapping(value = "/shopdetail/getOperateStatus/{id}",method = RequestMethod.GET)
    public BaseJson getOperateStatus(@PathVariable("id") Integer id) {

        BaseJson baseJson = new BaseJson();
        int operaterStatus = detailService.getOperaterStatus(id);   //

        baseJson.setCode(1);
        baseJson.setMessage("成功");
        baseJson.setResult(operaterStatus);



        return baseJson;

    }
}
