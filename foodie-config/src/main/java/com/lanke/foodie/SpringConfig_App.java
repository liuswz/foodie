package com.lanke.foodie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigServer
public class SpringConfig_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringConfig_App.class, args);
	}
}
