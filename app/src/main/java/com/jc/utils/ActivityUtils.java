package com.jc.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

/**
 * Created by hzjixiaohui on 2015/11/5.
 */
public class ActivityUtils {
//    <uses-permission android:name="android.permission.GET_TASKS" />
    public boolean isRuningTop(Context context,String className) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        Log.d("", "pkg:" + cn.getPackageName());
        Log.d("", "cls:"+cn.getClassName());
        return className.equals(cn.getClassName());
    }
}
