package com.mall.security.app.util;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import com.mall.security.app.exception.AppSecretException;
import com.mall.security.service.SignUpUtils;


//注册工具类，将存储到session的信息缓存到redis中
@Component
public class AppSignUpUtils implements SignUpUtils{

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private UsersConnectionRepository usersConnectionRepository; 
	
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;
	
	@Override
	public void saveConnectionData(WebRequest request,ConnectionData connectionData) {
		redisTemplate.opsForValue().set(getKey(request), connectionData,10,TimeUnit.MINUTES);
	}
	
	//注册（新增记录）
	@Override
	public void doPostSignUp(WebRequest request,String userId) {
		String key = getKey(request);
		if(!redisTemplate.hasKey(key)) {
			throw new AppSecretException("无法找到缓存的用户社交账号信息");
		}
		//拿到缓存的用户信息
		ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
		Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
				.createConnection(connectionData);
		//新增记录
		usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);
		//清理缓存
		redisTemplate.delete(key);
	}
	
	private String getKey(WebRequest request) {
		String deviceId = request.getHeader("deviceId");
		if(StringUtils.isBlank(deviceId)) {  //若请求头中未包含 设备id，抛出异常
			throw new AppSecretException("设备参数Id不可为空!");
		}
		return "mall:security:social.connect." + deviceId;
	}
}
