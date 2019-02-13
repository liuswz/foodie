package com.lanke.foodie.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtils {

    public static String getTime(){

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(now);
        return dateStr;

    }
}
