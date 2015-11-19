package com.jc.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by hzjixiaohui on 2015/10/30.
 */
public abstract class RequestBean implements Serializable{
    public static final String GET = "GET";
    public static final String POST = "POST";
    public String url;
    public String method;
    public Map<String,String> datas;
}
