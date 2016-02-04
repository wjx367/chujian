package com.viewwang.chujian.cache;

import com.viewwang.chujian.util.NumberUtils;
import com.viewwang.chujian.util.TimeUtil;


public abstract class FileTimeExpiredCache<T> {
	private long cacheMillis = TimeUtil.MILLIS_OF_HOUR;
	
	/**
	 * 构造一个默认指定过期的时间毫秒数的缓存对象
	 */
	public FileTimeExpiredCache(long defaultCacheMillis) {
		this.cacheMillis = defaultCacheMillis;
	}
	/**
	 * 构造一个默认5分钟过期的时间缓存对象
	 */
	public FileTimeExpiredCache(){}
	
	/**
	 * 获取缓存毫秒数
	 */
	protected long getCacheMillis() {
		return this.cacheMillis;
	}
	
	/**
	 * 判断缓存的数据对象是否已经过期
	 * @return 过期返回true，否则返回false。
	 */
	public boolean isExpired(FileCacheWrapper wrapper) {
		if (wrapper != null) {
			long expiredTime = NumberUtils.valueOf(wrapper.expiredTime);
			if (expiredTime > 0) {
				long currentTime = System.currentTimeMillis();
				return currentTime > expiredTime;
			}
		}
		return true;
	}
	
	/**
	 * 设置缓存的数据对象，并设置缓存的毫秒数
	 * @param data 需要数据对象
	 * @param cacheMillis 缓存毫秒数
	 */
	public void setData(T data, long cacheMillis) {
		try {
			this.cacheMillis = cacheMillis;
			FileCacheWrapper wrapper = new FileCacheWrapper();
			wrapper.expiredTime = System.currentTimeMillis()+cacheMillis;
			wrapper.data = data;
			outputToFile(wrapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置缓存的数据对象，缓存时间为默认
	 * @param data 需要数据对象
	 */
	public void setData(T data) {
		setData(data, cacheMillis);
	}
	
	/**
	 * 获取缓存的对象
	 * @return 如果对象已过期则返回null。
	 */
	@SuppressWarnings("unchecked")
	public T getData() {
		try {
			FileCacheWrapper wrapper = inputFromFile();
			if (wrapper == null) {
				return null;
			}
			if (isExpired(wrapper)) {
				setExpired(true);
				
				if (isRemoveExpiredFile()) {
					removeCacheFile();
				}
				if (!isReturnExpiredData()) {
					return null;
				}
			}
			
			//setExpired(false);
			return (T) wrapper.data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public FileCacheWrapper getWrapper() {
		try {
			return inputFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean removeExpiredFile = true;
	private boolean returnExpiredData = true;
	private boolean expired = true;
	
	/**
	 * 设置是否删除过期文件，默认删除
	 */
	public void setRemoveExpiredFile(boolean value) {
		removeExpiredFile = value;
	}
	/**
	 * 是否删除过期文件，默认删除
	 */
	public boolean isRemoveExpiredFile() {
		return this.removeExpiredFile;
	}
	/**
	 * 设置是否返回过期内容，默认返回
	 */
	public void setReturnExpiredData(boolean value) {
		returnExpiredData = value;
	}
	/**
	 * 是否返回过期内容，默认返回
	 */
	public boolean isReturnExpiredData() {
		return this.returnExpiredData;
	}
	
	protected void setExpired(boolean value) {
		this.expired = value;
	}
	public boolean isExpired() {
		return this.expired;
	}
	/**
	 * 清除缓存
	 */
	public void clear() {
		removeCacheFile();
	}
	
	/**
	 * 保存到文件
	 * @param wrapper
	 */
	protected abstract void outputToFile(FileCacheWrapper wrapper);
	/**
	 * 从文件加载数据
	 * @return
	 */
	protected abstract FileCacheWrapper inputFromFile();
	/**
	 * 删除文件
	 */
	protected abstract void removeCacheFile();
}

