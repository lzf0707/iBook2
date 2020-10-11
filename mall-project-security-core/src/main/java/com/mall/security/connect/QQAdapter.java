package com.mall.security.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.mall.security.dto.QQUserInfo;
import com.mall.security.service.QQ;

public class QQAdapter implements ApiAdapter<QQ>{

	//测试当前API是否可用
	@Override
	public boolean test(QQ api) {
		// TODO Auto-generated method stub
		return true;//不做判断，直接返回true
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		// TODO Auto-generated method stub
		QQUserInfo qqUserInfo = api.getUserInfo(); //获取用户信息
		//转为标准结构信息
		values.setDisplayName(qqUserInfo.getNickname());
		values.setImageUrl(qqUserInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		values.setProviderUserId(qqUserInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		// TODO Auto-generated method stub
		//do noting
	}

}
