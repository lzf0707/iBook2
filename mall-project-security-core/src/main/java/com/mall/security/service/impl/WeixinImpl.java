package com.mall.security.service.impl;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.security.dto.WeixinUserInfo;
import com.mall.security.service.Weixin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//不能加Component，这样就是单例的了，不符合需求
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin{

	
	//不需要传入assess_token，父类会自动帮我们处理
	private static final String URL_GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?openid=";
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	//openId通过assess_token获取，assess_token是认证授权成功后传入,appId从系统配置文件中获取
	public WeixinImpl(String assess_token) {
		super(assess_token,TokenStrategy.ACCESS_TOKEN_PARAMETER); //token传入策略，发请求会自动把assess_token作为查询参数
	}
	
	/**
	 * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
	 */
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		messageConverters.remove(0);
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return messageConverters;
	}
	
	@Override
	public WeixinUserInfo getUserInfo(String openId) {
		// TODO Auto-generated method stub
		//凭借 获取用户信息url
		String url = URL_GET_USERINFO + openId;
		String result = getRestTemplate().getForObject(url, String.class); //发送请求，并获取结果字符串
		log.info(result); //打印结果
		if(StringUtils.contains(result, "errcode")) {
			return null;
		}
		WeixinUserInfo weixinUserInfo = null;
		try {
			//还需要将openId放入qqUserInfo中
			weixinUserInfo = objectMapper.readValue(result, WeixinUserInfo.class);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getStackTrace() + "\n" +e.getMessage());
			throw new RuntimeException("获取用户信息失败");	
		} //自动将字符串转为我们需要的类格式
		return weixinUserInfo;
	}

}
