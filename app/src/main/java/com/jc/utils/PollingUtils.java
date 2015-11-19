package com.jc.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.hq.shell.activity.MyApplication;
/**
 * Polling Tools
 * @Author Ryan
 * @Create 2013-7-13 上午10:14:43
 */
public class PollingUtils {
	//启动运行一次的服务
	public static void startOnceService(Context context,Class<?> cls,int delay_seconds,String action) {
		if(!IsRunning.check(action)){
			AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			Intent intent = new Intent(context, cls);
			intent.setAction(action);
			PendingIntent pendingIntent = PendingIntent.getService(context, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
			int triggerAtTime = (int) (SystemClock.elapsedRealtime() + delay_seconds * 1000); 
			manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
		}
	}

	/**
	 * @param context
	 * @param seconds
	 * @param cls
	 * @param action
	 */ 
	public static void startPollingService(Context context, int seconds, Class<?> cls,String action) {
		if(!IsRunning.check(action)){
			AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			Intent intent = new Intent(context, cls);
			intent.setAction(action);
			PendingIntent pendingIntent = PendingIntent.getService(context, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
			long triggerAtTime = SystemClock.elapsedRealtime();
//			ELAPSED_REALTIME_WAKEUP
			manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime,seconds * 1000, pendingIntent);
		}
	}

	/**
	 * 
	 * @param context
	 * @param cls
	 * @param action
	 */
	public static void stopPollingService(Context context, Class<?> cls,String action,Intent _intent) {
		AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
		manager.cancel(pendingIntent);
		
		MyApplication.getInstance().getApplicationContext().stopService(_intent);
	}
}
