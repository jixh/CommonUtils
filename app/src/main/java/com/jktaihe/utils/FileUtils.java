package com.jktaihe.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */
public class FileUtils {
    /**
     * if no exist make one
     * @param path
     */
    public static void checkExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * @param context
     * @return
     */
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
