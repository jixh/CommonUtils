package com.jc.utils;

import android.content.Context;
import android.os.Environment;

/**
 * Created by hzjixiaohui on 2015/10/21.
 */
public class FileUtils {

    public static String getAvailablePath(Context context){
        String path = "";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //执行存储sdcard方法 sdcard/sdcard0
            path  = Environment.getExternalStorageDirectory().getAbsolutePath() ;
        }
        else{
            //存储到手机中，或提示
            path =  context.getCacheDir().getAbsolutePath();
        }
        return path;
    }
}
