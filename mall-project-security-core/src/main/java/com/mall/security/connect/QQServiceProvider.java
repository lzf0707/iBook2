package com.mall.security.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
//import org.springframework.social.oauth2.OAuth2Template;
import com.mall.security.service.QQ;
import com.mall.security.service.impl.QQImpl;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{

	private String appId;
	
	//获取授权码
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	
	//根据授权码获取令牌（assess_token）
	private static final String URL_ASSESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	public QQServiceProvider(String appId,String appSecret) {
		// TODO Auto-generated constructor stub
		//super(new OAuth2Template(clientId, clientSecret, URL_AUTHORIZE, URL_ASSESS_TOKEN));
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ASSESS_TOKEN));
		this.appId = appId;  //需要重新设置appId，不然getApi中的appId都会是空的
	}

	@Override
	public QQ getApi(String accessToken) {
		// TODO Auto-generated method stub
		return new QQImpl(accessToken, appId); //返回实现类
	}

}
