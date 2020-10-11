package com.mall.model;

public class UserSignin {

	private long userSinginId;
	private long userId;
	private String createTime;
	private int point;
	
	public UserSignin() {
		// TODO Auto-generated constructor stub
	}

	public long getUserSinginId() {
		return userSinginId;
	}

	public void setUserSinginId(long userSinginId) {
		this.userSinginId = userSinginId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "UserSignin [userSinginId=" + userSinginId + ", userId=" + userId + ", createTime=" + createTime
				+ ", point=" + point + "]";
	}
	
}
