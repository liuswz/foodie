package com.lanke.sms;


import com.lanke.sms.service.DishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;

@Slf4j
@Component
public class SmsListener {
    @Autowired
    private DishesService dishesService;

    @JmsListener(destination="statics")
    public void sendSms(String username) throws IOException {


//构造模板引擎

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

        resolver.setPrefix("templates/");//模板所在目录，相对于当前classloader的classpath。

        resolver.setSuffix(".html");//模板文件后缀

        TemplateEngine templateEngine = new TemplateEngine();

        templateEngine.setTemplateResolver(resolver);







        Context context = new Context();

        context.setVariable("typeToDishList", dishesService.getTypeToDish(1));

        context.setVariable("shop", dishesService.getShopById(1));


        //渲染模板

        FileWriter write = new FileWriter("classify2.html");

        templateEngine.process("classify", context, write);

        log.info("接收到消息***********************："+username);

    }
}
