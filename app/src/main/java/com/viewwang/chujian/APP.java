package com.viewwang.chujian;

import android.app.Application;
import android.content.Context;
import android.service.textservice.SpellCheckerService;
import android.util.Log;

import java.io.File;
import java.net.CookieStore;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class APP extends Application {

	private ExecutorService service_task_executor;
	private ExecutorService resource_task_executor;
	
	private static Context mContext;

	private SpellCheckerService.Session session;

	private String filesPath;
	private String bannerPath;
	private String logoPath;
	private String iconPath;

	// 手势密码添加
	private static APP mInstance;

	public static APP getInstance() {
		return mInstance;
	}


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		mInstance = this;

		Log.d(G.tag("Smith"), "Application start.");
		
		mContext = getApplicationContext();

		// 初始化环境变量
		this.filesPath = this.getFilesDir().getAbsolutePath();
		this.bannerPath = this.filesPath + "/banner";
		this.logoPath = this.filesPath + "/logo";
		this.iconPath = this.filesPath + "/icon";

		// 初始化线程池
		this.service_task_executor = (ExecutorService) Executors
				.newCachedThreadPool();
		this.resource_task_executor = (ExecutorService) Executors
				.newFixedThreadPool(G.RESOURCE_THREAD_POOL_SIZE); // 创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。

	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		Log.d(G.tag("Smith"), "Application finished.");
		super.onTerminate();
	}

	protected void clearConfigFile() {
		new File(this.filesPath + "/" + G.BANNER_CONFIG_FILENAME).delete();
	}

	public String getFilesPath() {
		return filesPath;
	}

	public String getBannerPath() {
		return bannerPath;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public String getIconPath() {
		return iconPath;
	}

	public ExecutorService getServiceTaskExecutor() {
		// TODO Auto-generated method stub
		return this.service_task_executor;
	}

	public ExecutorService getResourceTaskExecutor() {
		// TODO Auto-generated method stub
		return this.resource_task_executor;
	}

	// cookie相关管理
	private CookieStore cookies;

	public CookieStore getCookie() {
		return cookies;
	}

	public void setCookie(CookieStore cks) {
		cookies = cks;
	}


	public void logout() {
		Map<String, String> vals = new HashMap<String, String>();
		vals.put("isLogin", "0");
		vals.put("jSessionId", "");
		vals.put("isAutoLogin", "");
		vals.put("name", "");
		vals.put("passWord", "");

	}
	
	public static Context getContext() {
		return mContext;
	}

	public void initImageLoader() {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.

	}

}
