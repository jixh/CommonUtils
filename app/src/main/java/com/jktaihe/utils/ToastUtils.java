package com.jktaihe.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.jktaihe.JKTAIHEUtils;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */

public class ToastUtils {

    private ToastUtils() {
        throw new AssertionError();
    }

    public static void s(CharSequence text) {
        show(JKTAIHEUtils.appContext, text, Toast.LENGTH_SHORT);
    }
    public static void l(CharSequence text) {
        show(JKTAIHEUtils.appContext, text, Toast.LENGTH_LONG);
    }
    public static void s(int resId) {
        show(JKTAIHEUtils.appContext, JKTAIHEUtils.appContext.getResources().getText(resId), Toast.LENGTH_SHORT);
    }
    public static void l(int resId) {
        show(JKTAIHEUtils.appContext, JKTAIHEUtils.appContext.getResources().getText(resId), Toast.LENGTH_LONG);
    }

    public static void s(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void s(Context context, String text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void l(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_LONG);
    }

    public static void l(Context context, String text) {
        show(context, text, Toast.LENGTH_LONG);
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

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text, int duration) {
        if (!TextUtils.isEmpty(text))
           Toast.makeText(context, text, duration).show();
    }
}
