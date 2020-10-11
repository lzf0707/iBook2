/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.mall.security.exception;

public class UserNotFoundException extends SecurityAuth2Exception {

  

	/**
	 * 
	 */
	private static final long serialVersionUID = 997226157741508198L;

	public UserNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "user_not_found";
    }
}
