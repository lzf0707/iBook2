package com.mall.listener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mall.Queue.DeferredResultHolder;
import com.mall.Queue.MockQueue;
import com.mall.web.controller.AsyncBasicController;

//应用1 线程二，监听应用2处理完的结果
@Component
public class QueueListener implements ApplicationListener<ApplicationEvent>{

	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	private static final Logger log = LoggerFactory.getLogger(AsyncBasicController.class);
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		//应用1 线程二
		new Thread(() -> {
			while(true) {
				//消息对象下单属性不为空，则处理
				if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					String orderNumber = mockQueue.getCompleteOrder();
					log.info("返回订单处理结果" + orderNumber);
					//返回结果
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success!");
					//处理完成，将消息对象下单属性置为空
					mockQueue.setCompleteOrder(null);
				}else { //反之则等待
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
