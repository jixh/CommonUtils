package com.jktaihe.utils;

import android.content.Context;

import java.io.InputStream;
import java.util.Properties;
/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */
public class PropertiesUtils {

    private static Properties  prop = new Properties();

    public static String getValue(Context context,int sourceid, String name){
    	String s = "";
        try {
             InputStream in = context.getResources().openRawResource(sourceid);
             prop.load(in);
             s = prop.getProperty(name).trim();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
        	return s;
        }
    }

}
