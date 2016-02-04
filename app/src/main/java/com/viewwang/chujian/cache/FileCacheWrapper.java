package com.viewwang.chujian.cache;

import com.viewwang.chujian.util.NumberUtils;

import java.io.Serializable;


/**
 *<p>文件缓存对象</p>
 */
public class FileCacheWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *<p>缓存对象</p>
	 */
	public Object data;

	/**
	 *<p>过期时间</p>
	 */
	public Long expiredTime;
	
	private static final String DIVIDER = "@!#272";
	
	public String toJson() {
		StringBuilder sb = new StringBuilder();
		sb.append(NumberUtils.valueOf(expiredTime));
		sb.append(DIVIDER);
//		sb.append(GsonUtils.objectToJson(data));
		return sb.toString();
	}
	
	public boolean parseJson(String json, Class<?> objClass) {
		String[] array = json.split(DIVIDER);
		if (array.length == 2) {
			this.expiredTime = NumberUtils.parseLong(array[0]);
//			this.data = GsonUtils.jsonToBean(array[1], objClass);
			return true;
		}
		return false;
	}
}

