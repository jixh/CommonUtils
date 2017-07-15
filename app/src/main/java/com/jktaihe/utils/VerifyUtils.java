package com.jktaihe.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 *
 * 正则表达式工具。
 * http://www.oschina.net/news/42768/12-resources-for-mastering-regular-expressions?p=2
 *
 */

public class VerifyUtils {

    /**
     * 邮件地址
     */
    public static final String EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    /**
     * 手机号
     */
    public static final String PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 匹配 IP地址、前后有汉字、带参数
     */
    public static final String URL = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

    /**
     * 匹配 URL中的域名部分
     */
    public static final String URL_DOMAIN = "((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+(:[0-9]+)?|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)";


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
     * 验证多个正则
     * @param str 要验证的字符串
     * @param type 多个正则表达式
     * @return true 表示有符合的匹配
     */
    public static boolean verifys( String str,String ... type){
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
