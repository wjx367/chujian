package com.viewwang.chujian.util;

public final class StringHelper {

	private StringHelper() {
		
	}
	
	public static final int stringToInt(String num) {
		try {
			return Integer.parseInt(num);
		} catch (Exception e) {
			return 0;
		}
	}

	public static final double stringToDouble(String num) {
		try {
			return Double.parseDouble(num);
		} catch (Exception e) {
			return 0;
		}
	}
}
