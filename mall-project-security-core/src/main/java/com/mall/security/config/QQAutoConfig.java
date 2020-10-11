package com.mall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.mall.security.connect.QQConnectionFactory;
import com.mall.security.properties.QQProperties;
import com.mall.security.properties.SecurityProperties;

//ConnectionFactory配置
@Configuration
@ConditionalOnProperty(prefix = "mall.security.social.qq",name = "app-id") //在配置了app-id，以下配置才生效
public class QQAutoConfig extends SocialAutoConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		// TODO Auto-generated method stub
		QQProperties qqProperties = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
	}

}
