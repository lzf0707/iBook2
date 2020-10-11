package com.mall.security.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.security.dto.QQUserInfo;
import com.mall.security.service.QQ;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//不能加Component，这样就是单例的了，不符合需求
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{

	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s"; 
	
	//不需要传入assess_token，父类会自动帮我们处理
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
		
	private String openId;
	
	private String appId;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	//openId通过assess_token获取，assess_token是认证授权成功后传入,appId从系统配置文件中获取
	public QQImpl(String assess_token,String appId) {
		super(assess_token,TokenStrategy.ACCESS_TOKEN_PARAMETER); //token传入策略，发请求会自动把assess_token作为查询参数
		this.appId = appId;
		//openId 发请求获取
		String url = String.format(URL_GET_OPENID, assess_token); //拼接
		String result = getRestTemplate().getForObject(url, String.class); //发送请求，并获取结果字符串
		log.info(result);
		//从返回结果中截取openId
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}
	
	@Override
	public QQUserInfo getUserInfo() {
		// TODO Auto-generated method stub
		//凭借 获取用户信息url
		String url = String.format(URL_GET_USERINFO, appId,openId);
		String result = getRestTemplate().getForObject(url, String.class); //发送请求，并获取结果字符串
		log.info(result); //打印结果
		try {
			//还需要将openId放入qqUserInfo中
			QQUserInfo qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
			qqUserInfo.setOpenId(openId);
			return qqUserInfo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getStackTrace() + "\n" +e.getMessage());
			throw new RuntimeException("获取用户信息失败");	
		} //自动将字符串转为我们需要的类格式
	}

}
