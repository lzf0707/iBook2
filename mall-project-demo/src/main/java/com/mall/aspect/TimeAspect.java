package com.mall.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class TimeAspect {
	/*
		第一个*含义是：代表所有类型的返回值
		第二个*是代表com.mall.web.controller包下的所有类
		第三个是类下的所有方法，括号中两个点表示任意个形参
	*/
	@Around("execution(* com.mall.*.*(..))")
	public Object handleController(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("time aspect start");
		
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is " + arg);
		}
		
		long start = new Date().getTime();
		Object obj = pjp.proceed();
		System.out.println("aspect : " + (new Date().getTime() - start));
		System.out.println("time aspect end");
		return obj;
	}
}
