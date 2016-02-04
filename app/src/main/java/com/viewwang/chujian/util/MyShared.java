package com.viewwang.chujian.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MyShared {
	private SharedPreferences sp;
	private Editor spEd;
	
	public MyShared(Context context, String pref)
	{
		this.sp = context.getSharedPreferences(pref, 0);
		spEd = this.sp.edit();
	}
	
	public boolean isLogin()
	{
		return sp.getBoolean("isAutoLogin", false);
	}
	
	public boolean isAutoLogin()
	{
		return sp.getBoolean("isAutoLogin", false);
	}
	
	public String getSessionId()
	{
		return sp.getString("jSessionId", "");
	}
	
	public String getString(String key)
	{
		return sp.getString(key, "");
	}
	
	public int getInt(String key)
	{
		return sp.getInt(key, 0);
	}
	
	public boolean getBoolean(String key)
	{
		return sp.getBoolean(key, false);
	}
	
	public void putString(String key, String val)
	{
		spEd.putString(key, val);
	}
	
	public void putBoolean(String key, Boolean bool)
	{
		spEd.putBoolean(key, bool);
	}
	
	public void commit()
	{
		spEd.commit();
	}
}
