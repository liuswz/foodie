package com.lanke.foodie.service;


import com.lanke.foodie.entity.ProductComment;
import com.lanke.foodie.entity.ShopComment;
import com.lanke.foodie.userdto.PageResults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "FOODIE-ORDERS")
public interface CommentService {
    @RequestMapping(value = "/comment/addShopComment",method = RequestMethod.POST)
    public Integer  addShopComment(@RequestBody ShopComment shopComment);
    @RequestMapping(value = "/comment/addProductComment",method = RequestMethod.POST)
    public Integer  addProductComment(@RequestBody ProductComment productComment);

    @RequestMapping(value = "/order/getAllShopComment/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResults getAllShopComment(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId")  Integer shopId);
    @RequestMapping(value = "/order/getAllProductComment/{page}/{size}/{productId}",method = RequestMethod.GET)
    public PageResults getAllProductComment(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("productId")  Integer productId);
}
