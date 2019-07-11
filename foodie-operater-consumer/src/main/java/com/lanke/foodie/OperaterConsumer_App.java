package com.lanke.foodie;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients("com.lanke.foodie.service")
@ServletComponentScan
//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
//@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
//@RibbonClient(name="FOODIE-SHOPDETAIL")

public class OperaterConsumer_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(OperaterConsumer_App.class, args);
	}
}
