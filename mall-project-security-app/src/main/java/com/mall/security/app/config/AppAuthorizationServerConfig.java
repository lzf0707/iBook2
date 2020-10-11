package com.mall.security.app.config;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import com.mall.security.properties.OAuth2ClientProperties;
import com.mall.security.properties.SecurityProperties;

@Configuration
@EnableAuthorizationServer 
public class AppAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired(required = false) //默认不生效
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Autowired(required = false)
	private TokenEnhancer jwtTokenEnhancer;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		endpoints
			.tokenStore(tokenStore) //token 持久化配置
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService);
		
		if(jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
			TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
			List<TokenEnhancer> enhancers = new ArrayList<TokenEnhancer>();
			enhancers.add(jwtTokenEnhancer);
			enhancers.add(jwtAccessTokenConverter);
			enhancerChain.setTokenEnhancers(enhancers);
			
			endpoints
				.tokenEnhancer(enhancerChain)
				.accessTokenConverter(jwtAccessTokenConverter);
		}
	}
	
	//客户端配置，yml配置的client信息将不生效
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// TODO Auto-generated method stub
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
		if(ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) { //不为空
			for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
				builder.withClient(config.getClientId())
					.secret(config.getClientSecret())
					.accessTokenValiditySeconds(config.getAccessTokenValiditySeconds()) //token失效时间 7200秒
					.authorizedGrantTypes("password","authorization_code","refresh_token") //限定授权模式
					.refreshTokenValiditySeconds(259200) //refreshToken 失效时间，要设置的比token失效时间长，不然无意义
					.scopes("all","read","wreite");
			}
		}
		
	}
}
