package com.mall.api.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.mall.security.authorize.AuthorizeConfigProvider;

@Component
public class ApiAuthorizeConifgProvider implements AuthorizeConfigProvider {

	/* (non-Javadoc)
	 * @see com.imooc.security.core.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		//api项目授权配置
		config
			.antMatchers("/frontend/p/bindLocalAuth")
				.permitAll()
			.antMatchers("/frontend/p/**") //指定需要认证的请求
				.authenticated()//未登录用户都需要认证，未登录返回 false
//			.antMatchers("/frontend/p/**")
//				.access("@rbacService.hasPermission(request,authentication)")//任何请求都需要进行授权控制
			; 
		return true;
	}
}
