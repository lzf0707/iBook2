package com.mall.security.browser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import com.mall.security.dto.SimpleResponse;
import com.mall.security.dto.SocialUserInfo;
import com.mall.security.properties.SecurityProperties;


@RestController
public class BrowserSecurityController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	//需要身份认证时，跳转这里
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED) //返回状态码，未授权
	public ResponseEntity<SimpleResponse>  requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			log.info("引发跳转的url：" + targetUrl);
			String loginUrl = securityProperties.getBrowser().getLoginUrl();
			if(!StringUtils.isBlank(loginUrl)) { //判断是否有配置登录跳转url，若是，直接跳转到登录页
				redirectStrategy.sendRedirect(request, response, loginUrl);
			}
		}
		return ResponseEntity.ok (new SimpleResponse("用户未登录，请登录后操作!",HttpStatus.UNAUTHORIZED.value()));
	}
	
	//引导去注册页的时候调用，其他时候调用，session里无存储记录
	@GetMapping("/social/user")
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		SocialUserInfo socialUserInfo = new SocialUserInfo();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		socialUserInfo.setProviderId(connection.getKey().getProviderId());
		socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
		socialUserInfo.setNickName(connection.getDisplayName());
		socialUserInfo.setHeadImg(connection.getImageUrl());
		return socialUserInfo;
	}
	
	@GetMapping("/session/invalid")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<SimpleResponse> sessionInvalid(){
		return ResponseEntity.ok(new SimpleResponse("session失效", HttpStatus.UNAUTHORIZED.value()));
	}
}
