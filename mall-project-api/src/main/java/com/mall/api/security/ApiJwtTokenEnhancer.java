package com.mall.api.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Component;

@Component("jwtTokenEnhancer")
public class ApiJwtTokenEnhancer implements TokenEnhancer{

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		Map<String, Object> info = new HashMap<String, Object>();
		SocialUser socialUser = (SocialUser) authentication.getUserAuthentication().getPrincipal();
		info.put("userName", socialUser.getUsername());
		 ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
