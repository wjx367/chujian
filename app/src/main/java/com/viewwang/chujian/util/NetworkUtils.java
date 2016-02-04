package com.viewwang.chujian.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {
	/**
	 * 判断Wifi网络是否可用
	 */
	public static boolean isWifiConnected(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWiFiNetworkInfo = mConnectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return mWiFiNetworkInfo != null && mWiFiNetworkInfo.isConnected();
	}

	/**
	 * 判断Mobile网络是否可用
	 */
	public static boolean isMobileConnected(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mMobileNetworkInfo = mConnectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mMobileNetworkInfo != null && mMobileNetworkInfo.isConnected();
	}
	
	/**
	 * 判断当前网络是否可用
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		return mNetworkInfo != null && mNetworkInfo.isAvailable();
	}
	
	/**
	 * 判断当前是否为GPRS网络环境
	 */
	public static boolean isGPRSNetwork(Context context) {
		return isMobileConnected(context) && !isWifiConnected(context);
	}
}
