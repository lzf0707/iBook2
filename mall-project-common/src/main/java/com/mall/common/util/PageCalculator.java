package com.mall.common.util;

public class PageCalculator {

	public PageCalculator() {
		// TODO Auto-generated constructor stub
	}
	
	public static int calculateRowIndex(int pageIndex,int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}

}
