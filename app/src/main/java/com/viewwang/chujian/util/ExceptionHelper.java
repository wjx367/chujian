package com.viewwang.chujian.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionHelper {

	private ExceptionHelper() {
	}

	public static String stackTraceToString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
