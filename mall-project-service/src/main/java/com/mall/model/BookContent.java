package com.mall.model;

public class BookContent {

	private Long bookContentId;
	private String bookContentImg;
	private String bookContentDesc;
	private Integer priority;
	private String createTime;
	private Long articleId;
	
	
	public Long getBookContentId() {
		return bookContentId;
	}


	public void setBookContentId(Long bookContentId) {
		this.bookContentId = bookContentId;
	}


	public String getbookContentImg() {
		return bookContentImg;
	}


	public void setbookContentImg(String bookContentImg) {
		this.bookContentImg = bookContentImg;
	}


	public String getBookContentDesc() {
		return bookContentDesc;
	}


	public void setBookContentDesc(String bookContentDesc) {
		this.bookContentDesc = bookContentDesc;
	}


	public Integer getPriority() {
		return priority;
	}


	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public Long getArticleId() {
		return articleId;
	}


	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	

	@Override
	public String toString() {
		return "BookContent [bookContentId=" + bookContentId + ", bookContentImg=" + bookContentImg + ", bookContentDesc="
				+ bookContentDesc + ", priority=" + priority + ", createTime=" + createTime + ", articleId=" + articleId
				+ "]";
	}


	public BookContent() {
		// TODO Auto-generated constructor stub
	}

}
