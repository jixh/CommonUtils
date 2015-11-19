package com.jc.utils;

import java.util.List;

import android.app.ActivityManager;
import android.content.Context;

import com.hq.shell.activity.MyApplication;

public class IsRunning {
	public static boolean check(String action){
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager)((MyApplication.getInstance().getApplicationContext()).getSystemService(Context.ACTIVITY_SERVICE)); 
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(1000);
		if (!(serviceList.size()>0)) {
			isRunning = false;
		}
		for (int i=0; i<serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(action)) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}
}
