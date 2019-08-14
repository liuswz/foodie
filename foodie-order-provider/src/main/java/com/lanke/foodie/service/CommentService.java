package com.lanke.foodie.service;


import com.lanke.foodie.entity.ProductComment;
import com.lanke.foodie.entity.ShopComment;
import com.lanke.foodie.userdto.PageResults;



public interface CommentService {


    public Integer  addShopComment(ShopComment shopComment);
    public Integer  addProductComment(ProductComment productComment);
    public PageResults getAllShopComment(Integer pageNum, Integer pageSize,Integer shopId);
    public PageResults getAllProductComment(Integer pageNum, Integer pageSize,Integer productId);


}
