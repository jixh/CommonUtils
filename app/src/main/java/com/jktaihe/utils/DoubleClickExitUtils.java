package com.jktaihe.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */

public class DoubleClickExitUtils {
    private final Activity mActivity;
    private boolean isOnKeyBacking;
    private Handler mHandler;
    private Toast mBackToast;

    public DoubleClickExitUtils(Activity activity) {
        mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Activity onKeyDown事件
     * */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }
        if(isOnKeyBacking) {
            mHandler.removeCallbacks(onBackTimeRunnable);
            if(mBackToast != null){
                mBackToast.cancel();
            }

            mActivity.finish();//应该调用退出方法

            return true;
        } else {
            isOnKeyBacking = true;
            if(mBackToast == null) {
                mBackToast = Toast.makeText(mActivity, "再次点击退出", Toast.LENGTH_SHORT);
            }
            mBackToast.show();
            mHandler.postDelayed(onBackTimeRunnable, 2000);
            return true;
        }
    }

    private Runnable onBackTimeRunnable = new Runnable() {

        @Override
        public void run() {
            isOnKeyBacking = false;
            if(mBackToast != null){
                mBackToast.cancel();
            }
        }
    };
}
