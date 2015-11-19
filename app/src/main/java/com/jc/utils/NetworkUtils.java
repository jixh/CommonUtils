package com.jc.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.hq.shell.R;
import com.hq.shell.activity.MyApplication;
import com.jc.commonutils.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by Administrator on 2015/6/2.
 */
public class NetworkUtils {

    static HttpURLConnection conn = null;

    @Deprecated
    public static String getData(String sendUrl,String[] k,String[] v){
        String  s = "";
        try{
            Properties prop = new Properties();
            InputStream in = (MyApplication.getInstance().getApplicationContext()).getResources().openRawResource(R.raw.setting);
            prop.load(in);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance().getApplicationContext());
            
//            sendUrl += "?USERNAME="+ URLEncoder.encode(preferences.getString("username", ""), "UTF-8");
            sendUrl += "?PASSWORD="+ URLEncoder.encode(preferences.getString("password", ""), "UTF-8");
            
            for(int i=0;i<k.length;i++){
            	sendUrl += "&"+k[i]+"="+ URLEncoder.encode(v[i], "UTF-8");
//        		sendUrl += "&"+k[i]+"="+v[i];
            }
            Log.e("GET", prop.getProperty("url").trim() + sendUrl);
            URL url = new URL(prop.getProperty("url").trim()+sendUrl);
			// url.openConnection()打开网络链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
//			X509HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
//            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

			conn.setRequestMethod("GET");// 设置请求的方式
			conn.setReadTimeout(20000);// 设置超时的时间
			conn.setConnectTimeout(20000);// 设置链接超时的时间
			// 设置请求的头
			conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
			// 获取响应的状态码 404 200 505 302
			if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();

                // 创建字节输出流对象
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    os.write(buffer, 0, len);
                }
                // 释放资源
				is.close();
				os.close();
				// 返回字符串
				s = new String(os.toByteArray());

			} else {
				s = "<set><success>0</success><message>"+"连接失败"+"</message></set>";
			}
        }catch (Exception e){
            e.printStackTrace();
            s = "<set><success>0</success><message>"+e.getMessage()+"</message></set>";
        }finally {
            return s;
        }
    }
    @Deprecated
    public static String request(String sendUrl,String[] k,String[] v){
        String s = "";
        try{
        	
            Properties prop = new Properties();
            InputStream in = (MyApplication.getInstance().getApplicationContext()).getResources().openRawResource(R.raw.setting);
            prop.load(in);
            URL url = new URL(prop.getProperty("url").trim()+sendUrl);
            Log.e("POST",prop.getProperty("url").trim()+sendUrl);
            /****/
            //打开服务器
            conn=(HttpURLConnection)url.openConnection();
            //设置连接主机超时
            conn.setConnectTimeout(20*1000);
          //设置从主机读取数据超时
            conn.setReadTimeout(20*1000);
            //设置输入输出流
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置请求的方法为Post
           
            conn.setRequestMethod("POST");
            //Post方式不能缓存数据，则需要手动设置使用缓存的值为false
            conn.setUseCaches(false);
            
//            X509HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
//            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
            
            //连接数据库
            conn.connect();
            /**写入参数**/
            OutputStream os=conn.getOutputStream();
            //封装写给服务器的数据（这里是要传递的参数）
            DataOutputStream dos=new DataOutputStream(os);
            //写方法：name是key值不能变，编码方式使用UTF-8可以用中文
           
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance().getApplicationContext());
            
//            dos.writeBytes("USERNAME="+ URLEncoder.encode(preferences.getString("username", ""), "UTF-8"));
            dos.writeBytes("PASSWORD="+ URLEncoder.encode(preferences.getString("password", ""), "UTF-8"));

            for(int i=0;i<k.length;i++){
            	dos.writeBytes("&"+k[i]+"="+ URLEncoder.encode(v[i], "UTF-8"));
//        		dos.writeBytes("&"+k[i]+"="+ v[i]);
            }
            dos.flush();
            /**读服务器数据**/
            if(conn.getResponseCode()==200){
            	InputStream is=conn.getInputStream();
                //关闭外包装流
                dos.close();
                //创建包装流
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                s = br.readLine();
            }else{
            	s = "<set><success>0</success><message>"+"连接异常"+"</message></set>";;
            }
        }catch (Exception e){
            e.printStackTrace();
            s = "<set><success>0</success><message>"+"连接失败"+"</message></set>";
        }finally {
            conn.disconnect();
            return s;
        }
    }

}
