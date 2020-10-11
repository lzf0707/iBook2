package com.mall.security.properties;

import lombok.Data;

@Data
public class WeixinProperties{
	private String providerId = "weixin";
	/**
	 * Application id.
	 */
	private String appId;

	/**
	 * Application secret.
	 */
	private String appSecret;

}
