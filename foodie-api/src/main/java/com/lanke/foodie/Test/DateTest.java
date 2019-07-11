package com.lanke.foodie.Test;

import com.lanke.foodie.entity.Dish;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) throws ParseException {
        String datetime="2019-02-21 13:50:47.0";
      //  Date date=new Date(datetime);
        SimpleDateFormat ft =
                new SimpleDateFormat ("HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        Dish d2 =new Dish();
        
        Date d = sdf1.parse(datetime);

        System.out.println("Current Date: " + ft.format(d));

    }
}
