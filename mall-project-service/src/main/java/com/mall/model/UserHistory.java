package com.mall.model;

import lombok.Data;

@Data
public class UserHistory {
	private long userHistoryId;
	private long userId;
	private BookArticle article;
	private String createTime;
	private Book book;
}
