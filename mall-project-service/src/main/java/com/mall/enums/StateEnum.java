package com.mall.enums;

public enum StateEnum {
	POINT_ERROR(8888,"积分不足"),
	OK(200,"SUCCESS"),
	FAILD(8888,"FAILD"),
	OFFLIME(-1,"非法区域"),
	SUCCESS(0,"操作成功"),
	INNER_ERROR(-1001,"操作失败"),
	EMPTY(-1002,"信息为空"),
	ONLY_ONE_ACCONT(-1003,"最多只能绑定一个本地帐号");
	
	private int state;
	private String stateInfo;
	
	private StateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;	
	}
	
	public String getStateInfo() {
		return stateInfo;
	}

	public static StateEnum stateOf(int index) {
		for (StateEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
	
	
	
	
}
