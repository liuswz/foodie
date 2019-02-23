package com.lanke.foodie.service;


import com.lanke.foodie.entity.Shop;

public interface DetailService {
    public int regist(Shop shop);

    public int update(Shop shop);

    public Shop getById(Integer id);
}
