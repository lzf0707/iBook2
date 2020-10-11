package com.mall.dto;

import java.util.List;

import com.mall.enums.StateEnum;

public class ResultExecution<E> {

	private int state;
	private String stateInfo;
	private Integer count;
	private E e;
	private List<E> list;
	private int totalPoint;
	
	//构造器
	public ResultExecution(StateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	//成功的构造器
	public ResultExecution(StateEnum stateEnum,E e) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.e = e;
	}
	
	//成功的构造器
	public ResultExecution(StateEnum stateEnum,List<E> list) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.list = list;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public E getE() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public ResultExecution() {
		// TODO Auto-generated constructor stub
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}

}
