package com.mall.security.properties;

import com.mall.security.enums.LoginType;
import lombok.Data;

@Data
public class BrowserProperties {
	//退出登录跳转请求
	private String logoutUrl;
	//注册跳转请求
	private String signUpUrl;
	//登录跳转请求
	private String loginUrl = "/frontend/imoocSignIn";
	//默认为json
	private LoginType loginType = LoginType.JSON;
	
	private int rememberSeconds = 3600;
	
	/**
	 * session 配置
	 */
	private SessionProperties session = new SessionProperties();
	
}
