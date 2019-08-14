package com.lanke.foodie.controller;


import com.lanke.foodie.entity.User;
import com.lanke.foodie.service.UserDetailService;
import com.lanke.foodie.service.UserService;
import com.lanke.foodie.simpleEntity.SimpleUser;
import com.lanke.foodie.userdto.PageResults;
import com.lanke.foodie.userparams.ShopInSiteAndTypeParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {


    @Autowired
    private UserService userService;
    @RequestMapping(value = "/shopdetail/addUser",method = RequestMethod.POST)
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @RequestMapping(value = "/shopdetail/updateWechat",method = RequestMethod.POST)
    public Integer updateWechat(@RequestParam("typeName")  String typeName,@RequestParam("id") Integer id){
        return userService.updateWechat(typeName,id);
    }
    @RequestMapping(value = "/shopdetail/updateAddress",method = RequestMethod.POST)
    public Integer updateAddress(@RequestParam("address")  String address,@RequestParam("id") Integer id){
        return userService.updateAddress(address,id);
    }
    @RequestMapping(value = "/shopdetail/updatePhotoUrl",method = RequestMethod.POST)
    public Integer updatePhotoUrl(@RequestParam("photoUrl")  String photoUrl,@RequestParam("id") Integer id){
        return userService.updatePhotoUrl(photoUrl,id);
    }

    @RequestMapping(value = "/shopdetail/updateUsername",method = RequestMethod.POST)
    public Integer updateUsername(@RequestParam("username")  String username,@RequestParam("id") Integer id){
        return userService.updateUsername(username,id);
    }

    @RequestMapping(value = "/shopdetail/updateNickname",method = RequestMethod.POST)
    public Integer updateNickname(@RequestParam("nickname")  String nickname,@RequestParam("id") Integer id){
        return userService.updateNickname(nickname,id);
    }
    @RequestMapping(value = "/shopdetail/getUserByPhoneNum/{phoneNum}",method = RequestMethod.GET)
    public User getUserByPhoneNum(@PathVariable("phoneNum") String phoneNum){
        return userService.getUserByPhoneNum(phoneNum);
    }

}
