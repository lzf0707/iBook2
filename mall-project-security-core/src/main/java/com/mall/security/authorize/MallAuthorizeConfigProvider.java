package com.mall.security.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.mall.security.constants.SecurityConstants;
import com.mall.security.properties.SecurityProperties;


/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * 
 * @author zhailiang
 *
 */
@Component
@Order(Integer.MIN_VALUE) //最小值，有限执行
public class MallAuthorizeConfigProvider implements AuthorizeConfigProvider{
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		// TODO Auto-generated method stub
		config.antMatchers(
				SecurityConstants.DEFAULT_UNAUTHENTICATION_URL
				,securityProperties.getBrowser().getLoginUrl() == null ? 
						"/test" : securityProperties.getBrowser().getLoginUrl()
				,SecurityConstants.DEFALUT_STATIC_RESOURCES
				,SecurityConstants.DEFAULT_REGIST_URL
			).permitAll(); //访问此url，不需要身份认证
			//.anyRequest() //任何请求
		return false;
	}

}
