package com.foodie.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DashBoard_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(DashBoard_App.class, args);
	}
}
