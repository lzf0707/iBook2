package com.mall.security.properties;

import lombok.Data;

@Data
public class OAuth2ClientProperties {
	private String clientId;
	private String clientSecret;
	private Integer accessTokenValiditySeconds = 0;
}
