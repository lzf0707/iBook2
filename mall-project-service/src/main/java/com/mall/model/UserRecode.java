package com.mall.model;

public class UserRecode {

	private long userRecodeId;
	private long userId;
	private BookArticle article;
	private String createTime;
	private int point;
	private Book book;
	
	
	public long getUserRecodeId() {
		return userRecodeId;
	}


	public void setUserRecodeId(long userRecodeId) {
		this.userRecodeId = userRecodeId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public BookArticle getArticle() {
		return article;
	}


	public void setArticle(BookArticle article) {
		this.article = article;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}

    
	@Override
	public String toString() {
		return "UserRecode [userRecodeId=" + userRecodeId + ", userId=" + userId + ", articleId=" + article
				+ ", createTime=" + createTime + ", point=" + point + ", bookId=" + book + "]";
	}


	public UserRecode() {
		// TODO Auto-generated constructor stub
	}

}
