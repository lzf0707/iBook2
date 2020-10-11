package com.mall.security.browser.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.security.enums.LoginType;
import com.mall.security.properties.SecurityProperties;

@Component("mallAuthenticationSuccessHandler")
public class BorwserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {//不实现 AuthenticationSuccessHandler 改为继承spring默认成功处理器

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("登录成功");
		
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {//若配置为json 登录成功，返回json
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}else { //若配置为跳转，登录成功，进行相应的跳转
			super.onAuthenticationSuccess(request, response, authentication);
		}
		
	}

}
