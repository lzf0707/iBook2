package com.mall.security.service;

import org.springframework.social.connect.ConnectionData;
import org.springframework.web.context.request.WebRequest;

public interface SignUpUtils {
	void saveConnectionData(WebRequest request,ConnectionData connectionData);
	
	void doPostSignUp(WebRequest request,String userId);
}
