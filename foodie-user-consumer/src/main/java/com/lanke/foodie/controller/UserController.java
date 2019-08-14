package com.lanke.foodie.controller;

import com.lanke.foodie.entity.User;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.UserService;
import com.lanke.foodie.simpleEntity.SimpleUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/shopdetail/addUser",method = RequestMethod.POST)
    public BaseJson addUser(@RequestBody User user){
        User userResult = userService.addUser(user);
        BaseJson baseJson =new BaseJson();
        if(userResult==null){
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult(null);
        }else{
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult(userResult);

        }



        return  baseJson;

    }
    @RequestMapping(value = "/shopdetail/updateWechat",method = RequestMethod.POST)
    public BaseJson updateWechat(@RequestParam("typeName") String typeName,@RequestParam("id") Integer id){
        BaseJson baseJson =new BaseJson();
        Integer flag = userService.updateWechat(typeName,id);
        if(flag>0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult("更改成功");
        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult("更改失败");
        }

        return baseJson;
    }
    @RequestMapping(value = "/shopdetail/updateAddress",method = RequestMethod.POST)
    public BaseJson updateAddress(@RequestParam("address")  String address,@RequestParam("id") Integer id){

        BaseJson baseJson =new BaseJson();
        Integer flag = userService.updateWechat(address,id);
        if(flag>0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult(address);
        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult(address);
        }

        return baseJson;
    }
    @RequestMapping(value = "/shopdetail/updatePhotoUrl",method = RequestMethod.POST)
    public BaseJson updatePhotoUrl(@RequestParam("photoUrl")  String photoUrl,@RequestParam("id") Integer id){

        BaseJson baseJson =new BaseJson();
        Integer flag = userService.updatePhotoUrl(photoUrl,id);
        if(flag>0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult(photoUrl);
        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult(photoUrl);
        }

        return baseJson;
    }
    @RequestMapping(value = "/shopdetail/updateUsername",method = RequestMethod.POST)
    public BaseJson updateUsername(@RequestParam("username")  String username,@RequestParam("id") Integer id){
        BaseJson baseJson =new BaseJson();
        Integer flag = userService.updateUsername(username,id);
        if(flag>0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult(username);
        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult(username);
        }

        return baseJson;

    }
    @RequestMapping(value = "/shopdetail/updateNickname",method = RequestMethod.POST)
    public BaseJson updateNickname(@RequestParam("nickname")  String nickname,@RequestParam("id") Integer id){

        BaseJson baseJson =new BaseJson();
        Integer flag = userService.updateNickname(nickname,id);
        if(flag>0){
            baseJson.setCode(Result.SUCCESS.getIndex());
            baseJson.setMessage("成功");
            baseJson.setResult(nickname);
        }else{
            baseJson.setCode(Result.FAIL.getIndex());
            baseJson.setMessage("失败");
            baseJson.setResult(nickname);
        }

        return baseJson;
    }
    @RequestMapping(value = "/shopdetail/getUserByPhoneNum/{phoneNum}",method = RequestMethod.GET)
    public BaseJson getUserByPhoneNum(@PathVariable("phoneNum") String phoneNum){

        BaseJson baseJson =new BaseJson();

        baseJson.setCode(Result.SUCCESS.getIndex());
        baseJson.setMessage("成功");
        baseJson.setResult(userService.getUserByPhoneNum(phoneNum));

        return baseJson;

    }
}
