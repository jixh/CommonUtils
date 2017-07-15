package com.jktaihe.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences本地记录行为是否第一二次
 */
public class Once {

    SharedPreferences mSharedPreferences;
    Context mContext;


    public Once(Context context) {
        mSharedPreferences = context.getSharedPreferences("once", Context.MODE_PRIVATE);
        mContext = context;
    }


    public void show(String tagKey, OnceCallback callback) {
        show(tagKey,callback,null);
    }

    public void show(int tagKeyResId, OnceCallback callback) {
        show(mContext.getString(tagKeyResId), callback);
    }

    public void show(String tagKey, OnceCallback callback, NotOnceCallback notOnceCallback) {
        boolean isSecondTime = mSharedPreferences.getBoolean(tagKey, false);
        if (!isSecondTime) {
            callback.onOnce();
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(tagKey, true);
            editor.apply();
        } else if (notOnceCallback != null){
            notOnceCallback.notOnOnce();
        }
    }

    public interface OnceCallback {
        void onOnce();
    }

    public interface NotOnceCallback {
        void notOnOnce();
    }
}
