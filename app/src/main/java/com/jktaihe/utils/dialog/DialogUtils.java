package com.jktaihe.utils.dialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.DatePicker;

import com.jktaihe.utils.R;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by hzwukewei on 2016/5/12.
 */
public class DialogUtils {

    /***
     * 获取一个等待的dialog*
     * @param context
     * @param message
     * @return dialog
     */
    public static ProgressDialog showProgressDialog(Context context, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        if (!TextUtils.isEmpty(message))
            dialog.setMessage(message);
        dialog.setCancelable(false);
        return dialog;
    }
    /***
     * 获取一个信息对话框，注意需要自己手动调用show方法显示
     * @param context
     * @param message
     * @param onClickListener
     * @return
     */
    public static AlertDialog.Builder getMessageDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialogBuilder(context);
        builder.setMessage(message);
        builder.setPositiveButton("确定", onClickListener);
        return builder;
    }

    public static AlertDialog.Builder getMessageDialog(Context context, String message) {
        return getMessageDialog(context, message, null);
    }


    /***
     * 显示一个确定和取消
     *
     * @param
     * @param
     * @return
     */
    public static void getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onOkClickListener, DialogInterface.OnClickListener onCancleClickListener) {
        AlertDialog.Builder builder = getDialogBuilder(context);
        builder.setMessage(message)
                .setPositiveButton("确定", onOkClickListener)
                .setNegativeButton("取消", onCancleClickListener)
                .show();
    }

    /**
     *
     * @param context
     * @param message
     * @param ok
     * @param no
     * @param onOkClickListener
     */
    public static void getConfirmDialog(Context context, String message,String ok,String no, DialogInterface.OnClickListener onOkClickListener) {
        AlertDialog.Builder builder = getDialogBuilder(context);
        builder.setMessage(message)
                .setPositiveButton(ok, onOkClickListener)
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
    /***
     * 显示一个确定和取消
     * @param
     * @param
     * @return
     */
    public static AlertDialog showConfirmDialog(Context context, String message, DialogInterface.OnClickListener onOkClickListener) {

        AlertDialog.Builder builder = getDialogBuilder(context);

        return builder.setMessage(message)
                .setPositiveButton("确定", onOkClickListener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    public static AlertDialog.Builder getConfirmDialogBuilder(Context context, String message, DialogInterface.OnClickListener onOkClickListener) {
        AlertDialog.Builder builder = getDialogBuilder(context);
        builder.setMessage(message)
                .setPositiveButton("确定", onOkClickListener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder;
    }

    public static AlertDialog getConfirmDialog(Context context,String title, String message, DialogInterface.OnClickListener onOkClickListener) {
        AlertDialog.Builder builder = getDialogBuilder(context);
        return builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton("确定", onOkClickListener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }


    /**
     * 日期选择范围： 100年前~ 当前日期
     * 默认日期：1990-01-01
     * @param context
     * @param listener
     * @return
     */
    public static DatePickerDialog getDatePickerDialog1(Context context, final DatePickerDialog.OnDateSetListener listener) {
        final Calendar selectCalendar = Calendar.getInstance(Locale.CHINA);

        final Calendar endCalendar = Calendar.getInstance(Locale.CHINA);

        final Calendar startCalendar = Calendar.getInstance(Locale.CHINA);

        startCalendar.set(endCalendar.get(Calendar.YEAR) - 100, endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectCalendar.set(year,monthOfYear,dayOfMonth);
                if (startCalendar.after(selectCalendar)||selectCalendar.after(endCalendar))return;//fix可以点击bug有效果但仍然可以点击bug
                if (listener!=null)listener.onDateSet(view,year,monthOfYear,dayOfMonth);
            }
        }, 1990, 0, 1);

        DatePicker datePicker = datePickerDialog.getDatePicker();
        setMinHMS(startCalendar);
        datePicker.setMinDate(startCalendar.getTimeInMillis());
        setMaxHMS(endCalendar);
        datePicker.setMaxDate(endCalendar.getTimeInMillis());
        return datePickerDialog;
    }

    /**
     * 有中间按钮的情况
     * @param context
     * @param listener
     * @return
     */
    public static DatePickerDialog getDatePickerDialog(Context context, final DatePickerDialog.OnDateSetListener listener) {
        final Calendar selectCalendar = Calendar.getInstance(Locale.CHINA);
        final Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
        final DatePickerDialog datePickerDialog;
        if (Build.VERSION.SDK_INT >= 21 ) {
             datePickerDialog = new AppDatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    selectCalendar.set(year,monthOfYear,dayOfMonth);
                    if (selectCalendar.after(endCalendar))return;//fix可以点击bug有效果但仍然可以点击bug
                    if (listener!=null)listener.onDateSet(view,year,monthOfYear,dayOfMonth);
                }
            }, selectCalendar.get(Calendar.YEAR), selectCalendar.get(Calendar.MONTH), selectCalendar.get(Calendar.DAY_OF_MONTH));

            if (listener!=null)
                datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "未接种", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener!=null)
                        listener.onDateSet(null,-1,-1,-1);
                    }
                });
        }else {
            datePickerDialog = new AppDatePickerDialog(context,null,selectCalendar.get(Calendar.YEAR), selectCalendar.get(Calendar.MONTH), selectCalendar.get(Calendar.DAY_OF_MONTH));

            datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatePicker datePicker = datePickerDialog.getDatePicker();
                    int year = datePicker.getYear();
                    int monthOfYear = datePicker.getMonth();
                    int dayOfMonth = datePicker.getDayOfMonth();
                    selectCalendar.set(year,monthOfYear,dayOfMonth);

                    if (selectCalendar.after(endCalendar))return;//fix可以点击bug有效果但仍然可以点击bug

                    if (listener!=null)listener.onDateSet(datePicker,year,monthOfYear,dayOfMonth);
                }
            });

            datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "未接种", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(listener!=null)
                        listener.onDateSet(null, -1, -1, -1);
                }

            });
        }
        DatePicker datePicker = datePickerDialog.getDatePicker();
        setMaxHMS(endCalendar);
        datePicker.setMaxDate(endCalendar.getTimeInMillis());
        return datePickerDialog;
    }

    /**
     *   服用药物：弹窗选择； 开始服用日期：只能选择今天以前的日期；
     * @param context
     * @param listener
     * @return
     */
     public static DatePickerDialog getDrugsStartDatePickerDialog(Context context,String endDate, final DatePickerDialog.OnDateSetListener listener) {

         final Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
         final Calendar selectCalendar = Calendar.getInstance(Locale.CHINA);

         if (!TextUtils.isEmpty(endDate) && !"至今".equals(endDate)){
             try {
                 String[] ymd = endDate.split("-");
                 endCalendar.set(Integer.valueOf(ymd[0]),Integer.valueOf(ymd[1])-1,Integer.valueOf(ymd[2]));
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }


         DatePickerDialog datePickerDialog = new AppDatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            selectCalendar.set(year,monthOfYear,dayOfMonth);

            if (selectCalendar.after(endCalendar)) return;//fix可以点击bug有效果但仍然可以点击bug

            if (listener!=null)listener.onDateSet(view,year,monthOfYear,dayOfMonth);
        }
    }, endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));

    DatePicker datePicker = datePickerDialog.getDatePicker();
    setMaxHMS(endCalendar);
    datePicker.setMaxDate(endCalendar.getTimeInMillis());
    return datePickerDialog;
}

    private static void setMinHMS(Calendar calendar) {
        if (calendar==null)return;
        calendar.set(Calendar.HOUR_OF_DAY,calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE,calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND,calendar.getMinimum(Calendar.MILLISECOND));
    }

    private static void setMaxHMS(Calendar calendar) {
        if (calendar==null)return;
        calendar.set(Calendar.HOUR_OF_DAY,calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE,calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND,calendar.getMaximum(Calendar.MILLISECOND));
    }

    public static void getListDialog(Context context, final String[] items, DialogInterface.OnClickListener listener ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppDialog)
                .setItems(items, listener);
        builder.show();
    }

    /***
     * 获取一个dialog
     *
     * @param context
     * @return
     */
    public static AlertDialog.Builder getDialogBuilder(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.AppDialog);
        return builder;
    }

}