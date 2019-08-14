package com.lanke.foodie.utils;

import com.lanke.foodie.dto.OrderAndShopDto;
import com.lanke.foodie.entity.Order;
import com.lanke.foodie.entity.ProductComment;
import com.lanke.foodie.entity.ShopComment;
import com.lanke.foodie.simpleEntity.SimpleOrder;
import com.lanke.foodie.userdto.DishForAppointOrder;
import com.lanke.foodie.userdto.DishForGoShopOrder;
import com.lanke.foodie.userdto.ProductForGoShopOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BaseUtils {

    public static String getTime() {

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(now);
        return dateStr;

    }
    public static String getTimeNoSecond() {

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(now);
        return dateStr;

    }

    public static String createOrderNo() {


        return new Date().getTime() + "" + (int) (89999999 * Math.random() + 10000000);

    }

    public static List<Order> transformTime(List<Order> list, String dateFormate) {
        SimpleDateFormat ft = new SimpleDateFormat(dateFormate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        for (Order o : list) {
            Date d = null;
            try {
                d = sdf1.parse(o.getCreateTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            o.setCreateTime(ft.format(d));
        }
        return list;
    }
    public static List<DishForGoShopOrder> transformTimeForDishForGoShopOrder(List<DishForGoShopOrder> list, String dateFormate) {
        SimpleDateFormat ft = new SimpleDateFormat(dateFormate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        for (DishForGoShopOrder o : list) {
            Date d = null;
            try {
                d = sdf1.parse(o.getCreateTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            o.setCreateTime(ft.format(d));
        }
        return list;
    }
    public static List<DishForAppointOrder> transformTimeForDishForAppointOrder(List<DishForAppointOrder> list, String dateFormate) {
        SimpleDateFormat ft = new SimpleDateFormat(dateFormate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        for (DishForAppointOrder o : list) {
            Date d = null;
            try {
                d = sdf1.parse(o.getCreateTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            o.setCreateTime(ft.format(d));
        }
        return list;
    }
    public static List<ProductForGoShopOrder> transformTimeForProductForGoShopOrder(List<ProductForGoShopOrder> list, String dateFormate) {
        SimpleDateFormat ft = new SimpleDateFormat(dateFormate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        for (ProductForGoShopOrder o : list) {
            Date d = null;
            try {
                d = sdf1.parse(o.getCreateTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            o.setCreateTime(ft.format(d));
        }
        return list;
    }
    public static List<SimpleOrder> transformTimeForSimpleOrder(List<SimpleOrder> list) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        for (SimpleOrder o : list) {
            Date d = null;
            try {
                d = sdf1.parse(o.getCreateTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            o.setCreateTime(ft.format(d));
        }
        return list;
    }
    public static List<ShopComment> transformTimeForShopComment(List<ShopComment> list) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        for (ShopComment o : list) {
            Date d = null;
            try {
                d = sdf1.parse(o.getCreateTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            o.setCreateTime(ft.format(d));
        }
        return list;
    }
    public static List<ProductComment> transformTimeForProductComment(List<ProductComment> list) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        for (ProductComment o : list) {
            Date d = null;
            try {
                d = sdf1.parse(o.getCreateTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            o.setCreateTime(ft.format(d));
        }
        return list;
    }
    public static List<OrderAndShopDto> transformTimeToOrderAndShopDto(List<OrderAndShopDto> list, String dateFormate) {
        SimpleDateFormat ft = new SimpleDateFormat (dateFormate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        for(Order o:list){
            Date d = null;
            try {
                d = sdf1.parse(o.getCreateTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            o.setCreateTime(ft.format(d));
        }
        return list;
    }


    //生成随机用户名，数字和字母组成,
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
