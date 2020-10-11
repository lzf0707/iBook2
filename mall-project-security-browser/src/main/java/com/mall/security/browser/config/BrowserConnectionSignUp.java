package com.mall.security.browser.config;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import com.mall.common.util.TradeIdGenerate;
import com.mall.entity.LocalAuthEntity;
import com.mall.entity.PersonInfoEntity;
import com.mall.service.LocalAuthService;
import com.mall.service.PersonInfoService;

//用于第三方社交登录，实现自动注册
@Component
public class BrowserConnectionSignUp implements ConnectionSignUp{

	@Autowired
	private LocalAuthService localAuthService;
	
	@Autowired
	private PersonInfoService personInfoService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String execute(Connection<?> connection) {
		// TODO Auto-generated method stub
		//不管是注册用户、还是绑定用户，都会拿到一个唯一UserId标识
		//用户信息注册，昵称、头像为系统默认，待登录后，用户自己完善
		PersonInfoEntity personInfoEntity = new PersonInfoEntity();
		personInfoEntity.setCreateTime(new Date());
		personInfoEntity.setLastEditTime(new Date());
		personInfoEntity.setEnableStatus(0);
		//随机字母+数字
		personInfoEntity.setName(TradeIdGenerate.getTradeId(10));
		//头像网上随机选一张
		personInfoEntity.setProfileImg("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3215943721,3617856399&fm=26&gp=0.jpg");
		personInfoEntity.setUserType(1);
		personInfoService.save(personInfoEntity);
		
		//创建本地账号：默认使用社交账号名称,密码为123456
		LocalAuthEntity localAuthEntity = new LocalAuthEntity();
		localAuthEntity.setCreateTime(new Date());
		localAuthEntity.setLastEditTime(new Date());
		String username = TradeIdGenerate.getTradeId(3);
		localAuthEntity.setUserName(username);
		localAuthEntity.setPassword(passwordEncoder.encode("123456"));
		localAuthEntity.setUserId(personInfoEntity.getUserId());
		localAuthService.save(localAuthEntity);
		return username;
	}

}
