/**
 * 
 */
package com.mall.security.service;

import org.springframework.web.context.request.ServletWebRequest;

import com.mall.security.dto.ValidateCode;

/**
 * 校验码生成器
 * @author zhailiang
 *
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 * @param request
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);
	
}
