package com.mall.security.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import com.mall.security.service.Weixin;
import com.mall.security.service.impl.WeixinImpl;

public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin>{

	/**
	 * 微信获取授权码的url
	 */
	private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
	/**
	 * 微信获取accessToken的url
	 */
	private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	public WeixinServiceProvider(String appId,String appSecret) {
		super(new WeixinOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		// TODO Auto-generated constructor stub
	}

	@Override
	public Weixin getApi(String accessToken) {
		// TODO Auto-generated method stub
		return new WeixinImpl(accessToken);
	}

}
