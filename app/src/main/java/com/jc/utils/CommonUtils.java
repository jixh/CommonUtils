package com.jc.utils;

import android.content.Context;

/**
 * Created by jc on 11/21/2015.
 */
public class CommonUtils {

    public static Context context = null;

    public static void init(Context context) {
        CommonUtils.context = context;
    }
}
