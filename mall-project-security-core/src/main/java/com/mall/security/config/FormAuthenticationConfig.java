package com.mall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mall.security.constants.SecurityConstants;

//账号密码登录配置 
@Component
public class FormAuthenticationConfig {
	
	@Autowired
	private AuthenticationSuccessHandler mallAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler mallAuthenticationFailHandler;
	
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin() //使用表单登录
			//.httpBasic() //基本访问
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) //指定登录页面，抽取成常量 /authentication/require
			.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_LOGIN_CHECK) //指定提交登录请求
			.successHandler(mallAuthenticationSuccessHandler)
			.failureHandler(mallAuthenticationFailHandler);
	}
}
