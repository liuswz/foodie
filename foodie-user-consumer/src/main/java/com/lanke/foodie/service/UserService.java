package com.lanke.foodie.service;

import com.lanke.foodie.entity.User;
import com.lanke.foodie.simpleEntity.SimpleUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "FOODIE-DETAIL")
public interface UserService {
    @RequestMapping(value = "/shopdetail/addUser",method = RequestMethod.POST)
    public User addUser(@RequestBody User user);
    @RequestMapping(value = "/shopdetail/updateWechat",method = RequestMethod.POST)
    public Integer updateWechat(@RequestParam("typeName")  String typeName,@RequestParam("id") Integer id);
    @RequestMapping(value = "/shopdetail/updateAddress",method = RequestMethod.POST)
    public Integer updateAddress(@RequestParam("address")  String address,@RequestParam("id") Integer id);
    @RequestMapping(value = "/shopdetail/updatePhotoUrl",method = RequestMethod.POST)
    public Integer updatePhotoUrl(@RequestParam("photoUrl")  String photoUrl,@RequestParam("id") Integer id);

    @RequestMapping(value = "/shopdetail/updateUsername",method = RequestMethod.POST)
    public Integer updateUsername(@RequestParam("username")  String username,@RequestParam("id") Integer id);

    @RequestMapping(value = "/shopdetail/updateNickname",method = RequestMethod.POST)
    public Integer updateNickname(@RequestParam("nickname")  String nickname,@RequestParam("id") Integer id);
    @RequestMapping(value = "/shopdetail/getUserByPhoneNum/{phoneNum}",method = RequestMethod.GET)
    public User getUserByPhoneNum(@PathVariable("phoneNum") String phoneNum);
}
