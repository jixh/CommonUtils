package com.jc.utils;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hzjixiaohui on 2015/11/6.
 */
public class VarifyUtils {

    public static final String Email =  "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    public static final String PHONE =  "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";


    public static boolean varify(@NonNull String str,@NonNull String type){
            Pattern p = Pattern.compile(type);
            Matcher m = p.matcher(str);
        return  m.matches();
    }

    public static boolean varify(@NonNull String str,@NonNull String ... type){
        boolean flag = false;
        for (String s:type){
            Pattern p = Pattern.compile(s);
            Matcher m = p.matcher(str);
            if (m.matches()){
                flag = true;
            }
        }
        return flag;
    }
}
