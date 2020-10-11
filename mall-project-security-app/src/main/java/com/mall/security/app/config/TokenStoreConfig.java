package com.mall.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import com.mall.security.properties.SecurityProperties;

@Configuration
public class TokenStoreConfig {
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	@ConditionalOnProperty(prefix = "mall.security.oauth2",name = "storeType",havingValue = "redis") //默认不使用
	public TokenStore redisTokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}
	
	//检查配置项前缀，
	@ConditionalOnProperty(prefix = "mall.security.oauth2",name = "storeType",havingValue = "jwt",matchIfMissing = true)
	@Configuration
	public static class JwtTokenConfig{
		
		@Autowired
		private SecurityProperties securityProperties;
		
		@Autowired
		private TokenEnhancer tokenEnhancer;
		
		@Bean
		public TokenStore jwtTokenStore() {
			return new JwtTokenStore(jwtAccessTokenConverter());
		}
		
		//用于token生成处理
		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter() {
			JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
			accessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey()); //密钥配置,密钥签发令牌，需要存储好，以防泄露，由于是标准配置，一旦泄露，他人就可使用此密钥进行信息获取
			return accessTokenConverter;
		}
		
		//令牌增强配置
		@Bean
		@ConditionalOnMissingBean(name = "jwtTokenEnhancer")
		public TokenEnhancer jwtTokenEnhancer() {
			return tokenEnhancer;//自定义
		}
	}
}
