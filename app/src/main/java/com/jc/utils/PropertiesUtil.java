package com.jc.utils;

import java.io.InputStream;
import java.util.Properties;

import com.hq.shell.activity.MyApplication;

public class PropertiesUtil {

    private static Properties  prop = new Properties();

    public static String getValue(int sourceid,String name){
    	String s = "";
        try {
             InputStream in = (MyApplication.getInstance().getApplicationContext()).getResources().openRawResource(sourceid);
             prop.load(in);
             s = prop.getProperty(name).trim();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
        	return s;
        }
    }

}
