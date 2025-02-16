package com.lanke.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lanke.foodie.dao.ProductDao;
import com.lanke.foodie.dto.PageResult;
import com.lanke.foodie.dto.ProductDto;
import com.lanke.foodie.dto.ProductWithMoneyOffDto;
import com.lanke.foodie.entity.*;
import com.lanke.foodie.service.ProductService;
import com.lanke.foodie.userdto.*;
import com.lanke.foodie.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public Integer addProductType(ProductType productType) {
        productType.setCreateTime(BaseUtils.getTime());

        if(productDao.checkProductType(productType.getTypeName())==0){
            return productDao.addProductType(productType);
        }else{

            return 0;
        }
    }
    public List<ProductType> findAllProductType() {
        return productDao.findAllProductType();
    }
    public Integer delProductTypeById(String ids) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";

        return productDao.delProductTypeById(ids);
    }

    public Integer getIfProductByTypeIds(String ids) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";
        return productDao.getIfProductByTypeIds(ids);
    }

    public Integer addVoucherForShop(String ids, VoucherForShop voucherForShop) {

        voucherForShop.setCreateTime(BaseUtils.getTime());

        String[] allIds = ids.split(",");
        List<VoucherForShop> list =new ArrayList<VoucherForShop>();

        for(int i=0;i<allIds.length;i++){
            VoucherForShop voucher=new VoucherForShop();

            voucher.setMoney(voucherForShop.getMoney());
            voucher.setCreateTime(voucherForShop.getCreateTime());
            voucher.setStartDate(voucherForShop.getStartDate());
            voucher.setDeadLine(voucherForShop.getDeadLine());
            voucher.setShopId(Integer.parseInt(allIds[i]));

            list.add(voucher);
        }
        return productDao.addVoucherForShop(list);
    }

    public List<VoucherForShop> getVoucherForShopById(Integer shopId) {
        return productDao.getVoucherForShopById(shopId);
    }

    @Override
    public List<VoucherForUserDto> getVoucherForUserById(Integer shopId, Integer userId) {
        return productDao.getVoucherForUserById(shopId,userId);
    }

    @Override
    public Integer delVoucherForUserById(Integer id) {
        return productDao.delVoucherForUserById(id);
    }

    public Integer addMoneyOff(MoneyOff moneyOff) {
        moneyOff.setCreateTime(BaseUtils.getTime());
        if(productDao.checkMoneyOff(moneyOff.getFullNum(),moneyOff.getMinusNum())==0){
            return productDao.addMoneyOff(moneyOff);
        }else{
            return 0;
        }
    }

    public Integer delMoneyOffById(String ids) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";
        return productDao.delMoneyOffById(ids);
    }

    @Override
    public List<MoneyOff> findMoneyOffByIds(String ids) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";
        return productDao.findMoneyOffByIds(ids);
    }

    public List<MoneyOff> findAllMoneyOff() {
        return productDao.findAllMoneyOff();
    }

    public Integer getIfMoneyOffInProduct(String ids) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";
        return productDao.getIfMoneyOffInProduct(ids);
    }

    public Integer addProduct(Product product) {
        product.setCreateTime(BaseUtils.getTime());
        if(productDao.checkProduct(product.getProductName())==0){
            return productDao.addProduct(product);
        }else{
            return 0;
        }
    }

    public Integer delProductById(String ids) {
        ids = "("+ids.substring(0,ids.length() - 1)+")";
        return productDao.delProductById(ids);
    }

    public Product getProductById(Integer id) {
        return productDao.getProductById(id);
    }

    public PageResult findAllProduct(Integer pageNum, Integer pageSize, String value) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ProductDto> page=   (Page<ProductDto>) productDao.findAllProduct(value);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<String> getProductNamesByValue(String value) {
        return productDao.getProductNamesByValue(value);
    }

    public PageResults findAllProductWithMoneyOff(Integer pageNum, Integer pageSize, String value) {
        PageHelper.startPage(pageNum, pageSize);

        //Page<ProductWithMoneyOffDto> page=   (Page<ProductWithMoneyOffDto>) productDao.findAllProductWithMoneyOff(value);


        Page<ProductWithMoneyOffDto> page=   (Page<ProductWithMoneyOffDto>) productDao.findAllProductWithMoneyOff(value);
        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;

    }

    public PageResults findAllProductWithMoneyOffByType(Integer pageNum, Integer pageSize, String productType) {
        PageHelper.startPage(pageNum, pageSize);

        Page<ProductWithMoneyOffDto> page=   (Page<ProductWithMoneyOffDto>) productDao.findAllProductWithMoneyOffByType(productType);

        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }


    public Integer updateProduct(Product product) {

        product.setCreateTime(BaseUtils.getTime());
        List<Integer> ids = productDao.checkProductById(product.getProductName());
        if(ids.size() > 0 && ids.get(0) != product.getId()){
            return 0;
        }else{
            return productDao.updateProduct(product);
        }

    }

    public String getProductNameById(Integer id) {
        return productDao.getProductNameById(id);
    }

    public Integer addShopVoucher(Voucher voucher) {
        if(productDao.checkVoucher(voucher.getShopId())==0){
            voucher.setCreateTime(BaseUtils.getTime());
            return productDao.addShopVoucher(voucher);
        }else{
            return productDao.updateVoucher(voucher);
        }
    }


    public PageResults getVoucherByCity(Integer pageNum, Integer pageSize, String city) {
        PageHelper.startPage(pageNum, pageSize);
        Page<VoucherDto> page=   (Page<VoucherDto>) productDao.getVoucherByCity(city);


        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;

    }

    @Override
    public PageResults getVoucherForUserByUserId(Integer pageNum, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<VoucherDto> page=   (Page<VoucherDto>) productDao.getVoucherForUserByUserId(userId);
        PageResults pageResults = new PageResults();
        pageResults.setTotal(page.getTotal());
        pageResults.setRows(page.getResult());
        return pageResults;
    }

    public Voucher getVoucherById(Integer shopId) {
        Voucher voucher = productDao.getVoucherById(shopId);
        if(voucher==null){
            return new Voucher();
        }else{
            return voucher;
        }
    }
    public Integer delVoucherById(Integer shopId) {
        return productDao.delVoucherById(shopId);
    }

    @Override
    public Integer addShopVoucherForUser(VoucherForUser voucher) {
        if(productDao.checkVoucherForUser(voucher.getShopId(),voucher.getUserId(),voucher.getVoucherId())>0){
            return 0;
        }else{
            voucher.setCreateTime(BaseUtils.getTime());
            voucher.setHadUse(0);
            return productDao.addShopVoucherForUser(voucher);

        }
    }

    @Override
    public Integer checkVoucherForUser(Integer shopId, Integer userId, Integer voucherId) {
        return productDao.checkVoucherForUser(shopId,userId,voucherId);
    }

    @Override
    public ProductUserDto getProductDetailForUser(Integer id) {
        ProductUserDto productUserDto = productDao.getProductDetailForUser(id);
        String ids = productUserDto.getMoneyOffIds();
        String fullNum="",minusNum="";
        if(ids!=null) {
            ids = "(" + ids.substring(0, ids.length() - 1) + ")";
            List<MoneyOff> moneyOffs = productDao.findMoneyOffByIds(ids);
            for (MoneyOff m : moneyOffs) {
                fullNum += m.getFullNum().toString() + ",";
                minusNum += m.getMinusNum().toString() + ",";
            }
        }
        productUserDto.setFullNum(fullNum);
        productUserDto.setMinusNum(minusNum);
        return productUserDto;
    }
}
