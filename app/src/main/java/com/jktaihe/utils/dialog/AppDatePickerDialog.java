package com.jktaihe.utils.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

import com.jktaihe.utils.R;


/**
 * @author hzjixiaohui
 * @Desc
 * @date 2017-4-21
 */

public class AppDatePickerDialog extends DatePickerDialog {

    public AppDatePickerDialog(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, R.style.AppDialog, listener, year, month, dayOfMonth);
    }

    public AppDatePickerDialog(@NonNull Context context, @StyleRes int themeResId, @Nullable OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, themeResId, listener, year, monthOfYear, dayOfMonth);
    }

    @Override
    protected void onStop() {
//        super.onStop(); fixbug android 4.1/4.2 执行onDateSet两次
    }
}
