package com.mall.common.util;

import java.util.ArrayList;
import java.util.Random;

public class TradeIdGenerate {
	private static final Random r = new Random();
	
	public static String getTradeId(int length){
		//五位随机数字
		int rannum = r.nextInt(89999) + 10000;
		ArrayList<Character> list = new ArrayList<Character>();
		for (char c = 'a'; c <= 'z'; c++) {
		    list.add(c);
		}
		String str = "";
	    for (int i = 0; i < length; i++) {
	        int num = (int) (Math.random() * 26);
	        str = str + list.get(num);
	    }
	    return str + rannum;
	}
	
	public static void main(String[] args) {
		System.out.println(TradeIdGenerate.getTradeId(8));
	}
}
