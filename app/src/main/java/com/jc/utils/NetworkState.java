package com.jc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

public class NetworkState {
	
//	private static ConnectivityManager connectivityManager; //網絡連接管理
	private static State mobile; //移動網絡狀態
	private static State wifi; //WIFI網絡狀態
	
	/**
	 * 初始化
	 * */
	public static ConnectivityManager init(Context context) {
		return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}
	
	/**
	 * 當前網絡是否已連接
	 * @return true 網絡已連接<br />false 其它
	 * */
	public static boolean isConnected(ConnectivityManager connectivityManager) {
		boolean result = false;
		mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		result = (mobile == State.CONNECTED || wifi == State.CONNECTED);
		return result;
	}
	
	/**
	 * 當前網絡是否正在連接
	 * @return true 網絡正在連接<br />false 其它
	 * */
	public boolean isConnecting(ConnectivityManager connectivityManager) {
		boolean result = false;
		mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		result = (mobile == State.CONNECTING || wifi == State.CONNECTING);
		return result;
	}

}