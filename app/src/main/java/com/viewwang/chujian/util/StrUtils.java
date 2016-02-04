package com.viewwang.chujian.util;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {
	
	/**
	 * 判断给定字符串是否空白串。
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		for (int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	public static String getEditTextWithoutSpace(EditText editText) {
		if(editText!=null)
			return editText.getText().toString().trim().replaceAll("\\ ", "");
		else
			return "";
	}
	/**
	 * 不为空返回true，为空返回false。
	 */
	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * 清除字符两端的空格（包括html空格，该空格用string.trim()方法无法去除）
	 */
	public static String trim(String htmlText) {
		if (htmlText == null) {
			return "";
		}
		if (htmlText.indexOf(160) == -1) {
			//不包含html空格
			return htmlText.trim();
		}
		char[] cArray = htmlText.toCharArray();
		int startIndex = 0;
		for (int i=0;i<cArray.length;i++) {
			//160在ascii码表中代表html空格
			if (cArray[i] != 160) {
				startIndex = i;
				break;
			}
		}
		int endIndex = cArray.length-1;
		if (endIndex > startIndex) {
			for (int j=cArray.length-1;j>=0;j--) {
				if (cArray[j] != 160) {
					endIndex = j;
					break;
				}
			}
			if (endIndex > startIndex) {
				char[] newArray = new char[endIndex-startIndex+1];
				int x = 0;
				for (int i=startIndex;i<=endIndex;i++) {
					newArray[x++] = cArray[i];
				}
				return new String(newArray).trim();
			}
		}
		return "";
	}
	
	public static String valueOf(String str) {
		return str != null ? str.trim() : "";
	}
	
	public static boolean isUpperCase(char ch) {
		return ch >= 65 && ch <= 90;
	}
	
	public static boolean isLowerCase(char ch) {
		return ch >= 97 && ch <= 122;
	}
	
	public static boolean isEqual(String str1, String str2) {
		if (str1 != null && str2 != null) {
			return str1.equals(str2);
		}
		return false;
	}
	
	public static boolean notEqual(String str1, String str2) {
		return !isEqual(str1, str2);
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 判断字符串是否是由相同不同的字符所构成
	 * 如果由不同字符组成返回true，否则返回false
	 */
	public static boolean isDifferentCharString(String str) {
		if (str != null && str.length() > 1) {
			char[] charArray = str.toCharArray();
			char firstChar = charArray[0];
			boolean different = false;
			for (char c : charArray) {
				if (firstChar != c) {
					different = true;
					break;
				}
			}
			return different;
		}
		return false;
	}
	
	/**
	 * 链接字符串
	 * @param args 子字符串参数
	 * @return 链接后的字符串
	 */
	public static StringBuilder append(Object... args) {
		StringBuilder sb = new StringBuilder();
		if (args != null && args.length > 0) {
			for (Object obj : args) {
				sb.append(obj);
			}
		}
		return sb;
	}
	
	/**
	 * 将列表对象转换成字符串链接
	 * @param list 需要链接的对象列表
	 * @return 链接后的字符串
	 */
	public static <T extends Object> String append(List<T> list) {
		if (list != null && list.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Object obj : list) {
				sb.append(obj);
			}
			return sb.toString();
		}
		return null;
	}
	
	/**
	 * 将列表对象转换成字符串用指定的分隔符链接
	 * @param divider 分隔符
	 * @param list 需要链接的对象列表
	 * @return 链接后的字符串
	 */
	public static <T extends Object> String appendWithDivider(String divider, List<T> list) {
		if (list != null && list.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Object obj : list) {
				sb.append(obj).append(divider);
			}
			return sb.substring(0, sb.length() - divider.length());
		}
		return "";
	}
	
	/**
	 * 将多个对象用指定的分隔符连接成一个字符串
	 * @param divider 分隔符
	 * @param objects 需要连接的对象
	 * @return 链接后的字符串
	 */
	public static String appendWithDivider(String divider, Object...objects) {
		if (objects != null && objects.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (Object obj : objects) {
				sb.append(obj).append(divider);
			}
			return sb.substring(0, sb.length() - divider.length());
		}
		return "";
	}
	
	/**
	 * 获取字符串的md5
	 */
	public static String getMd5(String str) {
		if (str != null) {
			try {
				byte[] hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
				StringBuilder sb = new StringBuilder(hash.length * 2);
				for (byte b : hash) {
					if ((b & 0xFF) < 0x10) {
						sb.append("0");
					}
					sb.append(Integer.toHexString(b & 0xFF));
				}
				return sb.toString();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 获取字节数组串的md5
	 */
	public static String getMd5(byte[] bytes) {
		if (bytes != null) {
			try {
				MessageDigest digest = MessageDigest.getInstance("MD5");
				digest.update(bytes);
				byte[] hash = digest.digest();
				StringBuilder sb = new StringBuilder(hash.length * 2);
				for (byte b : hash) {
					if ((b & 0xFF) < 0x10) {
						sb.append("0");
					}
					sb.append(Integer.toHexString(b & 0xFF));
				}
				return sb.toString();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 判断是否为手机号码
	 */
	public static boolean isPhoneNumber(String text) {
		if (text == null) {
			return false;
		}
		return text.matches("^1[3|4|5|7|8][0-9]\\d{8}$");
	}
	
	/**
	 * 替换指定前缀和后缀之间的内容
	 * @param content 内容字符串
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @param replacement 需要替换成的内容
	 * @return 替换后的字符串
	 */
	public static String replaceMiddleText(String content, String prefix, String suffix, String replacement) {
		int index = content.indexOf(prefix);
		if (index >= 0) {
			String head = content.substring(0, index);
			String tail = content.substring(index + prefix.length());
			index = tail.indexOf(suffix);
			if (index >= 0) {
				tail = tail.substring(index + suffix.length());
				StringBuilder sb = new StringBuilder();
				sb.append(head);
				sb.append(prefix);
				sb.append(replacement);
				sb.append(suffix);
				sb.append(tail);
				return sb.toString();
			}
		}
		throw new IllegalArgumentException("prefix content【"+prefix+"】 not found in content \n"+content);
	}
	
	/**
	 * 替换指定前缀加后缀以及之间的内容
	 * @param content 内容字符串
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @param replacement 需要替换成的内容
	 * @return 替换后的字符串
	 */
	public static String replaceWholeText(String content, String prefix, String suffix, String replacement) {
		return replaceMiddleText(content, prefix, suffix, "@123").replace(prefix + "@123" + suffix, replacement);
	}
	

	/**
	 * 获取子字符串在父字符串中的个数
	 * @param parent 父字符串
	 * @param child 子字符串
	 */
	public static int getChildTextCount(String parent, String child) {
		Pattern pattern = Pattern.compile(child);
		Matcher matcher = pattern.matcher(parent);
		int count = 0;
		while(matcher.find()) {
			count++;
		}
		return count;
	}
	
	/**
	 * 读取xml文件字符串，去除其中的注释内容
	 * @param xmlFilePath xml文件内容
	 */
	public static String loadPlainXmlContent2(String xmlFilePath) throws Exception {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(xmlFilePath), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line).append("\n");
		}
		br.close();
		String content = sb.toString();
		int anotationStartTagCount = getChildTextCount(content, "<!--"); 
		for (int i=0;i<anotationStartTagCount;i++) {
			content = replaceWholeText(content, "<!--", "-->", "");
		}
		return content;
	}
	
	/**
	 * 读取xml文件字符串，去除其中的注释内容
	 * @param xmlFilePath xml文件内容
	 */
	public static String loadPlainXmlContent(String xmlFilePath) throws Exception {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(xmlFilePath), "UTF-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		boolean anotationStarted = false;
		String anotationStartTag = "<!--";
		String anotationEndTag = "-->";
		//读取Manifest.xml文件字符串，去除其中的注释内容
		while ((line = br.readLine()) != null) {
			int index = line.indexOf(anotationStartTag);
			if (index != -1) {
				sb.append(line.substring(0, index));
				index = line.indexOf(anotationEndTag);
				if (index != -1) {
					anotationStarted = false;
					sb.append(line.substring(index+anotationEndTag.length()));
					continue;
				}
				else {
					anotationStarted = true;
					sb.append("\n");
					continue;
				}
			}
			index = line.indexOf(anotationEndTag);
			if (index != -1) {
				anotationStarted = false;
				sb.append(line.substring(index+anotationEndTag.length()));
				continue;
			}
			if (anotationStarted) {
				sb.append("\n");
				continue;
			}
			sb.append(line).append("\n");
		}
		br.close();
		return sb.toString();
	}
	
	/**
	 * 将二进制数组转换成十六进制字符串
	 * @param bytes 二进制数组
	 * @return 十六进制字符串
	 */
	public static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<bytes.length;i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() <= 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * 将int类型的IP地址转换成通常看到的ip地址
	 */
	public static String intToIp(int i) {
		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + (i >> 24 & 0xFF);
	}
	
	/**
	 * 从url中解析出参数的键值对
	 */
	public static HashMap<String, String> getParametersFromUrl(String url) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			URL u = new URL(url);
			String query = u.getQuery();
			if (StrUtils.notEmpty(query)) {
				StringTokenizer tokenizer = new StringTokenizer(query, "&");
				while(tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();
					String[] tokens = token.split("=");
					if (tokens.length == 2) {
						map.put(tokens[0], tokens[1]);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 从url中解析出参数
	 */
	public static String getParameterFromUrl(String url, String parameterName) {
		return getParametersFromUrl(url).get(parameterName);
	}
	
	/**
	 * 判断是否为移动的手机号码
	 */
	public static boolean isChinaMobileNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.length() != 11) {
			return false;
		}
		//非移动手机号的号段
		HashSet<String> notChinaMobileSet = new HashSet<String>(17);
		notChinaMobileSet.add("130");
		notChinaMobileSet.add("131");
		notChinaMobileSet.add("132");
		notChinaMobileSet.add("145");
		notChinaMobileSet.add("155");
		notChinaMobileSet.add("156");
		notChinaMobileSet.add("185");
		notChinaMobileSet.add("186");
		notChinaMobileSet.add("145");
		notChinaMobileSet.add("133");
		notChinaMobileSet.add("153");
		notChinaMobileSet.add("180");
		notChinaMobileSet.add("181");
		notChinaMobileSet.add("189");
		
		//移动手机号的号段
//		HashSet<String> chinaMobileSet = new HashSet<String>(17);
//		chinaMobileSet.add("134");
//		chinaMobileSet.add("135");
//		chinaMobileSet.add("136");
//		chinaMobileSet.add("137");
//		chinaMobileSet.add("138");
//		chinaMobileSet.add("139");
//		chinaMobileSet.add("147");
//		chinaMobileSet.add("150");
//		chinaMobileSet.add("151");
//		chinaMobileSet.add("152");
//		chinaMobileSet.add("157");
//		chinaMobileSet.add("158");
//		chinaMobileSet.add("159");
//		chinaMobileSet.add("182");
//		chinaMobileSet.add("183");
//		chinaMobileSet.add("184");
//		chinaMobileSet.add("187");
//		chinaMobileSet.add("188");
		//这里通过排除法判断中国移动手机号码
		return !notChinaMobileSet.contains(phoneNumber.substring(0, 3));
	}
	
	/**
	 * 获取字符串中介于prefix和suffix之间的字符串
	 * @param content
	 * @param prefix
	 * @param suffix
	 * @return 没有找到返回null。
	 */
	public static String getMiddleText(String content, String prefix, String suffix) {
		if (content != null && prefix != null && suffix != null) {
			int prefixIndex = content.indexOf(prefix);
			if (prefixIndex >= 0) {
				String tail = content.substring(prefixIndex + prefix.length());
				int suffixIndex = tail.indexOf(suffix);
				if (suffixIndex > 0) {
					return tail.substring(0, suffixIndex);
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取字符串中介于prefix和suffix之间的字符串 然后拼接上prefix和suffix返回
	 * @param content
	 * @param prefix
	 * @param suffix
	 * @return 没有找到返回null。
	 */
	public static String getWholeText(String content, String prefix, String suffix) {
		String middleText = getMiddleText(content, prefix, suffix);
		if (middleText != null) {
			return new StringBuilder().append(prefix).append(middleText).append(suffix).toString();
		}
		return null;
	}
	
	/**
	 * 格式化金额
	 * @param amount 金额
	 * @param decimalDigits 小数点位数
	 * @return
	 */
	public static String formatAmount(Double amount, int decimalDigits) {
		return formatAmount(amount, decimalDigits, false);
	}
	
	/**
	 * 格式化金额
	 * @param amount 金额
	 * @param decimalDigits 小数点位数
	 * @param scientific 是否使用科学计数法，即在用逗号每三位隔开
	 * @return
	 */
	public static String formatAmount(Double amount, int decimalDigits, boolean scientific) {
		if (amount == null) amount = 0d;
		double num=0.49999999;
		for (int i=0;i<decimalDigits;i++){
			num=num/10;
		}
		String result = String.format("%." + decimalDigits + "f", Math.abs(amount - num));
		if (!scientific) {
			return result;
		}
		return formatScientificAmount(result);
	}
	
	/**
	 * 格式化金额
	 * @param amount 金额
	 * @param decimalDigits 小数点位数
	 * @param scientific 是否使用科学计数法，即在用逗号每三位隔开
	 * @param clearZeroTail 是否清除尾部0
	 * @return
	 */
	public static String formatAmount(Double amount, int decimalDigits, boolean scientific, boolean clearZeroTail) {
		String amountText = formatAmount(amount, decimalDigits, scientific);
		if (clearZeroTail) {
			int index = amountText.indexOf(".");
			if (index > 0) {
				StringBuilder sb = new StringBuilder(amountText);
				char chr;
				while ((chr = sb.charAt(sb.length()-1)) == 48 || chr == 46) {
					sb.deleteCharAt(sb.length()-1);
					if (chr == 46) break;//去除小数点后结束
				}
				return sb.toString();
			}
		}
		return amountText;
	}
	
	private static String formatScientificAmount(String amount) {
		String[] arr = amount.split("\\.");
		String prefix = arr[0];
		int count = prefix.length();
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<count;i++) {
			sb.append(prefix.charAt(i));
			int c = count - 1 - i;
			if (c % 3 == 0 && c != 0) {
				sb.append(",");
			}
		}
		if (arr.length > 1) {
			for (int i=1;i<arr.length;i++) {
				sb.append(".").append(arr[i]);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 格式化金额
	 * @param amount 金额
	 * @param decimalDigits 是否使用科学计数法，即在用逗号每三位隔开
	 * @return
	 */
	public static String formatAmount(Float amount, int decimalDigits) {
		return formatAmount(amount, decimalDigits, false);
	}
	
	/**
	 * 格式化金额
	 * @param amount 金额
	 * @param decimalDigits 小数点位数
	 * @param scientific 是否使用科学计数法，即在用逗号每三位隔开
	 * @return
	 */
	public static String formatAmount(Float amount, int decimalDigits, boolean scientific) {
		if (amount == null) amount = 0f;
		String result = String.format("%."+decimalDigits+"f", amount);
		if (!scientific) {
			return result;
		}
		return formatScientificAmount(result);
	}
	
	/**
	 * 格式化金额
	 * @param amount 金额
	 * @param decimalDigits 小数点位数
	 * @param scientific 是否使用科学计数法，即在用逗号每三位隔开
	 * @param clearZeroTail 是否清除尾部0
	 * @return
	 */
	public static String formatAmount(Float amount, int decimalDigits, boolean scientific, boolean clearZeroTail) {
		String amountText = formatAmount(amount, decimalDigits, scientific);
		if (clearZeroTail) {
			int index = amountText.indexOf(".");
			if (index > 0) {
				StringBuilder sb = new StringBuilder(amountText);
				char chr;
				while ((chr = sb.charAt(sb.length()-1)) == 48 || chr == 46) {
					sb.deleteCharAt(sb.length()-1);
					if (chr == 46) break;//去除小数点后结束
				}
				return sb.toString();
			}
		}
		return amountText;
	}

	public static String formatAmountClearZero(Double amount){
		return formatAmount(amount, 2, false, true);
	}
	
	public static String formatFileSize(Long size, int decimalDigits) {
		if (size != null) {
			if (size < 1024) {
				return size+"B";
			}
			
			double dsize = size/1024.0;
			if (dsize < 1024) {
				return formatAmount(dsize, decimalDigits)+"K";
			}
			
			dsize = dsize/1024.0;
			if (dsize < 1024) {
				return formatAmount(dsize, decimalDigits)+"M";
			}
			
			dsize = dsize/1024.0;
			return formatAmount(dsize, decimalDigits)+"G";
		}
		return "未知";
	}
	
	public static String getUrlHost(String url) {
		if (url != null) {
			try {
				URL u = new URL(url);
				return u.getHost();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String getUrlReferer(String url) {
		if (url != null) {
			try {
				URL u = new URL(url);
				return u.getProtocol() + "://" + u.getHost();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获取去除参数后的连接地址
	 * @param url 包含参数的连接地址
	 * @return 失败返回null。
	 */
	public static String getPlainUrl(String url) {
		try {
			URL u = new URL(url);
			String query = u.getQuery();
			if (StrUtils.notEmpty(query)) {
				int index = url.indexOf(query);
				if (index > 0) {
					return url.substring(0, index).replace("?", "");
				}
			}
			else  {
				return url;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 隐藏手机号码中间4位
	 */
	public static String hidePhoneNumber(String phoneNumber) {
		if (phoneNumber != null && phoneNumber.length() == 11) {
			return new StringBuilder().append(phoneNumber.substring(0, 3)).append("****")
					.append(phoneNumber.substring(phoneNumber.length() - 4)).toString();
		}
		return phoneNumber;
	}

	/**
	 * 隐藏银行卡号，只显示前6位和后4位
	 */
	public static String hideBankCardNumberWithStar(String bankCardNo) {
		if (bankCardNo != null && bankCardNo.length() > 10) {
			StringBuilder sb = new StringBuilder();
			sb.append(bankCardNo.substring(0, 6));
			int starCount = bankCardNo.length() - 6 - 4;
			for (int i = 0; i < starCount; i++) {
				sb.append("*");
			}
			sb.append(bankCardNo.substring(bankCardNo.length() - 4));
			return sb.toString();
		}
		return bankCardNo;
	}
	
	/**
	 * 判断是否是数字字符串
	 * @param text 字符串
	 * @param justNumbers 是否要求纯数字（没有小数点）
	 * @return 匹配返回true，否则返回false。
	 */
	public static boolean isNumbers(String text, boolean justNumbers) {
		if (text != null) {
			if (justNumbers) {
				return text.matches("[\\d]+");
			}
			return text.matches("^\\d$|^\\d+[.]?\\d+$");
		}
		return false;
	}
	
	/**
	 * 判断是否是纯字母
	 * @param text
	 * @return
	 */
	public static boolean isLetter(String text) {
		if (text != null) {
			return text.matches("[[a-zA-Z]]+");
		}
		return false;
	}
	/**
	 * 判断是否是纯数字字符串
	 * @param text 字符串
	 * @return 匹配返回true，否则返回false。
	 */
	public static boolean isNumbers(String text) {
		return isNumbers(text, true);
	}
	public static boolean isMailAddress(String text){
		return text.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
//		return text.matches("^\\w+(\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
	}
	/**
	 * 对字符串中的各个字符按照一定的方式进行调换位置组成新的字符串
	 * @param str 原始字符串
	 * @param key 调换方式
	 * @return
	 */
	public static String shiftEncode(String str, int[] key) {
		char[] chr = str.toCharArray();
		for (int i=0;i<chr.length;i++) {
			swap(chr, i, key[i%key.length]);
		}
		return String.valueOf(chr);
	}
	/**
	 * 对已经通过一定方式调换位置的字符进行还原，还原成原始字符串
	 * @param str 调换后的字符串
	 * @param key 调换方式
	 * @return
	 */
	public static String shiftDecode(String str, int[] key) {
		char[] chr = str.toCharArray();
		for (int i=chr.length-1;i>=0;i--) {
			swap(chr, i, key[i%key.length]);
		}
		return String.valueOf(chr);
	}
	/**
	 * 交换字符数组中两个元素的位置
	 * @param chr
	 * @param x
	 * @param y
	 */
	public static void swap(char[] chr, int x, int y) {
		if (x < chr.length && y < chr.length && x != y) {
			char e = chr[x];
			chr[x] = chr[y];
			chr[y] = e;
		}
	}
	
	/**
	 * 获取一个字符串中第一次出现的数字连续字符串
	 */
	public static String getFirstNumberFromText(String text) {
		if (StrUtils.notEmpty(text)) {
			Matcher numberMatcher = Pattern.compile("\\d+").matcher(text);
			if (numberMatcher.find()) {
				return numberMatcher.group();
			}
		}
		return "";
	}
	
	/**
	 * 从字符串中获取所有出现的连续数字的列表
	 */
	public static List<String> getNumberListFromText(String text) {
		if (StrUtils.notEmpty(text)) {
			Matcher numberMatcher = Pattern.compile("\\d+").matcher(text);
			List<String> list = new ArrayList<String>();
			while (numberMatcher.find()) {
				list.add(numberMatcher.group());
			}
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static void printList(List list) {
		if (list != null) {
			for (Object obj : list) {
				System.out.println(obj);
			}
		}
	}
	public static void printArray(Object[] arr) {
		if (arr != null) {
			for (Object obj : arr) {
				System.out.println(obj);
			}
		}
	}
	/**
	 * 获取指定位数的随机数字字符串
	 * @param count 字符串位数
	 * @return
	 */
	public static String getRandomCode(int count) {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<count;i++) {
			sb.append(NumberUtils.random(0, 9));
		}
		return sb.toString();
	}

	/**
	 * 限定editText只能输入小数个数
	 * @param count 限定小数位数个数
	 * @param editText EditText对象
	 */
	public static void setEditTextNumber(final int count,EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				setEditTextStyle(count,s);
			}
		});
	}

	/**
	 * 限定editText只能输入小数个数
	 * @param count 限定小数位数个数
	 * @param editable edittable对象
	 */
	public static void setEditTextStyle(int count,Editable editable) {
		String content = editable.toString();
		if (content.indexOf(".")>0) {
			if(content.substring(content.indexOf("."), content.length()).length()>count+1){
				editable.delete(content.indexOf(".")+count+1, content.indexOf(".")+count+2);
			}
		}
	}

	/**
	 * 第一个字母大写
	 * @param str 字符串
	 * @return 第一个字母大写其他字母小写的字符串
	 */
	@SuppressLint("DefaultLocale")
	public static String makeFirstLetterUpperCase(String str) {
		if (str != null && str.length() > 0) {
			return (str.charAt(0)+"").toUpperCase()+str.substring(1);
		}
		return str;
	}
	
	/**
	 * 第一个字母小写
	 * @param str 字符串
	 * @return 第一个字母小写其他字母不变的字符串
	 */
	@SuppressLint("DefaultLocale")
	public static String makeFirstLetterLowerCase(String str) {
		if (str != null && str.length() > 0) {
			return (str.charAt(0)+"").toLowerCase()+str.substring(1);
		}
		return str;
	}

	public static void main(String[] args) {
//		System.out.println(formatAmount(100000.00141000, 8, false, true));
//		System.out.println(formatAmount(100000d, 8, false, true));
//		System.out.println(formatAmount(100000.001, 8, false, true));
	}
}
