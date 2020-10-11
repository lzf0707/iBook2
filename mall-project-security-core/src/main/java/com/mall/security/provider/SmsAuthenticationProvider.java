package com.mall.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import com.mall.security.service.MyUserDetailsService;
import com.mall.security.token.SmsAuthenticationToken;

public class SmsAuthenticationProvider implements AuthenticationProvider{

	private MyUserDetailsService myUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		//读取token中的用户密码，查询用户信息
		SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken)authentication;
		UserDetails user = myUserDetailsService.loadUserByMobile((String) authenticationToken.getPrincipal());
		if(user == null) { //用户为空抛异常
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		//生成验证通过的token
		SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	//判断传入进来的token类型
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return SmsAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public MyUserDetailsService getMyUserDetailsService() {
		return myUserDetailsService;
	}

	public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
	}
}
