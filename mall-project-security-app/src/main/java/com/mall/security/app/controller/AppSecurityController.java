package com.mall.security.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.mall.security.app.util.AppSignUpUtils;
import com.mall.security.dto.SocialUserInfo;

@RestController
public class AppSecurityController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@Autowired
	private AppSignUpUtils appSignUpUtils;
	
	//引导去注册页的时候调用，其他时候调用，session里无存储记录
	@GetMapping("/social/signUp")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		SocialUserInfo socialUserInfo = new SocialUserInfo();
		//第一次请求，在浏览器端是可以有session的；之后的请求，因为App无sessionId，所以讲无法获取，需要存储到redis，根据设备id获取
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		socialUserInfo.setProviderId(connection.getKey().getProviderId());
		socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
		socialUserInfo.setNickName(connection.getDisplayName());
		socialUserInfo.setHeadImg(connection.getImageUrl());
		appSignUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
		return socialUserInfo;
	}
}
