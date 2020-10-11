package com.mall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import com.mall.security.filter.SmsCodeAuthenticationFilter;
import com.mall.security.provider.SmsAuthenticationProvider;
import com.mall.security.service.MyUserDetailsService;

@Component
public class SmsCodeAuthenticationSecurityConfig 
	extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailHandler;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//过滤器初始化
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailHandler);
		
		//provider初始化
		SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
		smsAuthenticationProvider.setMyUserDetailsService(myUserDetailsService);
		
		//配置
		http.authenticationProvider(smsAuthenticationProvider) //provider配置
			.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); //过滤器配置
	}
}
