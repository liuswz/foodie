package com.lanke.sms;



import com.alibaba.fastjson.JSON;
import com.lanke.foodie.entity.ShopDetails;
import com.lanke.foodie.searchEntity.SearchShop;
import com.lanke.foodie.simpleEntity.SimpleShop;
import com.lanke.foodie.utils.ESUtils;
import com.lanke.sms.repository.ShopRepository;
import com.lanke.sms.service.DishesService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component

public class SmsListener {
    @Autowired
    private DishesService dishesService;

    private final  String localUrl="D:\\nginx-1.10.3\\nginx-1.10.3\\html\\user\\";



   // @JmsListener(destination="statics")
    @RabbitListener(queues = "statics",containerFactory="rabbitListenerContainerFactory")
    @RabbitHandler
    public void sendSms(Map<String,Integer> map) {

//构造模板引擎
        Integer id = map.get("id");
        Integer flag = map.get("flag");
        if(flag==1){
            dishesService.takeToES(id);
        }


        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

        resolver.setPrefix("templates/");//模板所在目录，相对于当前classloader的classpath。

        resolver.setSuffix(".html");//模板文件后缀

        TemplateEngine templateEngine = new TemplateEngine();

        templateEngine.setTemplateResolver(resolver);


        Context context = new Context();

        context.setVariable("typeToDishList", dishesService.getTypeToDish(id));
      //  log.info( dishesService.getTypeToDish(Integer.parseInt(map.get("id"))).get(0).getDishType().getTypeName()+"---------------------------------");
       // log.info(JSON.toJSONString(dishesService.getTypeToDish(Integer.parseInt(map.get("id")))));
        SimpleShop simpleShop = dishesService.getShopById(id);
        context.setVariable("shop", simpleShop);
        //渲染模板
        FileWriter write = null;
        try {
            write = new FileWriter(localUrl+simpleShop.getUsername()+".html");
        } catch (IOException e) {
            e.printStackTrace();
        }

        templateEngine.process("classify3", context, write);



    }

    @RabbitListener(queues = "delete_searchdata",containerFactory="rabbitListenerContainerFactory")
    @RabbitHandler
    public void deleteSearchData(Integer shopId){
        dishesService.deleteSearchData(shopId);

    }
}
