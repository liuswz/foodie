package com.lanke.foodie.controller;

import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.entity.Voucher;
import com.lanke.foodie.entity.VoucherForUser;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.VoucherService;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userdto.VoucherForUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    //代金卷

    @RequestMapping(value = "/voucher/getVoucherById/{shopId}",method = RequestMethod.GET)
    public BaseJson getVoucherById(@PathVariable("shopId") Integer shopId) {
        BaseJson baseJson =new BaseJson();

        baseJson.setCode(Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(voucherService.getVoucherById(shopId));

        return baseJson;
    }
    @RequestMapping(value = "/voucher/getVoucherByCity/{page}/{size}/{city}",method = RequestMethod.GET)
    public PageResults getVoucherByCity(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("city") String city){
        PageResults pageResults = voucherService.getVoucherByCity(page,size,city);
        pageResults.setCode(Result.SUCCESS.getIndex());
        pageResults.setMessage("成功");
        return pageResults;
    }
    @RequestMapping(value = "/voucher/getVoucherForUserByUserId/{page}/{size}/{userId}",method = RequestMethod.GET)
    public PageResults getVoucherForUserByUserId(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("userId") Integer userId) {
        PageResults pageResults = voucherService.getVoucherForUserByUserId(page,size,userId);
        pageResults.setCode(Result.SUCCESS.getIndex());
        pageResults.setMessage("成功");
        return pageResults;
    }
    @RequestMapping(value = "/voucher/addShopVoucherForUser",method = RequestMethod.POST)
    public BaseJson addShopVoucherForUser(@RequestBody VoucherForUser voucher){
        BaseJson baseJson =new BaseJson();
        Integer flag = voucherService.addShopVoucherForUser(voucher);
        if(flag>0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("已领取");
        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("未领取");
        }
        return baseJson;
    }
    @RequestMapping(value = "/voucher/checkVoucherForUser/{shopId}/{userId}/{voucherId}",method = RequestMethod.GET)
    public BaseJson checkVoucherForUser(@PathVariable("shopId") Integer shopId,@PathVariable("userId") Integer userId,@PathVariable("voucherId") Integer voucherId) {
        BaseJson baseJson =new BaseJson();
        Integer flag = voucherService.checkVoucherForUser(shopId,userId,voucherId);
        if(flag>0){
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("已领取");
        }else{
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("未领取");

        }
        return baseJson;

    }


    @RequestMapping(value = "/voucher/getVoucherForUserById/{shopId}/{userId}",method = RequestMethod.GET)
    public BaseJson getVoucherForUserById(@PathVariable("shopId")  Integer shopId, @PathVariable("userId")  Integer userId) {
        BaseJson baseJson =new BaseJson();

        baseJson.setCode(Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(voucherService.getVoucherForUserById(shopId,userId));

        return baseJson;
    }
    @RequestMapping(value = "/voucher/delVoucherForUserById/{id}",method = RequestMethod.GET)
    public BaseJson delVoucherForUserById(@PathVariable("id")  Integer id) {

        BaseJson baseJson =new BaseJson();
        Integer flag =  voucherService.delVoucherForUserById(id);
        if(flag>0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("删除成功");
        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("删除失败");

        }
        return baseJson;

    }

}
