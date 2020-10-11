package com.mall.security.properties;

import lombok.Data;

@Data
public class OAuth2Properties {
	private OAuth2ClientProperties[] clients = {};
	private String jwtSigningKey = "api";
	private String storeType;
}
