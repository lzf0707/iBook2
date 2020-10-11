package com.mall.model;

public class UserSubscribe {

	private long userSubscribeId;
	private long userId;
	private Book book;
	private String createTime;
	
	public long getUserSubscribeId() {
		return userSubscribeId;
	}

	public void setUserSubscribeId(long userSubscribeId) {
		this.userSubscribeId = userSubscribeId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	@Override
	public String toString() {
		return "UserSubscribe [userSubscribeId=" + userSubscribeId + ", userId=" + userId + ", book=" + book
				+ ", createTime=" + createTime + "]";
	}

	public UserSubscribe() {
		// TODO Auto-generated constructor stub
	}

}
