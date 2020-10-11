package com.mall.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mall.Interceptor.TimeInterceptor;
import com.mall.filter.TimeFilter;

/*
@Configuration用于定义配置类，可替换xml配置文件，
被注解的类内部包含有一个或多个被@Bean注解的方法，
这些方法将会被AnnotationConfigApplicationContext
或AnnotationConfigWebApplicationContext类进行扫描，
并用于构建bean定义，初始化Spring容器。
*/

@Configuration 
public class WebConfiguration extends WebMvcConfigurerAdapter{
	
	@Autowired
	private TimeInterceptor timeInterceptor;
	
	/*
	 * 异步 拦截器配置
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.registerCallableInterceptors(interceptors);
	}
	 */
	
	//WebConfig 需要继承  WebMvcConfigurerAdapter配置类
	@Override  
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(timeInterceptor);
	}
	
	//第三方过滤器注解
	//@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		///注册
		registrationBean.setFilter(timeFilter);		
		//配置需要拦截的URL，与@Component不同的区别，可以指定生效的URL
		List<String> urls = new ArrayList<String>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
}
