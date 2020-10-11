package com.mall.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import com.mall.security.app.filter.OpenIdAuthenticationFilter;
import com.mall.security.app.handler.AppAuthenticationFailHandler;
import com.mall.security.app.handler.AppAuthenticationSuccessHandler;
import com.mall.security.app.provider.OpenIdAuthenticationProvider;

@Component
public class OpenIdAuthenticationSecurityConfig 
	extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	@Autowired
	private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;
	
	@Autowired
	private AppAuthenticationFailHandler appAuthenticationFailHandler;
	
	@Autowired
	private SocialUserDetailsService socialUserDetailsService;
	
	@Autowired
	private UsersConnectionRepository usersConnectionRepository;
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		OpenIdAuthenticationFilter openIdAuthenticationFilter = new OpenIdAuthenticationFilter();
		openIdAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		openIdAuthenticationFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
		openIdAuthenticationFilter.setAuthenticationFailureHandler(appAuthenticationFailHandler);
		
		OpenIdAuthenticationProvider openIdAuthenticationProvider = new OpenIdAuthenticationProvider();
		openIdAuthenticationProvider.setSocialUserDetailsService(socialUserDetailsService);
		openIdAuthenticationProvider.setUsersConnectionRepository(usersConnectionRepository);
		
		http.authenticationProvider(openIdAuthenticationProvider)
			.addFilterAfter(openIdAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
	}
}
