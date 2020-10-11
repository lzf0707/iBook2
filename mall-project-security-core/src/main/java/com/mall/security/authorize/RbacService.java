package com.mall.security.authorize;


import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

//权限控制
public interface RbacService {
	boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
