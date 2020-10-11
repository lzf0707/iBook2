package com.mall.Interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component //声明注解并在配置文件注册
public class TimeInterceptor implements HandlerInterceptor {

	//处理之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle");
		//用于方法之间传递信息
		request.setAttribute("startTime", new Date().getTime());
		//与filter不同之处，多了一个handler
		//获取类
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		//获取方法
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		//返回false ，后边的方法将不会此时，需要修改
		return true;
	}

	//处理之后，若control层抛出异常，此方法将不会被调用
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("time interceptor：" + (new Date().getTime() - start));
	}

	//无论是否抛出异常，此方法都会被调用
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("time interceptor：" + (new Date().getTime() - start));
		System.out.println("异常：" + ex);
	}

}
