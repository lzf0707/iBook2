package com.mall.security.connect;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QQOAuth2Template extends OAuth2Template{

	public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true); //设为true，默认发请求就会带上clientId、clientSecret
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RestTemplate createRestTemplate() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = super.createRestTemplate();
		//添加一个新的Converters
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}
	
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		// TODO Auto-generated method stub
		//Map.class json格式，String.class 字符串格式
		String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		log.info("获取assessToken响应：" + responseStr);
		String[] items = StringUtils.splitByWholeSeparator(responseStr, "&");
		String accessToken = StringUtils.substringAfterLast(items[0],"=");
		Long expiresIn = new Long(StringUtils.substringAfterLast(items[1],"="));
		String refreshToken = StringUtils.substringAfterLast(items[2],"=");
		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
	}
	

}
