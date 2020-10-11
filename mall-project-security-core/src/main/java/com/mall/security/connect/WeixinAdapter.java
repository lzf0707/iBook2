package com.mall.security.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import com.mall.security.dto.WeixinUserInfo;
import com.mall.security.service.Weixin;

public class WeixinAdapter implements ApiAdapter<Weixin>{

	private String openId;
	
	public WeixinAdapter() {}
	
	public WeixinAdapter(String openId){
		this.openId = openId;
	}
	
	@Override
	public boolean test(Weixin api) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setConnectionValues(Weixin api, ConnectionValues values) {
		// TODO Auto-generated method stub
		WeixinUserInfo weixinUserInfo = api.getUserInfo(openId);
		values.setDisplayName(weixinUserInfo.getNickname());
		values.setImageUrl(weixinUserInfo.getHeadimgurl());
		values.setProfileUrl(null);
		values.setProviderUserId(weixinUserInfo.getOpenid());
	}

	@Override
	public UserProfile fetchUserProfile(Weixin api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(Weixin api, String message) {
		// TODO Auto-generated method stub
		
	}

}
