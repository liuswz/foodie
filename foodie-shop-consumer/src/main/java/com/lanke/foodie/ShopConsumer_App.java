package com.lanke.foodie;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
//@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
@EnableFeignClients("com.lanke.foodie.service")//basePackages= {"com.atguigu.springcloud"}
//@EnableCasClient
public class ShopConsumer_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(ShopConsumer_App.class, args);
	}
}
