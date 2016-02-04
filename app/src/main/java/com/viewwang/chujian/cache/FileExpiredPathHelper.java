package com.viewwang.chujian.cache;

import com.viewwang.chujian.util.NumberUtils;

import java.io.File;


/**
 * 对缓存的路径进行加工，利用文件名称实现缓存时间过期的判断
 */
public class FileExpiredPathHelper {
	private String folderPath;
	private String cacheKey;
	private long cacheMillis;
	
	public FileExpiredPathHelper(File folder, String cacheKey, long cacheMillis) {
		this(folder.getAbsolutePath(), cacheKey, cacheMillis);
	}
	public FileExpiredPathHelper(String folderPath, String cacheKey, long cacheMillis) {
		this.folderPath = folderPath;
		this.cacheKey = cacheKey;
		this.cacheMillis = cacheMillis;
	}
	
	public boolean isExpired() {
		String filePath = processFilePath();
		File file = new File(filePath);
		if (!file.exists()) {
			return true;
		}
		String fileName = file.getName();
		String[] params = fileName.split("_");
		if (params.length < 2) {
			file.delete();
			return true;
		}
		long cacheMillis = NumberUtils.parseLong(params[params.length - 1]);

		return Math.abs(System.currentTimeMillis() - file.lastModified()) > cacheMillis;
	}
	
	public String processFilePath() {
		StringBuilder sb = new StringBuilder();
		sb.append(folderPath);
		if (!folderPath.endsWith("/")) {
			sb.append("/");
		}
		sb.append(processFileName());
		return sb.toString();
	}
	
	public String processFileName() {
		StringBuilder sb = new StringBuilder();
		sb.append(cacheKey);
		sb.append("_");
		sb.append(cacheMillis);
		return sb.toString();
	}
}
