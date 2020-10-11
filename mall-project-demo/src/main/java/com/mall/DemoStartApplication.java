package com.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController //提供restful服务
public class DemoStartApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(DemoStartApplication.class, args);
	}

	@GetMapping("/hello/{id}")
	public String hello(@PathVariable String id) {
		System.out.println("hello " + id);
		return "springboot hello world!";
	} 
}
