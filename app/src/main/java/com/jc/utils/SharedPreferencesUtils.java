package com.jc.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hq.shell.activity.MyApplication;

/**
 * Singleton helper class for lazily initialization.

 */
public  class SharedPreferencesUtils {

    public static SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance().getApplicationContext());;

    public static void put(String key,String value){
        preferences.edit().putString(key,value).commit();
    }

    public static void put(String key,boolean value){
        preferences.edit().putBoolean(key, value).commit();
    }

}
