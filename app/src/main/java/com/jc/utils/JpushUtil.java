package com.jc.utils;

import android.app.Notification;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.hq.shell.R;
import com.hq.shell.activity.MyApplication;
import com.jc.commonutils.R;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class JpushUtil {
    public static final String PREFS_NAME = "JPUSH_EXAMPLE";
    public static final String PREFS_DAYS = "JPUSH_EXAMPLE_DAYS";
    public static final String PREFS_START_TIME = "PREFS_START_TIME";
    public static final String PREFS_END_TIME = "PREFS_END_TIME";
    public static final String KEY_APP_KEY = "JPUSH_APPKEY";

    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }
    
    // 校验Tag Alias 只能是数字,英文字母和中文
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_-]{0,}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    // 取得AppKey
    public static String getAppKey(Context context) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai)
                metaData = ai.metaData;
            if (null != metaData) {
                appKey = metaData.getString(KEY_APP_KEY);
                if ((null == appKey) || appKey.length() != 24) {
                    appKey = null;
                }
            }
        } catch (NameNotFoundException e) {

        }
        return appKey;
    }
    
    // 取得版本号
    public static String GetVersion(Context context) {
		try {
			PackageInfo manager = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
			return manager.versionName;
		} catch (NameNotFoundException e) {
			return "Unknown";
		}
	}

    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
    
	public static String getImei(Context context, String imei) {
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			imei = telephonyManager.getDeviceId();
		} catch (Exception e) {
			Log.e(JpushUtil.class.getSimpleName(), e.getMessage());
		}
		return imei;
	}

//    public void stopPush(){
//        JPushInterface.stopPush(MyApplication.getInstance());
//    }
//
//    public void resumePush(){
//        JPushInterface.resumePush(MyApplication.getInstance());
//    }
private static TagAliasCallback mAliasCallback = new TagAliasCallback() {

    @Override
    public void gotResult(int code, String alias, Set<String> tags) {
        String logs ;
        switch (code) {
            case 0:
                logs = "Set tag and alias success";
                Log.e(TAG, logs);
                break;

            case 6002:
                logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                Log.e(TAG, logs);
                if (isConnected(MyApplication.getInstance())) {
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                } else {
                    Log.i(TAG, "No network");
                }
                break;

            default:
                logs = "Failed with errorCode = " + code;
                Log.e(TAG, logs);
        }
        ToastUtils.s(logs);
    }

};

    private static TagAliasCallback mTagsCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.e(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.e(TAG, logs);
                    if (isConnected(MyApplication.getInstance())){
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }

            ToastUtils.s(logs);
        }

    };


private final static Handler mHandler = new Handler() {
    @Override
    public void handleMessage(android.os.Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case MSG_SET_ALIAS:
                Log.d(TAG, "Set alias in handler.");
                JPushInterface.setAliasAndTags(MyApplication.getInstance(), (String) msg.obj, null, mAliasCallback);
                break;

            case MSG_SET_TAGS:
                Log.d(TAG, "Set tags in handler.");
                JPushInterface.setAliasAndTags(MyApplication.getInstance(), null, (Set<String>) msg.obj, mTagsCallback);
                break;
            default:
                Log.i(TAG, "Unhandled msg - " + msg.what);
        }
    }
};



    private static void setAlias(String alias ){
        if (TextUtils.isEmpty(alias)) {
            ToastUtils.s(R.string.error_alias_empty);
            return;
        }
        if (!isValidTagAndAlias(alias)) {
            ToastUtils.s(R.string.error_tag_gs_empty);
            return;
        }
        //调用JPush API设置Alias
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    public static void setAliasAndTags(){

        if(true)return;

        String alias = MyApplication.userState.user.userName;
        setAlias(alias);

        String tag = MyApplication.userState.user.userName;
        // 检查 tag 的有效性
        if (TextUtils.isEmpty(alias)) {
            ToastUtils.s(R.string.error_tag_empty);
            return;
        }
        // ","隔开的多个 转换成 Set
        String[] sArray = tag.split(",");
        Set<String> tagSet = new LinkedHashSet<String>();
        for (String sTagItme : sArray) {
            if (!isValidTagAndAlias(sTagItme)) {
                ToastUtils.s(R.string.error_tag_gs_empty);
                return;
            }
            tagSet.add(sTagItme);
        }
        //调用JPush API设置Tag
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));
    }

    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;
    private static final String TAG = "JpushUtil";
    /**
     *设置通知提示方式 - 基础属性
     */
    private void setStyleBasic(){
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MyApplication.getInstance());
        builder.statusBarDrawable = R.drawable.ic_launcher;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
        JPushInterface.setPushNotificationBuilder(1, builder);
    }


    /**
     *设置通知栏样式 - 定义通知栏Layout
     */
    private void setStyleCustom(){
        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(MyApplication.getInstance(),R.layout.push_customer_notitfication_layout,R.id.icon, R.id.title, R.id.text);
        builder.layoutIconDrawable = R.drawable.ic_launcher;
        builder.developerArg0 = "developerArg2";
        JPushInterface.setPushNotificationBuilder(2, builder);
    }

}
