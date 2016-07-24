package com.jktaihe.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
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
        return className.equals(getTopActivity(context));
    }

    /**
     * get top activity
     * @param context
     * @return
     */
    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null)
            return runningTaskInfos.get(0).topActivity.getClassName();
        else
            return "";
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
