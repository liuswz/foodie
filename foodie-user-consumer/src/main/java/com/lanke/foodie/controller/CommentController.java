package com.lanke.foodie.controller;


import com.lanke.foodie.entity.ProductComment;
import com.lanke.foodie.entity.ShopComment;
import com.lanke.foodie.enums.Result;
import com.lanke.foodie.json.BaseJson;
import com.lanke.foodie.service.CommentService;
import com.lanke.foodie.userdto.PageResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/userconsumer")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @RequestMapping(value = "/comment/addShopComment",method = RequestMethod.POST)
    public BaseJson  addShopComment(@RequestBody ShopComment shopComment){

        int flag = commentService.addShopComment(shopComment);
        BaseJson baseJson = new BaseJson();
        if(flag == Result.SUCCESS.getIndex()){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
        }else {
            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("添加失败");
        }
        return baseJson;
    }
    @RequestMapping(value = "/comment/addProductComment",method = RequestMethod.POST)
    public BaseJson  addProductComment(@RequestBody ProductComment productComment){
        int flag = commentService.addProductComment(productComment);
        BaseJson baseJson = new BaseJson();
        if(flag == Result.SUCCESS.getIndex()){
            baseJson.setCode(flag);
            baseJson.setMessage("成功");
            baseJson.setResult("添加成功");
        }else {
            baseJson.setCode(flag);
            baseJson.setMessage("失败");
            baseJson.setResult("添加失败");
        }
        return baseJson;

    }

    @RequestMapping(value = "/order/getAllShopComment/{page}/{size}/{shopId}",method = RequestMethod.GET)
    public PageResults getAllShopComment(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("shopId")  Integer shopId){
        PageResults pageResults =commentService.getAllShopComment(page,size,shopId);
        pageResults.setCode(Result.SUCCESS.getIndex());
        pageResults.setMessage("成功");
        return  pageResults;

    }
    @RequestMapping(value = "/order/getAllProductComment/{page}/{size}/{productId}",method = RequestMethod.GET)
    public PageResults getAllProductComment(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("productId")  Integer productId){

        PageResults pageResults = commentService.getAllProductComment(page,size,productId);
        pageResults.setCode(Result.SUCCESS.getIndex());
        pageResults.setMessage("成功");
        return  pageResults;
    }
}
