package com.lanke.foodie.controller;


import com.lanke.foodie.entity.ProductComment;
import com.lanke.foodie.entity.ShopComment;
import com.lanke.foodie.service.CommentService;


import com.lanke.foodie.userdto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;



    @RequestMapping(value = "/comment/addShopComment",method = RequestMethod.POST)
    public Integer  addShopComment(@RequestBody ShopComment shopComment){
        return commentService.addShopComment(shopComment);
    }
    @RequestMapping(value = "/comment/addProductComment",method = RequestMethod.POST)
    public Integer  addProductComment(@RequestBody ProductComment productComment){
        return commentService.addProductComment(productComment);
    }

    @RequestMapping(value = "/order/getAllShopComment/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResults getAllShopComment(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("shopId")  Integer shopId){

        return commentService.getAllShopComment(page,size,shopId);
    }
    @RequestMapping(value = "/order/getAllProductComment/{page}/{size}/{productId}",method = RequestMethod.GET)
    public PageResults getAllProductComment(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("productId")  Integer productId){
        return commentService.getAllProductComment(page,size,productId);
    }
}
