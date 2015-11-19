package com.jc.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzjixiaohui on 2015/10/30.
 */
public abstract class ResponseBean implements Serializable{
    private String code;
    private String msg ;
    private Object data;
    private Map<String,Object> datas = new HashMap<String,Object>();
    public abstract boolean isSuccess();

    @Override
    public String toString() {
        return "ResponseBean{" +
                "code='" + code +
                ", msg='" + msg +
                ", data=" + data +
                ", datas=" + datas +
                '}';
    }
}
