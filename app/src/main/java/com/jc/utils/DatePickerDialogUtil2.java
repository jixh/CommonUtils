package com.jc.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;


public class DatePickerDialogUtil2 {

    Context context = null;
    TextView textview = null;

    public DatePickerDialogUtil2(Context context,TextView textview) {
        this.context = context;
        this.textview = textview;
    }


    public void showDatePickerDialog(String date){
        MyDatePickerDialog datePickerDialog = null;
        if(date.equals("")){
            final Calendar calendar = Calendar.getInstance(Locale.CHINA);
            datePickerDialog = new MyDatePickerDialog(context, setting, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }else{
            datePickerDialog = new MyDatePickerDialog(context, setting,Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1])-1, Integer.parseInt(date.split("-")[2]));
        }
        
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "未知", new DialogInterface.OnClickListener() {  
        	@Override  
        	public void onClick(DialogInterface dialog, int which) {  
        		textview.setText("未知");
        	}  
        });  

        
        datePickerDialog.show();
    }


    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener setting = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth){
            textview.setText(year+"-"+((monthOfYear+1)<10?("0"+(monthOfYear+1)):(monthOfYear+1))+"-"+(dayOfMonth<10?("0"+dayOfMonth):dayOfMonth));
        }
    };

}
