package com.mall.security.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import com.mall.security.service.SocialAuthenticationFilterPostProcessor;

public class MySpringSocialConfigurer extends SpringSocialConfigurer{
	private String filterProcessesUrl;
	
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;
	
	public MySpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	
	@Override
	protected <T> T postProcess(T object) {
		// TODO Auto-generated method stub
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		if(socialAuthenticationFilterPostProcessor != null) {	//若传入的filter不为空，则调用
			socialAuthenticationFilterPostProcessor.process(filter);
		}
		return super.postProcess(object);
	}

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	public SocialAuthenticationFilterPostProcessor getSocialAuthenticationFilterPostProcessor() {
		return socialAuthenticationFilterPostProcessor;
	}

	public void setSocialAuthenticationFilterPostProcessor(
			SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
		this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
	}
}
