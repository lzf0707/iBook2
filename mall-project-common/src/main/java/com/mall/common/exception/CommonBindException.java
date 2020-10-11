/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.mall.common.exception;

import com.mall.common.enums.CommonHttpStatus;
import org.springframework.http.HttpStatus;

public class CommonBindException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = -4137688758944857209L;

	/**
	 * http状态码
	 */
	private Integer httpStatusCode;


	/**
	 * @param httpStatus http状态码
	 */
	public CommonBindException(CommonHttpStatus httpStatus) {
		super(httpStatus.getMsg());
		this.httpStatusCode = httpStatus.value();
	}

	/**
	 * @param httpStatus http状态码
	 */
	public CommonBindException(CommonHttpStatus httpStatus, String msg) {
		super(msg);
		this.httpStatusCode = httpStatus.value();
	}


	public CommonBindException(String msg) {
		super(msg);
		this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
	}


	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

}
