/**
 * 
 */
package com.mall.security.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;


/**
 * 验证码信息封装类
 * 
 * @author zhailiang
 *
 */
@Data
public class ValidateCode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1588203828504660915L;

	private String code;
	
	private LocalDateTime expireTime;
	
	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ValidateCode(String code, LocalDateTime expireTime){
		this.code = code;
		this.expireTime = expireTime;
	}
	
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
