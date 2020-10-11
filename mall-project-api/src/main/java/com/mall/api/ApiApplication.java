package com.mall.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mall"})
public class ApiApplication{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ApiApplication.class, args);
	}
}
