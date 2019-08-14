package com.lanke.foodie.controller;

import com.fasterxml.jackson.databind.ser.Serializers;

import com.lanke.foodie.dto.FindOrderParamsDto;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ShopSearchPropertyDto;
import com.lanke.foodie.entity.Operater;
import com.lanke.foodie.entity.Shop;
import com.lanke.foodie.entity.ShopType;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.json.BaseJson;


import com.lanke.foodie.service.DetailService;
import com.lanke.foodie.service.DishService;
import com.lanke.foodie.service.OrderService;
import com.lanke.foodie.view.TransferView;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/operaterconsumer")
public class DetailController {

    @Autowired
    private DetailService detailService;
    @Autowired
    private DishService dishService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AmqpTemplate rabbitTemplate;
//    @Autowired
//    private ShopRepository shopRepository;

    @RequestMapping(value = "/shopdetail/getShopById/{id}",method = RequestMethod.GET)
    public Shop getShopById(@PathVariable("id") Integer id){
        BaseJson baseJson = new BaseJson();
        Shop shop = detailService.getShopById(id);

        return shop;

    }

    @RequestMapping(value = "/shopdetail/findAllShop",method = RequestMethod.GET)
    public PageResult findAllShop(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("value") String value){
        return detailService.findAllShop(page,size,value);
    }
    @RequestMapping(value = "/shopdetail/findShopByStatus",method = RequestMethod.GET)
    public PageResult findShopByStatus(ShopSearchPropertyDto shopSearchPropertyDto, @RequestParam("page") Integer page, @RequestParam("size") Integer size){

//        ShopSearchPropertyDto searchPropertyDto=new ShopSearchPropertyDto();
//        searchPropertyDto.setShopCity(shopCity);
//        searchPropertyDto.setShopStatus(shopStatus);
//        searchPropertyDto.setOperateStatus(operateStatus);
        return detailService.findShopByStatus(page,size,shopSearchPropertyDto);
    }




    @RequestMapping(value = "/shopdetail/updateShopStatus/{id}",method = RequestMethod.GET)
    public BaseJson updateShopStatus(@PathVariable("id") Integer id){

        int flag = detailService.updateShopStatus(id);
        BaseJson baseJson = new BaseJson();
        if(flag >1){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("授权成功");
        }else if(flag==0){

            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("审核已通过");


        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("更改失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/shopdetail/deleteShop/{id}",method = RequestMethod.GET)
    public BaseJson deleteShop(@PathVariable("id") Integer id){

        int flag = detailService.deleteShop(id);
        dishService.delDishByShopId(id);
        dishService.delDishTypeByShopId(id);
        BaseJson baseJson = new BaseJson();
        if(flag >0){

          //  shopRepository.deleteById(id.longValue());

            rabbitTemplate.convertAndSend("delete_searchdata", id);
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("删除成功");
        }else {

            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("删除失败");


        }
        return baseJson;

    }

    @RequestMapping(value = "/shopdetail/findPayPhoto/{id}",method = RequestMethod.GET)
    public BaseJson findPayPhoto(@PathVariable("id") Integer id){
        String  payPhoto = detailService.findPayPhoto(id);
        BaseJson baseJson = new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(payPhoto);
        return baseJson;

    }
  //  @CrossOrigin(origins="http://localhost:82",allowCredentials = "true")
    @RequestMapping(value = "/shopdetail/login",method = RequestMethod.POST)
    public BaseJson login(HttpSession session, Operater operater){
        BaseJson baseJson = new BaseJson();

        if(detailService.getPassword(operater.getUsername()).equals(operater.getPassword())){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult(operater.getUsername());

            session.setAttribute("username", operater.getUsername());

           // log.info( session.getAttribute("username").toString()+"***********");

        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("用户名或密码错误");
        }


        return baseJson;
    }
    @RequestMapping(value = "/shopdetail/logout",method = RequestMethod.GET)
    public BaseJson logout(HttpSession session){
        BaseJson baseJson = new BaseJson();
        //System.out.println( session.getId()+"------------------");
        session.invalidate();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult("登出成功");

        return baseJson;
    }
    //@CrossOrigin(origins="http://localhost:82",allowCredentials = "true")
    @RequestMapping(value = "/shopdetail/getUsername",method = RequestMethod.GET)
    public BaseJson getUsername(HttpSession session){
        BaseJson baseJson = new BaseJson();


        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(session.getAttribute("username"));

        return baseJson;
    }
    @RequestMapping(value = "/shopdetail/redirect",method = RequestMethod.GET)
    public BaseJson redirect(){
        BaseJson baseJson = new BaseJson();
        baseJson.setCode(1);
        baseJson.setMessage("失败");
        baseJson.setResult("请登入");

        return baseJson;
    }

    @RequestMapping(value = "/shopdetail/addShopType/{typeName}",method = RequestMethod.GET)
    public BaseJson addShopType(@PathVariable("typeName") String typeName ){
        ShopType shopType=new ShopType();
        shopType.setTypeName(typeName);

        BaseJson baseJson = new BaseJson();

        int flag = detailService.addShopType(shopType);
        if(flag == 0){
            baseJson.setCode(1);
            baseJson.setMessage("失败");
            baseJson.setResult("商户类型已存在");

        }else {
            baseJson.setCode(0);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");

        }
        return baseJson;
    }

    @RequestMapping(value = "/shopdetail/findAllShopType",method = RequestMethod.GET)
    public List<ShopType> findAllShopType() {

        return detailService.findAllShopType();
    }
    @RequestMapping(value = "/shopdetail/delShopTypeById/{ids}",method = RequestMethod.GET)
    public BaseJson delShopTypeById(@PathVariable("ids") String ids) {

        BaseJson baseJson = new BaseJson();
        if(detailService.delShopTypeById(ids) > 0){
            baseJson.setCode(0);
            baseJson.setMessage("成功");
        }else{
            baseJson.setCode(1);
            baseJson.setMessage("失败");
        }
        return baseJson;

    }
    @RequestMapping(value = "/shopdetail/getIfShopByTypeIds/{ids}",method = RequestMethod.GET)
    public Integer getIfShopByTypeIds(@PathVariable("ids") String ids ){
        return detailService.getIfShopByTypeIds(ids);
    }

    @RequestMapping(value = "/shopdetail/getShopNameById/{id}",method = RequestMethod.GET)
    public BaseJson getShopNameById(@PathVariable("id") Integer id){

        BaseJson baseJson = new BaseJson();
        baseJson.setCode(0);
        baseJson.setMessage("成功");
        baseJson.setResult(detailService.getShopNameById(id));
        return baseJson;
    }
}
