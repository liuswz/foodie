package com.lanke.foodie.service.impl;

import com.lanke.foodie.dao.UserDao;
import com.lanke.foodie.entity.User;
import com.lanke.foodie.service.UserService;

import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public User addUser(User user) {
        User user2 = userDao.getUserByPhoneNum(user.getPhoneNum());
        if(user2==null){
            if(userDao.addUser(user).getId()>0){
                return user;
            }else{
                return null;
            }
        }else{
            return  user2;
        }


    }

    public Integer updateWechat(String typeName,Integer id) {
        return userDao.updateWechat(typeName,id);
    }

    public Integer updateAddress(String address,Integer id) {
        return userDao.updateAddress(address,id);
    }

    public Integer updatePhotoUrl(String photoUrl,Integer id) {
        return userDao.updatePhotoUrl(photoUrl,id);
    }

    public Integer updateUsername(String username,Integer id) {
        return userDao.updateUsername(username,id);
    }

    public Integer updateNickname(String nickname,Integer id) {
        return userDao.updateNickname(nickname,id);
    }

    public User getUserByPhoneNum(String phoneNum) {
        return userDao.getUserByPhoneNum(phoneNum);
    }
}
