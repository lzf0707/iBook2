package com.mall.model;

import lombok.Data;

import java.util.List;

@Data
public  class BookArticle {
	private Long articleId;
	private String articleName;
	private String articleDesc;
	private String createTime;
	private String lastEditTime;
	private Integer enableStatus;
	private Long bookId;
	private Integer point;
	private Integer priority;
	private List<BookContent> contentList;
	private Long lastId;
	private Long nextId;
	private String lockFlag;
}
