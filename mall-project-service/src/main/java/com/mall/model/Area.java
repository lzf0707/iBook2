package com.mall.model;

public class Area {

	private Long areaId;
	private String areaName;
	private Integer priority;
	private String createTime;
	private String lastEditTime;
	
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getcreateTime() {
		return createTime;
	}
	public void setcreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public Area() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaName=" + areaName + ", priority=" + priority + ", createTime="
				+ createTime + ", lastEditTime=" + lastEditTime + "]";
	}
	
	

}
