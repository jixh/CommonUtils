package com.jktaihe.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */
public class StringUtils {

    /**
     * @param str
     * @return
     */
    public static boolean noEmpty(CharSequence str){
        return null != str && ! "".equals(str);
    }

    /**
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str){
        return !noEmpty(str);
    }

    /**
     * str != null && str != "".trim（） && str!= null
     * @param str
     * @return
     */
    public static boolean noEmptyStrict(CharSequence str){
        return null != str && ! "".equals(str.toString().trim()) && "null".equalsIgnoreCase(str.toString());
    }

    /**
    * if str == null return ""
    * */
    public static CharSequence getStr(CharSequence str){
        return getStr(str,"");
    }

    /**
     * if str == null return defaultValue
     * */
    public static CharSequence getStr(CharSequence str,CharSequence defaultValue){
        return noEmpty(str)?str:defaultValue;
    }

    /**
     * 获取字符串字节长度
     * @param str
     * @return
     */
    public int getStrLength(CharSequence str){

        int defaultValue = 0;

        if (isEmpty(str))return defaultValue;

        try {
            defaultValue = str.toString().getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return defaultValue;
    }
}
