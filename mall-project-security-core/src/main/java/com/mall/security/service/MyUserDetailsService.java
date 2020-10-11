package com.mall.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.mall.security.exception.UserNotFoundException;

public interface MyUserDetailsService extends UserDetailsService{
	//根据号码查找用户信息
	UserDetails loadUserByMobile(String mobile) throws UserNotFoundException;
}
