package com.mall.security.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.mall.security.service.SocialAuthenticationFilterPostProcessor;

@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor{

	@Autowired
	private AuthenticationSuccessHandler mallAuthenticationSuccessHandler;
	
	@Override
	public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
		// TODO Auto-generated method stub
		//传入自定义的成功处理器
		socialAuthenticationFilter.setAuthenticationSuccessHandler(mallAuthenticationSuccessHandler);
	}

}
