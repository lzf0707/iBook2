package com.mall.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

public class ApiUser extends SocialUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4170810001772308379L;
	private Integer uId;
	private String mobile;
	private Integer localAuthId;
	
	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getLocalAuthId() {
		return localAuthId;
	}

	public void setLocalAuthId(Integer localAuthId) {
		this.localAuthId = localAuthId;
	}

	public ApiUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public ApiUser(Integer uId,String mobile,Integer localAuthId,String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		// TODO Auto-generated constructor stub
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.uId = uId;
		this.localAuthId = localAuthId;
		this.mobile = mobile;
	}

}
