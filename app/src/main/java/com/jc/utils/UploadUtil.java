package com.jc.utils;

import android.util.Log;

import com.hq.shell.R;
import com.hq.shell.activity.MyApplication;
import com.jc.commonutils.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class UploadUtil {

//	private static String newName = "image.jpg";
//	private static String uploadFile = "/sdcard/image.jpg";
//	private static String actionUrl = "http://192.168.18.21:8080/xbkws/upload.html";
	public static Boolean uploadPhotos(String[] photos,String[] photo_name,String username,String path){
		Boolean b = true;
		try {
			Properties prop = new Properties();
	        InputStream in = (MyApplication.getInstance().getApplicationContext()).getResources().openRawResource(R.raw.setting);
	        prop.load(in);
			for(int i=0;i<photos.length;i++){
				String photoPath = photos[i];
//				String[] array = photoPath.split("/");
//				String photoname = array[array.length-1];
				String photoname = photo_name[i];
				b = uploadFile(photoPath,photoname,prop.getProperty("url").trim()+"/upload.html?username="+username+"&path="+path);
				Log.e("pic",prop.getProperty("url").trim()+"/upload.html?username="+username+"&path="+path);
				if(!b){
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}finally{
			return b;
		}
	}
	
	
	/* 上传文件至Server的方法 */
	public static  Boolean uploadFile(String uploadFile,String newName,String actionUrl) {
		Boolean b = true;
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		try {
			URL url = new URL(actionUrl);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			/* 允许Input、Output，不使用Cache */
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			/* 设置传送的method=POST */
			con.setRequestMethod("POST");
			/* setRequestProperty */
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			
//			X509HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
//            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
			
			/* 设置DataOutputStream */
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; " + "name=\"file1\";filename=\"" + newName + "\"" + end);
			ds.writeBytes(end);
			/* 取得文件的FileInputStream */
			FileInputStream fStream = new FileInputStream(uploadFile);
			/* 设置每次写入1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = -1;
			/* 从文件读取数据至缓冲区 */
			while ((length = fStream.read(buffer)) != -1) {
				/* 将资料写入DataOutputStream中 */
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
			fStream.close();
			ds.flush();
			ds.close();
			/**读服务器数据**/
            InputStream is=con.getInputStream();
            //创建包装流
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
//            s = br.readLine();
            br.close();
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
//			s = "<set><result><success>0</success><message>上传失败</message></result></set>";
		}finally{
			return b;
		}
	}

}
