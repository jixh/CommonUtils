package com.jktaihe.utils;

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
     * @param str
     * @return
     */
    public static boolean noEmptyStrict(CharSequence str){
        return null != str && ! "".equals(str.toString().trim()) && "null".equalsIgnoreCase(str.toString());
    }

}
