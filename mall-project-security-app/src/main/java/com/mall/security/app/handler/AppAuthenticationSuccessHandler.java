package com.mall.security.app.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("mallAuthenticationSuccessHandler")
public class AppAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {//不实现 AuthenticationSuccessHandler 改为继承spring默认成功处理器

	private String credentialsCharset = "UTF-8";
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	private AuthorizationServerTokenServices authorizationServerTokenServices;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("登录成功");
		//获取请求头中的clientId，clientSecret
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Basic ")) {
			throw new UnapprovedClientAuthenticationException("请求头中无Client信息");
		}
			
		String[] tokens = extractAndDecodeHeader(header, request);
		assert tokens.length == 2;
		String clientId = tokens[0];	
		String clientSecret = tokens[1];	
		//根据clientId获取clientDetails
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
		//验证clientDetails是否正确
		if(clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在：" + clientId);
		}else if(!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配：" + clientId);
		}
		//构建TokenRequest
		/*
		 * requestParameters 用于创建Authentication，由于成功处理器已存在，故不需要
		 * grantType 用户自定义
		 */
		@SuppressWarnings("unchecked")
		TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
		//构建OAuth2Request
		OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
		//构建OAuth2Authentication
		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
		//通过AuthorizationServerTokenServices构建OAuth2AccessToken
		OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(token));
	}
		
	/**
	 * Decodes the header into a username and password.
	 *
	 * @throws BadCredentialsException if the Basic header is not present or is not valid
	 * Base64
	 */
	private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
			throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			throw new BadCredentialsException(
					"Failed to decode basic authentication token");
		}

		String token = new String(decoded, getCredentialsCharset(request));

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}
	
	protected String getCredentialsCharset(HttpServletRequest httpRequest) {
		return this.credentialsCharset;
	}

}
