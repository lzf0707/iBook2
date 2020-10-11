package com.mall.security.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;
import javax.sql.DataSource;

import com.mall.security.authorize.AuthorizeConfigManager;
import com.mall.security.config.FormAuthenticationConfig;
import com.mall.security.config.SmsCodeAuthenticationSecurityConfig;
import com.mall.security.config.ValidateCodeSecurityConfig;
import com.mall.security.constants.SecurityConstants;
import com.mall.security.properties.SecurityProperties;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//验证码配置文件
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	//短信验证配置文件
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	//表单（账号密码）登录配置
	@Autowired
	private FormAuthenticationConfig formAuthenticationConfig;
	
	@Autowired
	private SpringSocialConfigurer socialConfigurer;
	
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
	
	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
		
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(datasource);
		//在启动的时候创建表
		//tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//表单（账号密码）登录配置
		formAuthenticationConfig.configure(http);
		
		http.apply(validateCodeSecurityConfig)//验证码过滤器
				.and()
			.apply(smsCodeAuthenticationSecurityConfig) //应用短信验证配置
				.and()
			.apply(socialConfigurer)  //第三方应用登录配置
				.postLoginUrl(SecurityConstants.DEFAULT_INDEX_URL)
				.and()
			.rememberMe()  //记住我功能配置(浏览器特有功能，不抽取)
				.tokenRepository(persistentTokenRepository())   //repository配置
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberSeconds())  //过期秒数配置
				.userDetailsService(userDetailsService)   //自动登录配置
				.and()
			.sessionManagement()
				.invalidSessionStrategy(invalidSessionStrategy) //设置浏览器失效自动跳转请求地址，返回失效提示信息
				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions()) //并发登录数 1
				.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
				.expiredSessionStrategy(sessionInformationExpiredStrategy)
				.and()
				.and()
			.logout()
				.logoutUrl(SecurityConstants.DEFAULT_LOGOUT_URL)  //默认登出请求
				//.logoutSuccessUrl("") //登录成功后跳转地址,与logoutSuccessHandler互斥，只可配置一项
				.logoutSuccessHandler(logoutSuccessHandler)
				.deleteCookies("JSESSIONID")
				.and()
			.csrf().disable();  //跨站请求服务	
		
		authorizeConfigManager.config(http.authorizeRequests());
	}
}
