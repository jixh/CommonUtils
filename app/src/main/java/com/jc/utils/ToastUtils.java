package com.jc.utils;

import android.content.Context;
import android.widget.Toast;

import com.hq.shell.activity.MyApplication;

/**
 * ToastUtils
 */
public class ToastUtils {

    private ToastUtils() {
        throw new AssertionError();
    }

    public static void show(int resId) {
        show(MyApplication.getInstance(), MyApplication.getInstance().getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(CharSequence text) {
        show(MyApplication.getInstance(), text, Toast.LENGTH_SHORT);
    }

    public static void s(final CharSequence text) {
        show(MyApplication.getInstance(), text, Toast.LENGTH_SHORT);
    }
    public static void l(CharSequence text) {
        show(MyApplication.getInstance(), text, Toast.LENGTH_LONG);
    }
    public static void s(int resId) {
        show(MyApplication.getInstance(), MyApplication.getInstance().getResources().getText(resId), Toast.LENGTH_SHORT);
    }
    public static void l(int resId) {
        show(MyApplication.getInstance(), MyApplication.getInstance().getResources().getText(resId), Toast.LENGTH_LONG);
    }

    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        if (StringUtils.noEmpty(text))
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(final Context context,final  CharSequence text,final  int duration) {
        if (StringUtils.noEmpty(text))
        Toast.makeText(context, text, duration).show();
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }


}
