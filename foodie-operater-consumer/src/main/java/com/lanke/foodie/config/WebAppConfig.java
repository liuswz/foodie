//package com.lanke.foodie.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//
//@SuppressWarnings("deprecation")
//
//@Configuration
//public class WebAppConfig extends WebMvcConfigurerAdapter {
//
//	@Autowired
//	private BaseInterceptor bi;
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//
//        registry.addInterceptor(bi).addPathPatterns("/**")   //拦截哪些路径("/**":代表拦截所有路径);
//        .excludePathPatterns("/operaterconsumer/shopdetail/logout", "/operaterconsumer/shopdetail/login"); //不拦截哪些路径;
//
//	}
//
//}
