package com.lanke.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.CommentDao;
import com.lanke.foodie.dao.UserOrderDao;
import com.lanke.foodie.dto.OrderAndItemDto;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.OrderItem;
import com.lanke.foodie.entity.ProductComment;
import com.lanke.foodie.entity.ShopComment;
import com.lanke.foodie.enums.*;
import com.lanke.foodie.service.CommentService;
import com.lanke.foodie.service.UserOrderService;
import com.lanke.foodie.simpleEntity.SimpleOrder;
import com.lanke.foodie.simpleEntity.SimpleOrderItem;
import com.lanke.foodie.userdto.*;
import com.lanke.foodie.userparams.ProductMarkDetail;
import com.lanke.foodie.userparams.ShopMarkDetail;
import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserOrderDao orderDao;
    @Autowired
    private CommentDao commentDao;


    public Integer addShopComment(ShopComment shopComment) {
        shopComment.setCreateTime(BaseUtils.getTimeNoSecond());
        if(commentDao.addShopComment(shopComment)>0){
            orderDao.updateCommentStatus(shopComment.getOrderId());
            ShopMarkDetail markDetail = orderDao.getShopMarkDetail(shopComment.getShopId());

            Double newMark;
            if(markDetail==null||markDetail.getShopMark()==null||markDetail.getCommentNum()==null){
                 newMark = shopComment.getMark();
            }else{
                 newMark = (markDetail.getShopMark()*markDetail.getCommentNum()+shopComment.getMark())/(markDetail.getCommentNum()+1);
            }


            orderDao.updateShopMark(shopComment.getShopId(),newMark);
            return Result.SUCCESS.getIndex();
        }
        return Result.FAIL.getIndex();
    }

    public Integer addProductComment(ProductComment productComment) {

        productComment.setCreateTime(BaseUtils.getTimeNoSecond());
        if(commentDao.addProductComment(productComment)>0){
            orderDao.updateCommentStatus(productComment.getOrderId());
            ProductMarkDetail markDetail = orderDao.getProductMarkDetail(productComment.getProductId());
            Double newMark;
            if(markDetail==null||markDetail.getProductMark()==null||markDetail.getCommentNum()==null){
                newMark = productComment.getMark();
            }else{
                newMark = (markDetail.getProductMark()*markDetail.getCommentNum()+productComment.getMark())/(markDetail.getCommentNum()+1);
            }

            orderDao.updateProductMark(productComment.getProductId(),newMark);
            return Result.SUCCESS.getIndex();
        }
        return Result.FAIL.getIndex();
    }

    public PageResults getAllShopComment(Integer pageNum, Integer pageSize, Integer shopId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ShopComment> page=   (Page<ShopComment>) BaseUtils.transformTimeForShopComment(commentDao.getAllShopComment(shopId));
        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public PageResults getAllProductComment(Integer pageNum, Integer pageSize, Integer productId) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ProductComment> page=   (Page<ProductComment>) BaseUtils.transformTimeForProductComment(commentDao.getAllProductComment(productId));
        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;

    }


}
