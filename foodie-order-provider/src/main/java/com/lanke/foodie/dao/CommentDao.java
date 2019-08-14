package com.lanke.foodie.dao;


import com.lanke.foodie.entity.ProductComment;
import com.lanke.foodie.entity.ShopComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {

    public Integer addShopComment(ShopComment shopComment);
    public Integer addProductComment(ProductComment productComment);
    public List<ShopComment> getAllShopComment(@Param("shopId") Integer shopId);
    public List<ProductComment> getAllProductComment(@Param("productId") Integer productId);
}
