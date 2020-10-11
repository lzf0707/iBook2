package com.mall.security.app.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import com.mall.security.authorize.AuthorizeConfigManager;
import com.mall.security.config.SmsCodeAuthenticationSecurityConfig;
import com.mall.security.config.ValidateCodeSecurityConfig;
import com.mall.security.constants.SecurityConstants;


@Configuration
@EnableResourceServer
public class AppResourceServerConfig extends ResourceServerConfigurerAdapter{

	//验证码配置文件
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	
	@Autowired
	private DataSource datasource;
	
	//短信验证配置文件
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer socialConfigurer;
	
	@Autowired
	private AuthenticationSuccessHandler appAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler appAuthenticationFailHandler;
	
	@Autowired
	private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
	
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//表单（账号密码）登录配置
		http.formLogin() //使用表单登录
			//.httpBasic() //基本访问
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) //指定登录页面，抽取成常量 /authentication/require
			.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_LOGIN_CHECK) //指定提交登录请求
			.successHandler(appAuthenticationSuccessHandler)
			.failureHandler(appAuthenticationFailHandler);
		
		http.apply(validateCodeSecurityConfig)//验证码过滤器
				.and()
			.apply(smsCodeAuthenticationSecurityConfig) //应用短信验证配置
				.and()
			.apply(openIdAuthenticationSecurityConfig)
				.and()
			.apply(socialConfigurer)  //第三方应用登录配置
				.postLoginUrl(SecurityConstants.DEFAULT_INDEX_URL)
				.and()
			.csrf().disable();  //跨站请求服务		
		authorizeConfigManager.config(http.authorizeRequests());
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(datasource);
		//在启动的时候创建表
		//tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	
}
