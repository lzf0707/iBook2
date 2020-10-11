package com.mall.security.service;

import com.mall.security.dto.WeixinUserInfo;

public interface Weixin {
	//微信互联，与QQ不同之处在于，获取token的同时还能获取openId
	WeixinUserInfo getUserInfo(String openId);
}
