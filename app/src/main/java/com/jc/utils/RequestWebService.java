package com.jc.utils;

import android.net.ConnectivityManager;
import android.util.Log;

import com.hq.shell.activity.MyApplication;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.InputStream;
import java.util.Properties;

public final class RequestWebService {

	public static String request(String methodName,String[] args){
		Log.i("RequestWebService",methodName);
		String s = "{success:false,error:'连接数据库失败！'}";
		try {
			ConnectivityManager connectivityManager = NetworkState.init(MyApplication.getInstance().getApplicationContext());
			Boolean isConnected = NetworkState.isConnected(connectivityManager);
			if(isConnected){
				try {
					Properties prop = new Properties();   
					InputStream in = RequestWebService.class.getResourceAsStream("/setting.properties");   
					prop.load(in);   
					String ip = prop.getProperty("ip").trim(); 
					String port = prop.getProperty("port").trim(); 
					String webservice = prop.getProperty("webservice").trim();
					//Toast.makeText(MainActivity.this, ip, Toast.LENGTH_SHORT).show();
			        String serviceUrl = "http://"+ip+":"+port+"/"+webservice+"/TestPort";  
			        String namespace  = "http://server/";
			        //String methodName = "login";  
			        // 第1步：创建SoapObject对象，并指定WebService的命名空间和调用的方法名  
			        SoapObject request = new SoapObject(namespace, methodName);  
			        // 第2步：设置WebService方法的参数  
			        for(int i=0;i<args.length;i++){
			        	request.addProperty("arg"+i, args[i]);
//			        	Log.i(methodName, args[i]);
			        }
			        // 第3步：创建SoapSerializationEnvelope对象，并指定WebService的版本  
			        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
			        // 设置bodyOut属性  
			        envelope.dotNet = false;  
			        envelope.setOutputSoapObject(request);  
			        // 第4步：创建HttpTransportSE对象，并指定WSDL文档的URL  
			        HttpTransportSE ht = new HttpTransportSE(serviceUrl);          
			        // 第5步：调用WebService 
			        ht.call(namespace+methodName, envelope); 
			        SoapObject result = (SoapObject)envelope.bodyIn;  
			        s = (String)result.getProperty(0).toString();
		        }catch (Exception e){ 
		        	e.printStackTrace();
		        	s = "{success:false,error:'【"+e.getMessage().replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'")+"】'}";
		        }
			}else{
				s = "{success:false,error:'网络未连接，请检查网络！'}";
			}
		} catch (Exception e) {
			s = "{success:false,error:'【"+e.getMessage().replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'")+"】'}";
		} finally{
			return s;
		}
	}
}
