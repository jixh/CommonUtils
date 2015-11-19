package com.jc.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.hq.shell.R;
import com.hq.shell.activity.MyApplication;
import com.hq.shell.bean.ResponseBean;
import com.hq.shell.bean.ShellResponseBean;
import com.hq.shell.redbag.bean.Redbags;
import com.jc.commonutils.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * 接口
 */
public class HttpUtils {
    private static String SERVER_URL;
    private static String password;
    public static String DOWNLOAD_URL;

    static {
        Properties prop = new Properties();
        InputStream in = (MyApplication.getInstance().getApplicationContext()).getResources().openRawResource(R.raw.setting);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance().getApplicationContext());
        try {
            prop.load(in);
          SERVER_URL =prop.getProperty("url",null).trim();
//          SERVER_URL = "http://192.168.8.156:8080/xbkws";//prop.getProperty("url",null).trim();
            DOWNLOAD_URL = prop.getProperty("download",null).trim();
            password = URLEncoder.encode(preferences.getString("password", ""), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //版本更新检查
    public static final String SEHLL_GET_VERSION = SERVER_URL + "/getVersion.html";
    //红包列表
    public static final String SEHLL_GET_MyPacketInfo = SERVER_URL + "/getMyPacketInfo.html";
    //红包兑换
    public static final String SEHLL_exchangeMyPacket = SERVER_URL + "/exchangeMyPacket.html";
    //红包明细
    public static final String SEHLL_getMyPacketDetailInfo = SERVER_URL + "/getMyPacketDetailInfo.html";


    public static InputStream getStreamFromURL(String imageURL) {
        InputStream in = null;
        try {
            URL url = new URL(imageURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            in = connection.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
    }

    public static ResponseBean getResponse(String flag , String data) {
        if (StringUtils.isEmpty(data))
            return new ShellResponseBean();
        Log.e("ResponseBean.data=", data);

        ShellResponseBean
                shellResponseBean = new ResponseBeanJSON<ShellResponseBean>().
                parse(data, ShellResponseBean.class);

        if (shellResponseBean == null) {
            return new ShellResponseBean();
        }

        if (SEHLL_GET_MyPacketInfo.equals(flag)) {
                Redbags redbags = new ResponseBeanJSON<Redbags>().
                        parse(""+shellResponseBean.getData(), Redbags.class);
                if (redbags != null)
                    shellResponseBean.getDatas().put("0", redbags);
        } else if (SEHLL_exchangeMyPacket.equals(flag)) {

        }
        return shellResponseBean;
    }
}

abstract class RB<T>{
    public abstract T parse(String json,Class<T> classT);
}

class ResponseBeanJSON<T> extends RB<T>{
    public  T  parse(String json,Class<T> classT){
        T t = null;
        try {
            t = (T)JSON.parseObject(json,classT);
            Log.e("parseJson", ""+classT+"=\n" + t.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return t;
        }
    }
}

class ResponseBeanGson<T> extends RB<T>{
    public  T  parse(String json,Class<T> classT){
        T t = null;
        try {
            Gson mgson = new Gson();


//            Redbags redbags = new Redbags();
//
//            try {
//                redbags.setPacket_total_momey(jsonObject.get("packet_total_momey"));
//                redbags.setZfb_account(jsonObject.getString("zfb_account"));
//                List<Redbag> list = gson.fromJson(
//                        jsonObject.getJSONObject("packetBaseInfos").toString(),
//                        new TypeToken<List<Redbag>>() {
//                        }.getType());
//                redbags.setPacketBaseInfos(list);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            t = (T)JSON.parseObject(json,classT);




            Log.e("parseJson", ""+classT+"=\n" + t.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return t;
        }
    }
}