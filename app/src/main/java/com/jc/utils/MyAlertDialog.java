package com.jc.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.DigitsKeyListener;
import android.view.ContextThemeWrapper;
import android.widget.EditText;
import android.widget.TextView;

import com.hq.shell.R;
import com.jc.commonutils.R;

/**
 * Created by Administrator on 2015/5/12.
 */
public class MyAlertDialog {

    Context context = null;
    TextView textview = null;
    String[] data = null;
    String title = null;

    String value = null;

    EditText edittext = null;

    //EditText
    public MyAlertDialog(Context context, TextView textview, String title, String value){
        this.context = context;
        this.textview = textview;
        this.title = title;
        this.value = value;
    }

    //Items
    public MyAlertDialog(Context context, TextView textview, String title, String[] data){
        this.context = context;
        this.textview = textview;
        this.title = title;
        this.data = data;
    }

    //EditText
    public void edit(int inputType){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
//        builder.setIcon(android.R.drawable.ic_dialog_info);
        edittext = new EditText(context);
        if(inputType!=-1){//-1:不限制格式
            edittext.setInputType(inputType);
            edittext.setKeyListener(new DigitsKeyListener(false, true));
        }
        edittext.setSingleLine(true);
        edittext.setHeight(80);
        edittext.setBackgroundResource(R.drawable.bg_edittext);
        edittext.setText(value);
        builder.setView(edittext);
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textview.setText(edittext.getText().toString());
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    //Items
    public void select(){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.Dialog));
        builder.setTitle(title);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                textview.setText(data[which]);
            }
        };
        builder.setItems(data, listener);
//    	builder.setPositiveButton("确定", null);
        builder.setNegativeButton("取消", null);
        builder.show();
    }
}
