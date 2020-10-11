package com.mall.web.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.mall.Queue.DeferredResultHolder;
import com.mall.Queue.MockQueue;

@RestController
@RequestMapping("/deferred")
public class AsyncDeferredController {

	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	private static final Logger log = LoggerFactory.getLogger(AsyncBasicController.class);

	@GetMapping("/order")
	public DeferredResult<String> order() throws Exception{
		log.info("主线程开始");
		
		//应用1 线程一（主线程）发送消息，等待应用2处理
		//生成8位订单随机码
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		//每一个订单号对应一个处理结果
		DeferredResult<String> result = new DeferredResult<String>();
		//将订单号-处理结果以键值对存入deferredResultHolder中
		deferredResultHolder.getMap().put(orderNumber, result);
		
		log.info("主线程返回");
		return result;
	}
}
