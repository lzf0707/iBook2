package com.mall.security.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import com.mall.security.service.QQ;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ>{

	public QQConnectionFactory(String providerId, String appId,String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
		// TODO Auto-generated constructor stub
	}

}
