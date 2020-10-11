package com.mall.security.app.provider;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import com.mall.security.app.token.OpenIdAuthenticationToken;

public class OpenIdAuthenticationProvider implements AuthenticationProvider{

	private SocialUserDetailsService socialUserDetailsService;
	private UsersConnectionRepository usersConnectionRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;		
		Set<String> providerUserIds = new HashSet<String>();
		providerUserIds.add((String) authenticationToken.getPrincipal());
		Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(
				authenticationToken.getProviderId(), providerUserIds);
		if(CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		String userId = userIds.iterator().next();
		UserDetails user = socialUserDetailsService.loadUserByUserId(userId);
		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		OpenIdAuthenticationToken authenticationResult = new OpenIdAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
	}


	public SocialUserDetailsService getSocialUserDetailsService() {
		return socialUserDetailsService;
	}


	public void setSocialUserDetailsService(SocialUserDetailsService socialUserDetailsService) {
		this.socialUserDetailsService = socialUserDetailsService;
	}


	public UsersConnectionRepository getUsersConnectionRepository() {
		return usersConnectionRepository;
	}

	public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository) {
		this.usersConnectionRepository = usersConnectionRepository;
	}
}
