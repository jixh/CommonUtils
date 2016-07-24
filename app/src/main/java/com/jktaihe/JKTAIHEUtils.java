package com.jktaihe;

import android.content.Context;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */
public class JKTAIHEUtils {

    public static Context appContext;

    public static synchronized void  init(Context context){
        if (appContext == null)
        appContext = context;
    }

}
