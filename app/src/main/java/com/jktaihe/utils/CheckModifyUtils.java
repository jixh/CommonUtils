package com.jktaihe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jktaihe on 2016/6/16.
 */
public class CheckModifyUtils {
    /**
     * @param context
     * @param aims 判断表达式 和 toast文案
     *
     *             基数位作为 条件 为空 或者 ture 则判断结束并返回结果
     * @return
     */
    public static boolean checkGroupValues(Context context,Object... aims){

        boolean flag = false;

        for (int i = 0,l = aims.length; i < l; i +=2 )
            if (aims[i] == null || aims[i].toString().trim().length() == 0|| (aims[i] instanceof Boolean && (boolean)aims[i]) ){
                ToastUtils.shortToast(context,aims[i+1].toString(), Toast.LENGTH_SHORT);
                flag = true;
                break;
            }
        return flag;

    }

    /**
     * 检查基数位是否为空
     * @param context
     * @param aims 判断表达式 和 toast对象
     * @return
     */
    public static boolean checkInputNoNull(Context context,Object... aims){

        boolean flag = false;

        for (int i = 0,l = aims.length; i < l; i +=2 )
            if (aims[i] == null ||  aims[i].toString().trim().length() == 0 || (aims[i] instanceof Boolean && (boolean)aims[i]) ){
                ToastUtils.shortToast(context,String.format(context.getString(R.string.case_check_input),aims[i+1] instanceof Integer ? context.getString(Integer.valueOf(aims[i+1].toString())):aims[i+1].toString()), Toast.LENGTH_SHORT);
                flag = true;
                break;
            }
        return flag;

    }


}
