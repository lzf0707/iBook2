package com.mall.security.service;

import org.springframework.social.security.SocialAuthenticationFilter;

public interface SocialAuthenticationFilterPostProcessor {

	void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
