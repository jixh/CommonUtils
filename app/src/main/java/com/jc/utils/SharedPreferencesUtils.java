package com.jc.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * SharedPreferencesUtils helper user for store sample data.
 */
public  class SharedPreferencesUtils {

    private static SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CommonUtils.context);

    public static void put(String key,String value){
        preferences.edit().putString(key,value).commit();
    }
    public static String getS(String key){
        return preferences.getString(key,"");
    }

    public static String getS(String key,String defaultS){
        return preferences.getString(key,defaultS);
    }

    public static void put(String key,boolean value){
        preferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getB(String key){
      return preferences.getBoolean(key, false);
    }

    public static void getB(String key,boolean value){
        preferences.getBoolean(key, value);
    }

}
