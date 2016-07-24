package com.jktaihe.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */

public  class SharedPreferencesUtils {

    public static final String PREFERENCE_NAME = "jktaihesaveInfo";

    private static SharedPreferences preferences = null;
    private static SharedPreferences.Editor editor = null;
    private static SharedPreferencesUtils instance = null;

    private SharedPreferencesUtils(Context cxt) {
        preferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static synchronized void init(Context context){
        if(instance == null){
            instance = new SharedPreferencesUtils(context);
        }
    }

    /**
     * get instance
     */
    public synchronized static SharedPreferencesUtils getInstance() {
        if (preferences == null) {
            throw new RuntimeException("please init first!");
        }
        return instance;
    }

    public static void put(String key,String value){
        editor.putString(key,value).commit();
    }

    public static String getS(String key){
        return preferences.getString(key,"");
    }

    public static String getS(String key,String defaultS){
        return preferences.getString(key,defaultS);
    }

}
