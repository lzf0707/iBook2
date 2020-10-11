package com.mall.model;

import lombok.Data;

@Data
public class UserArticle {
	private long userArticleId;
	private long userId;
	private BookArticle article;
	private String createTime;
	private Book book;
	private Integer point;
}
