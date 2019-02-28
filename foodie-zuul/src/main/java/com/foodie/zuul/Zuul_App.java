package com.foodie.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Zuul_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(Zuul_App.class, args);
	}
//	@Bean
//	public CorsFilter corsFilter() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		final CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		config.setMaxAge(18000L);
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
//	}

}
