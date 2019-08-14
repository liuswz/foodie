package com.lanke.foodie.service;

import com.lanke.foodie.entity.ShopCollect;
import com.lanke.foodie.entity.User;
import com.lanke.foodie.simpleEntity.SimpleUser;
import com.lanke.foodie.userdto.ShopDetailDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public User addUser(User user);
    public Integer updateWechat( String typeName ,Integer id);
    public Integer updateAddress(String address,Integer id);
    public Integer updatePhotoUrl( String photoUrl,Integer id);
    public Integer updateUsername(String username,Integer id);
    public Integer updateNickname(String nickname,Integer id);
    public User getUserByPhoneNum(String phoneNum);


}
