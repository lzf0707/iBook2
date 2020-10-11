package com.mall.security.properties;

import lombok.Data;

@Data
public class QQProperties{
	private String providerId = "qq";
	/**
	 * Application id.
	 */
	private String appId;

	/**
	 * Application secret.
	 */
	private String appSecret;

}
