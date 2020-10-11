package com.mall.security.authorize;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import lombok.extern.slf4j.Slf4j;

@Component("rbacService")
@Slf4j
public class RbacServiceImpl implements RbacService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		// TODO Auto-generated method stub
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		if (principal instanceof SocialUser) {
			//如果用户名是admin，就永远返回true
			SocialUser socialUser = (SocialUser) principal;
			// 读取用户所拥有权限的所有URL
			Collection<GrantedAuthority> authorityList = socialUser.getAuthorities();
			for (GrantedAuthority authority : authorityList) {	
				log.info("rabc权限控制：" + request.getRequestURI());
				if (antPathMatcher.match(authority.getAuthority(), request.getRequestURI())) {
					hasPermission = true;
					break;
				}
			}
		}
		return hasPermission;
	}
}
