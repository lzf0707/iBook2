package com.mall.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import com.mall.security.properties.SecurityProperties;
import com.mall.security.service.SocialAuthenticationFilterPostProcessor;

@Configuration
@EnableSocial
@Order(10) //确保了 SocialConfig 会被最后加载
public class SocialConfig extends SocialConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;
	
	@Autowired(required = false) //false 不一定会提供，使用默认处理器；反之则使用提供的处理器
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		// TODO Auto-generated method stub	
		//数据源、连接工厂、加解密
		JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());//不进行加密
		//设置前缀
		jdbcUsersConnectionRepository.setTablePrefix("tb_");
		if(connectionSignUp != null) {
			jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
		}
		return jdbcUsersConnectionRepository;
	}
	
	
	
	//SocialAuthticationFilter 配置
	@Bean
	public SpringSocialConfigurer socialConfigurer() {
		MySpringSocialConfigurer mySpringSocialConfigurer = new MySpringSocialConfigurer(securityProperties.getSocial().getFilterProcessesUrl());
		mySpringSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl()); //配置注册页面
		//传入自定义处理器
		mySpringSocialConfigurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
		return mySpringSocialConfigurer;
		//return new SpringSocialConfigurer();	
	}
	
	//用于获取社交账号信息，确认唯一的userId
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator, 
				getUsersConnectionRepository(connectionFactoryLocator));
	}
}
