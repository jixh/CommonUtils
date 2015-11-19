package com.jc.utils;

/**
 * Created by hzjixiaohui on 2015/10/28.
 */
public class StringUtils {

    public static boolean noEmpty(CharSequence str){
        return null != str && ! "".equals(str);
    }

    public static boolean isEmpty(CharSequence str){
        return !noEmpty(str);
    }



}
