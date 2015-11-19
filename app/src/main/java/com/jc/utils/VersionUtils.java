package com.jc.utils;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.hq.shell.R;
import com.hq.shell.activity.MyApplication;
import com.hq.shell.bean.ShellRequestBean;
import com.hq.shell.bean.ShellResultCallback;
import com.hq.shell.util.okhttp.OkHttpClientManager;
import com.jc.commonutils.R;
import com.notification.progress.NotificationDownloadService;
import com.squareup.okhttp.Request;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by hzjixiaohui on 2015/10/21.
 */
public class VersionUtils {

    private static final String  TAG = "VersionUtils" ;
    private Context context;
    private String current_version;
    MyDialog myDialog;
    private NotificationManager notificationManager = null;
    Notification mNotification;
    public VersionUtils(Context context) {
        this.context = context;
    }

    // 软件-版本
    public static String getAppVersionName() {
        String versionName = "";
        Application app = MyApplication.getInstance();
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = app.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(app.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static int getAppVersionCode() {
        int versionCode = 0;
        Application app = MyApplication.getInstance();
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = app.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(app.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getDeviceInfo(Context context) {
        try{
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if( TextUtils.isEmpty(device_id) ){
                device_id = mac;
            }

            if( TextUtils.isEmpty(device_id) ){
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //检查版本更新
    String isForceUpdate = "";
    public void checkVersion(String android, String patient, final boolean isShowMsg) {
//        if (!SharedPreferencesUtils.preferences.getBoolean("SHOWUPDATE",true)||isShowMsg)
//            return;
            initCustomDownloadNotification();
            //执行下载,进度条
            final MyListener listener1 = new MyListener() {
                @Override
                public void callback(String text) {
                    Intent intent = new Intent(context,
                            NotificationDownloadService.class);
                    context.startService(intent);
                    ToastUtils.s("开始下载...");
                    //这个是ui线程回调，可直接操作UI
//                    final UIProgressListener uiProgressResponseListener = new UIProgressListener() {
//                        @Override
//                        public void onUIProgress(long bytesRead, long contentLength, boolean done) {
//                            if (contentLength != -1) {
//                                //长度未知的情况下回返回-1
//                                long processCount = (100 * bytesRead) / contentLength;
//                                Log.e(TAG, processCount + "% done");
//                                if (processCount < 100) {
//                                    mNotification.contentView.setProgressBar(R.id.pb_download, 100, (int) processCount, false);
//                                } else {
//                                    mNotification.contentView.removeAllViews(R.id.pb_download);
//                                    mNotification.flags = Notification.FLAG_AUTO_CANCEL;
//                                    Intent intent = new Intent(context, context.getClass());
//                                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//                                    mNotification.setLatestEventInfo(context, "" + context.getString(R.string.app_name), "下载完成", pendingIntent);
//                                }
//                                notificationManager.notify(0, mNotification);
//                            }
//                        }
//
//                        @Override
//                        public void onUIStart(long bytesRead, long contentLength, boolean done) {
//                            super.onUIStart(bytesRead, contentLength, done);
//                            Toast.makeText(context, "下载开始", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onUIFinish(long bytesRead, long contentLength, boolean done) {
//                            super.onUIFinish(bytesRead, contentLength, done);
//                            Toast.makeText(context, "下载已完成", Toast.LENGTH_SHORT).show();
//                        }
//                    };

//                    final OkHttpClient client = new OkHttpClient();
//                    final Request downloadRequest = new Request.Builder()
//                            .url(HttpUtils.DOWNLOAD_URL)
//                            .build();
//
//                    //包装Response使其支持进度回调
//                    ProgressHelper.addProgressResponseListener(client, uiProgressResponseListener).newCall(downloadRequest).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Request request, IOException e) {
//                            Log.e(TAG, "error ", e);
//                        }
//
//                        @Override
//                        public void onResponse(Response response) throws IOException {
//                            InputStream is = null;
//                            byte[] buf = new byte[2048];
//                            int len = 0;
//                            FileOutputStream fos = null;
//                            String path = FileUtils.getAvailablePath(context);
//                            try {
//                                is = response.body().byteStream();
//                                File dir = new File(path);
//                                File downloadFile = new File(path, "shell.apk");
//
////                                if (downloadFile.exists()){
////                                    downloadFile.delete();
////                                }
//
//                                fos = new FileOutputStream(downloadFile);
//
//                                while ((len = is.read(buf)) != -1) {
//                                    fos.write(buf, 0, len);
//                                }
//                                fos.flush();
//    //                                Uri installUri = Uri.fromParts("package",""+downloadFile.getAbsolutePath(),null);
//    //                                Intent returnIt = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);
//    //                                startActivity(returnIt);
//
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
////                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    //                                   String type = getMIMEType(f);
//                                //   intent.setDataAndType(Uri.fromFile(f), type);
//                                intent.setDataAndType(Uri.fromFile(downloadFile),
//                                        "application/vnd.android.package-archive");
//                                context.startActivity(intent);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } finally {
//                                try {
//                                    if (is != null) is.close();
//                                    if (fos != null) fos.close();
//                                } catch (IOException e) {
//                                   e.printStackTrace();
//                                }
//                            }
//                        }
//                    });
                }
            };

            final MyListener listener2 = new MyListener() {
                @Override
                public void callback(String text) {
                    if ("1".equals(isForceUpdate)){
                        Toast.makeText(context,"请更新后再使用!",Toast.LENGTH_SHORT).show();
                        MobclickAgent.onKillProcess(context);
                        System.exit(0);
                    }
                }
            };

        ShellRequestBean shellRequestBean = new ShellRequestBean();
        shellRequestBean.datas.clear();
        shellRequestBean.url = HttpUtils.SEHLL_GET_VERSION;
        shellRequestBean.datas.put("system",android);
        shellRequestBean.datas.put("app",patient);
        OkHttpClientManager.httpAsyn(shellRequestBean, new ShellResultCallback<String>() {
                @Override
                public void onError(Request request, Exception e) {
                    Toast.makeText(context, "版本检查出错", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String response) {
                    Log.v(TAG, response);
                        String version = "";
                        try {
                        DomUtil domUtil = new DomUtil(response);
                        if ("1".equals(domUtil.getNode("success"))) {
                            version = domUtil.getNode("version");
                            isForceUpdate = domUtil.getNode("isForceUpdate");
                            if ("1".equals(isForceUpdate)){
                                Intent intent = new Intent(context,
                                        NotificationDownloadService.class);
                                context.startService(intent);
                                ToastUtils.s("开始下载...");
                                return;
                            }
                            boolean isN = false;
                            try {
                               String currentV = getAppVersionName().replaceAll("[^\\d]+","");
                               String onLineV = version.replaceAll("[^\\d]+","");
                                int cl = currentV.length();
                                int onl = onLineV.length();
                                if (cl>onl){
                                    for (int i = 0;i<cl -onl;i++){
                                        onLineV += "0";
                                    }
                                }else {
                                    for (int i = 0;i<onl-cl;i++){
                                        currentV += "0";
                                    }
                                }
                                isN = Integer.valueOf(currentV) < Integer.valueOf(onLineV);
                            } catch (Exception e) {
                                isN = !getAppVersionName().equals(version);
                                e.printStackTrace();
                            }
                            if (isN){
                                if (myDialog == null) {
                                    myDialog = new MyDialog(context);
                                    myDialog.updateVersion("发现新版本", domUtil.getNode("update"), listener1, listener2);
//                                    myDialog.cb.setVisibility(
//                                            isShowMsg
//                                                    ? View.GONE:View.VISIBLE);
                                }
                            }else {
                                if (isShowMsg)
                                    Toast.makeText(context,"当前已是最新版本",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            String msg = domUtil.getNode("message");
                            if (!TextUtils.isEmpty(msg) && isShowMsg)
                                Toast.makeText(context, "" + msg, Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    //初始化自定义通知
    private void initCustomDownloadNotification() {
        String tickerText = "小贝壳Shell下载中...";
        long when = System.currentTimeMillis();
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotification == null)
        mNotification = new Notification();

        mNotification.icon = R.drawable.icon360;
        mNotification.tickerText = tickerText;
        mNotification.when = when;
//        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
//        mNotification.defaults = Notification.DEFAULT_SOUND;

        Intent intent = new Intent(context, context.getClass());

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        mNotification.contentIntent = pendingIntent;

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.activity_notification);
        rv.setProgressBar(R.id.pb_download, 100, 0, false);
        mNotification.contentView = rv;
//        notificationManager.notify(0,mNotification);
    }
}

