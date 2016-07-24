package com.jktaihe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */

public  class SharedPreferencesUtils {

    private static SharedPreferences preferences = null;

    public void init(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void put(String key,String value){
        preferences.edit().putString(key,value).commit();
    }

    public static String getS(String key){
        return preferences.getString(key,"");
    }
    public static String getS(String key,String defaultS){
        return preferences.getString(key,defaultS);
    }

}
