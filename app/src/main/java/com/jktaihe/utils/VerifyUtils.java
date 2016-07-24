package com.jktaihe.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */

public class VerifyUtils {

    public static final String EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    public static final String PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * @param email
     * @return
     */
    public boolean isEmail(String email) {
        Pattern p = Pattern.compile(EMAIL);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * @param phone
     * @return
     */
    public boolean isPhone(String phone) {
        Pattern p = Pattern.compile(PHONE);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * @param str
     * @param type
     * @return
     */
    public static boolean verify( String str, String type){
        Pattern p = Pattern.compile(type);
        Matcher m = p.matcher(str);
        return  m.matches();
    }

    /**
     * @param str
     * @param type
     * @return
     */
    public static boolean verify( String str,String ... type){
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
