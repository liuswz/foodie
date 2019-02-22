package com.lanke.foodie.utils;

import com.lanke.foodie.entity.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BaseUtils {

    public static String getTime(){

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(now);
        return dateStr;

    }
    public static String createOrderNo(){


        return  new Date().getTime() + "" + (int)(89999999*Math.random()+10000000);

    }
    public static List<Order> transformTime(List<Order> list,String dateFormate) {
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
}
