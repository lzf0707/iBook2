package com.mall.security.dto;

import lombok.Data;

@Data
public class SimpleResponse {
	
	public SimpleResponse(Object content,int status) {
		this.content = content;
		this.status = status;
	}
	private Object content;
	private int status;
}
