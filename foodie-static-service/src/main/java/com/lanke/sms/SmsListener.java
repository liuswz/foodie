package com.lanke.sms;



import com.alibaba.fastjson.JSON;
import com.lanke.foodie.simpleEntity.SimpleShop;
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
@RabbitListener(queues = "statics")
public class SmsListener {
    @Autowired
    private DishesService dishesService;

    private final  String localUrl="D:\\nginx-1.10.3\\nginx-1.10.3\\html\\user\\";
   // @JmsListener(destination="statics")
    @RabbitHandler
    public void sendSms(Integer id) throws IOException {


//构造模板引擎

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
        FileWriter write = new FileWriter(localUrl+simpleShop.getUsername()+".html");

        templateEngine.process("classify3", context, write);



     /*   String endpoint = "https://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAICYbxfcsN4mvc";
        String accessKeySecret = "Q3JmnUHAV0OvREvyhfpsxDuFEUyQSH";

// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        ossClient.putObject("foodie-static-website", "classify.html", new File("H:\\吃货项目\\foodie\\classify2.html"));

// 关闭OSSClient。
        ossClient.shutdown();
*/
     //   log.info("接收到消息***********************："+username);

    }
}
