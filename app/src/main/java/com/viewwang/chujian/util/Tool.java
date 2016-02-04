package com.viewwang.chujian.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool {
	final static String digits = "0123456789ABCDEF";

	public static String urldoencode(String s) {
		// Guess a bit bigger for encoded form
		StringBuilder buf = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
					|| (ch >= '0' && ch <= '9') || ".-*_".indexOf(ch) > -1) { //$NON-NLS-1$
				buf.append(ch);
			} else {
				byte[] bytes = new String(new char[] { ch }).getBytes();
				for (int j = 0; j < bytes.length; j++) {
					buf.append('%');
					buf.append(digits.charAt((bytes[j] & 0xf0) >> 4));
					buf.append(digits.charAt(bytes[j] & 0xf));
				}
			}
		}
		return buf.toString();
	}

	// unicode转中文
	public static String decode(String unicodeStr) {
		if (unicodeStr == null) {
			return null;
		}
		StringBuffer retBuf = new StringBuffer();
		int maxLoop = unicodeStr.length();
		for (int i = 0; i < maxLoop; i++) {
			if (unicodeStr.charAt(i) == '\\') {
				if ((i < maxLoop - 5)
						&& ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr
								.charAt(i + 1) == 'U')))
					try {
						retBuf.append((char) Integer.parseInt(
								unicodeStr.substring(i + 2, i + 6), 16));
						i += 5;
					} catch (NumberFormatException localNumberFormatException) {
						retBuf.append(unicodeStr.charAt(i));
					}
				else
					retBuf.append(unicodeStr.charAt(i));
			} else {
				retBuf.append(unicodeStr.charAt(i));
			}
		}
		return retBuf.toString();
	}

	/**
	 * utf-8 转unicode
	 * 
	 * @param str
	 * @return String
	 */
	public static String toUnicode(String str) {
		char[] arChar = str.toCharArray();
		int iValue = 0;
		String uStr = "";
		for (int i = 0; i < arChar.length; i++) {
			iValue = (int) str.charAt(i);
			char sValue = str.charAt(i);
			if (iValue <= 256) {
				// uStr+="& "+Integer.toHexString(iValue)+";";
				// uStr+="\\"+Integer.toHexString(iValue);
				uStr += sValue;
			} else {
				// uStr+="&#x"+Integer.toHexString(iValue)+";";
				uStr += "\\u" + Integer.toHexString(iValue);
			}
		}
		return uStr;
	}

	// 中文转URL编码
	public final static String urlencode(String url) {
		Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(url);
		while (matcher.find()) {
			String tmp = matcher.group();
			try {
				url = url.replaceAll(tmp, URLEncoder.encode(tmp, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return url;
	}

	// 转32位大写MD5
	public final static String get32MD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			Log.e("Tool", "NoSuchAlgorithmException:" + e.toString());
		} catch (UnsupportedEncodingException e) {
			Log.e("Tool", "UnsupportedEncodingException:" + e.toString());
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	// 判断是否是手机号码
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
		
	}

	// 判断是否是邮箱地址
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	// 判断是否是html
	public static boolean isHTML(String html) {
		String str = "^<([^>\\s]+)[^>]*>(.*?<\\/\\\\1>)?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	// 判断网络是否连接
	public static boolean isNetworkAvailable(Activity mActivity) {
		Context context = mActivity.getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// 微信分享使用
	public static byte[] bmpToByteArray(final Bitmap bmp,
			final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String toDeciDouble(double num) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		BigDecimal bd = new BigDecimal(num);
		// num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		num = bd.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		return nf.format(num);
		// return Double.toString((Double)((long)(num*100)/100.0));
	}

	public static String toFFDouble(double num) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		BigDecimal bd = new BigDecimal(num);
		// num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		num = bd.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		return nf.format(num);
	}

	public static List<String> toList(JSONArray arr) {
		List<String> str_list = new ArrayList<String>();
		for (int i = 0; i < arr.length(); i++) {
			try {
				str_list.add(arr.getString(i));
			} catch (JSONException e) {
				Log.e("Tool", "Failed To List");
				return str_list;
			}
		}
		return str_list;
	}

	// url 解析
	public static String getUrl(String url) {
		String ret_url = "";
		if (url.contains("?")) {
			ret_url = url + "&";
		} else {
			ret_url = url + "?";
		}
		return ret_url + "clientType=android&";
	}

	@SuppressLint("SimpleDateFormat")
	public static String getNowTime() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("MM-dd   HH:mm:ss");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}

	public static String encodePassword(String password) {
		String str_begin = password.substring(0, 3);
		String str_end = password.substring(3);
		String new_password = str_end + str_begin;
		return new_password;
	}

	public static String decodePassword(String password) {
		if (password == null || "".equals(password))
			return "";
		int len = password.length();
		if (len < 6)
			return "";
		String str_begin = password.substring(0, len - 3);
		String str_end = password.substring(len - 3);
		String new_password = str_end + str_begin;
		return new_password;
	}

	// 号码中间数字隐藏处理 手机号，身份证号(15位 18位）
	public static String disnumber(String str) {
		String targetstr = "";
		if (str == null || str == "")
			return "";
		int len = str.length();
		switch (len) {
		case 11: // 手机号
			targetstr = str.substring(0, 3) + "****"
					+ str.substring(len - 4, len);
			break;
		case 18:
			targetstr = str.substring(0, 6) + "*********"
					+ str.substring(len - 3, len);
			break;
		case 15:
			targetstr = str.substring(0, 6) + "******"
					+ str.substring(len - 3, len);
			break;
		default: // 银行卡号
			if (len > 10) {
				StringBuffer sb = new StringBuffer();
				sb.append(str.substring(0, 3));
				for (int i = 0; i < len - 8; i++) {
					sb.append("*");
				}
				sb.append(str.substring(len - 3, len));
				targetstr = sb.toString();
			}
			break;
		}
		return targetstr;
	}
	
	public static String disnumber(String str, int start, int end) {
		// 13***45     2  4
		String targetstr = "";
		if (str == null || str == "")
			return "";
		int len = str.length();
		if (len<=2) {
			return str.substring(0,1)+"*";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(str.substring(0, start));
		for (int i = 0; i < str.length() - end; i++) {
			sb.append("*");
		}
		sb.append(str.substring(end+1, str.length()));
		targetstr = sb.toString();
		return targetstr;
	}

	// 像素之间的转换
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	
	//判断是否与当前时间 在同一天
	public static boolean isSameDay(String str) {
		if ("".equals(str)||str==null) {
			return false;
		}
		long timeMillis = System.currentTimeMillis();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String current_time = sf.format(timeMillis);
		return str.equals(current_time);

	}
	
	//判断密码格式是否正确
	public static String checkPwd(String str){
		String regEx1 = "^\\w{6,15}$";// 6-15位密码,不能包含空格和中文
		String regEx2 = "^\\d+$";// 密码为纯数字
		String regEx3 = "^[a-zA-Z]+$";// 密码为纯字母
		if (!str.matches(regEx1)) {
			return "请输入6-15位密码，不能包含空格和中文";
		}
		else if (str.matches(regEx2)) {
			return "密码不能为纯数字";
		}
		else if (str.matches(regEx3)) {
			return "密码不能为纯字母";
		}else {
			return "success";
		}
		
	}
}
