//package com.lanke.foodie.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean registFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new AuthFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("AuthFilter");
//        registration.setOrder(1);
//        return registration;
//    }
//
//}