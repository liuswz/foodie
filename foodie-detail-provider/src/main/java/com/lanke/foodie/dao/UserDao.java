package com.lanke.foodie.dao;

import com.lanke.foodie.entity.User;
import com.lanke.foodie.simpleEntity.SimpleUser;
import com.lanke.foodie.userdto.ShopDetailDto;
import com.lanke.foodie.userparams.ShopInSiteAndTypeParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    public User addUser(User user);
    public Integer updateWechat(@Param("typeName") String typeName,@Param("id") Integer id);
    public Integer updateAddress(@Param("address") String address,@Param("id") Integer id);
    public Integer updatePhotoUrl(@Param("photoUrl") String photoUrl,@Param("id") Integer id);

    public Integer updateUsername(@Param("username") String username,@Param("id") Integer id);
    public Integer updateNickname(@Param("nickname") String nickname,@Param("id") Integer id);
    public User getUserByPhoneNum(@Param("phoneNum") String phoneNum);
}
