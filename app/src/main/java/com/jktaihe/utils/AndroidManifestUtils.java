package com.jktaihe.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by jktaihe on 15/7/17.
 * blog: blog.jktaihe.com
 */

public class AndroidManifestUtils {
    /**
     * 打印AndroidManifest 中<meta>參數的value
     * @param context
     * @param name eg:JPUSH_APPKEY
     */
    public static String getMetaData(Context context, String name){
        String value = "";
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }finally {
            return value;
        }
    }
}
