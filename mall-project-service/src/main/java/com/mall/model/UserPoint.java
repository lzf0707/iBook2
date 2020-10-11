package com.mall.model;

public class UserPoint {

	private long userPointId;
	private long userId;
	private int point;
	private String createTime;
	private String lastEditTime;
	
	
	public UserPoint() {
		// TODO Auto-generated constructor stub
	}


	public long getUserPointId() {
		return userPointId;
	}


	public void setUserPointId(long userPointId) {
		this.userPointId = userPointId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getLastEditTime() {
		return lastEditTime;
	}


	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}


	@Override
	public String toString() {
		return "UserPoint [userPointId=" + userPointId + ", userId=" + userId + ", point=" + point + ", createTime="
				+ createTime + ", lastEditTime=" + lastEditTime + "]";
	}
	
	

}
