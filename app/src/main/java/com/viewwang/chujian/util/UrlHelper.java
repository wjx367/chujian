package com.viewwang.chujian.util;

public final class UrlHelper {

	private UrlHelper() {
		
	}
	
	public static String concatParam(String url, String paramStr) {
		if(paramStr == null || "".equals(paramStr)) {
			return url;
		}
		return url + (url.indexOf("?") != -1 ? "&" : "?") + paramStr;
	}
}
