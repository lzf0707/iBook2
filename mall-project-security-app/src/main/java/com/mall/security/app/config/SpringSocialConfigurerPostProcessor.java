package com.mall.security.app.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import com.mall.security.config.MySpringSocialConfigurer;

//spring所有bean初始化之前、之后都会经过两个方法
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		if(StringUtils.equals(beanName, "socialConfigurer")) {
			MySpringSocialConfigurer mySpringSocialConfigurer = (MySpringSocialConfigurer) bean;
			//替换浏览器的注册页面跳转url
			mySpringSocialConfigurer.signupUrl("/social/signUp"); //配置注册跳转路径
		}
		return bean;
	}

}
