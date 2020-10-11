package com.mall.Queue;

import org.springframework.stereotype.Component;

//消息队列对象
@Component
public class MockQueue {
	//下单请求
	private String placeOrder;
	//完成请求
	private String completeOrder;
	
	public String getPlaceOrder() {
		return placeOrder;
	}
	
	//相当于应用2
	public void setPlaceOrder(String placeOrder){
		//单开线程，模拟应用2
		new Thread(() ->  {
			System.out.println("接到下单请求：" + placeOrder);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
			System.out.println("下单请求完毕：" + placeOrder);	
		}).start();
		
	}
	public String getCompleteOrder() {
		return completeOrder;
	}
	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
}
