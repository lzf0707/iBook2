package com.mall.model;

import lombok.Data;

@Data
public class LocalAuth {
	private Long localAuthId;
	private PersonInfo user;
	private String userName;
	private String password;
	private String createTime;
	private String lastEditTime;
	private String mobile;
}
