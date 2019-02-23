package com.lanke.foodie;


import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
//@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
@EnableFeignClients()//basePackages= {"com.atguigu.springcloud"}
public class ShopDishesConsumer_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(ShopDishesConsumer_App.class, args);
	}
}
