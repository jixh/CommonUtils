package com.jc.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by hzjixiaohui on 2015/11/5.
 */
public class ActivityUtils {

    /**
     * 检查当前正在running的Activit 或者 Service
     *
     * <uses-permission android:name="android.permission.GET_TASKS" />
     *
     * @param context
     * @param className
     * @return
     */
    public static boolean isRuningTop(Context context,String className) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        Log.d("", "pkg:" + cn.getPackageName());
        Log.d("", "cls:"+cn.getClassName());
        return className.equals(cn.getClassName());
    }

    /**
     * 检查是否有running的Activit 或者 Service
     * @param context
     * @param action
     * @return
     */
    public static boolean checkRuning(Context context,String action){
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)(context.getSystemService(Context.ACTIVITY_SERVICE));
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
