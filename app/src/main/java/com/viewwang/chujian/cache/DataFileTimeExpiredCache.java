package com.viewwang.chujian.cache;

import com.viewwang.chujian.util.FileUtils;
import com.viewwang.chujian.util.StrUtils;
import com.viewwang.chujian.util.TimeUtil;

/**
 * 保存DataObjectBase类型对象
 */
public abstract class DataFileTimeExpiredCache<T> extends FileTimeExpiredCache<T> {
	private FileExpiredPathHelper helper = null;
	private String filePath = null;
	private Class<?> objClass;
	public DataFileTimeExpiredCache(Class<?> objClass) {
		super(TimeUtil.MILLIS_OF_DAY/2);
		this.objClass = objClass;

	}
	
	@Override
	protected void outputToFile(FileCacheWrapper wrapper) {
		try {
			FileUtils.outputStringToFile(filePath, wrapper.toJson());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected String getCacheKey() {
		return this.getClass().getSimpleName();
	}

	@Override
	protected FileCacheWrapper inputFromFile() {
		if (helper.isExpired()) {
			setExpired(true);
			
			if (isRemoveExpiredFile()) {
				removeCacheFile();
			}
			if (!isReturnExpiredData()) {
				return null;
			}
		}
		else {
			setExpired(false);
		}
		
		String data = FileUtils.getStringFromFile(filePath);
		if (StrUtils.isEmpty(data)) {
			return null;
		}
		
		FileCacheWrapper wrapper = new FileCacheWrapper();
		if (wrapper.parseJson(data, objClass)) {
			return wrapper;
		}
		return null;
	}

	@Override
	protected void removeCacheFile() {
		FileUtils.deleteFile(filePath);
	}
}
