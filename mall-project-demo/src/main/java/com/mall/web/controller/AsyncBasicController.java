package com.mall.web.controller;

import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncBasicController {
	
	private static final Logger log = LoggerFactory.getLogger(AsyncBasicController.class);

	@GetMapping("/order")
	public Callable<String> order(){
		log.info("主线程开始");
		//使用Runnable异步处理Rest服务
		//开启一个线程
		Callable<String> result = new Callable<String>() {
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				log.info("副线程开始");
				Thread.sleep(1000);
				log.info("副线程结束");
				return "success";
			}
		};
		log.info("主线程结束");
		return result;
	}
}
