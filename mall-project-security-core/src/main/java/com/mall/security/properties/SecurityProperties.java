package com.mall.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mall.security")
public class SecurityProperties {
	/**
	 * 浏览器环境配置
	 */
	private BrowserProperties browser = new BrowserProperties();
	/**
	 * 验证码配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();
	
	/**
	 * 第三方认证配置
	 */
	private SocialProperties social = new SocialProperties();
	
	/**
	 * APP client 配置
	 */
	private OAuth2Properties oauth2 = new OAuth2Properties();
}
