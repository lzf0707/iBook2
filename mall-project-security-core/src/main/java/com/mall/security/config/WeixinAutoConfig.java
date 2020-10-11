package com.mall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

import com.mall.security.connect.WeixinConnectionFactory;
import com.mall.security.controller.SocialConnectView;
import com.mall.security.properties.SecurityProperties;
import com.mall.security.properties.WeixinProperties;

@Configuration
@ConditionalOnProperty(prefix = "mall.security.social.weixin",name = "app-id") //在配置了app-id，以下配置才生效
public class WeixinAutoConfig extends SocialAutoConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		// TODO Auto-generated method stub
		WeixinProperties weixinProperties = securityProperties.getSocial().getWeixin();
		return new WeixinConnectionFactory(
				weixinProperties.getProviderId(), 
				weixinProperties.getAppId(), 
				weixinProperties.getAppSecret());
	}
	
	@Bean({"connect/weixinConnected","connect/weixinConnect"}) //绑定、解绑成功公用一个视图
	@ConditionalOnMissingBean(name = "weixinConnectView") //允许用户自定义返回微信绑定成功视图
	public View weixinConnectView() {
		return new SocialConnectView();
	}

}
