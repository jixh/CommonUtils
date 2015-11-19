package com.jc.utils;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hq.shell.R;
import com.hq.shell.activity.MyApplication;
import com.hq.shell.commonAdapter.CommonAdapter;
import com.hq.shell.commonAdapter.ViewHolder;
import com.jc.commonutils.R;

/**
 * Created by Administrator on 2015/5/27.
 */
public class MyDialog{
    AlertDialog alertDialog = null;
    Context context = null;

    public MyDialog(Context context){
        this.context = context;
    }

    public void dismiss(){
        alertDialog.dismiss();
    }

    private void setWindowSize(Window window){
    	WindowManager.LayoutParams lp = window.getAttributes();        
        window.setGravity(Gravity.CENTER);              
        lp.width = lp.width - 100; // 宽度        
        lp.height = lp.height; // 高度        
        lp.alpha = 1.0f; // 透明度        
        window.setAttributes(lp);
    }
    
    //warning
    public void warning(String text,Boolean cancelAble){
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_view = inflater.inflate(R.layout.waiting_dialog_layout, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(dialog_view);

        ((TextView) window.findViewById(R.id.text)).setText(text);

        //
        ImageView loading = (ImageView)dialog_view.findViewById(R.id.image_loading);
        loading.setVisibility(View.GONE);

        ImageView cancel = (ImageView)dialog_view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        if(!cancelAble){
            cancel.setVisibility(View.GONE);
        }
    }

    //waiting
    public void waiting(String text,Boolean cancelAble){
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_view = inflater.inflate(R.layout.waiting_dialog_layout, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(dialog_view);

        ((TextView) window.findViewById(R.id.text)).setText(text);

        //旋转
        ImageView loading = (ImageView)dialog_view.findViewById(R.id.image_loading);
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.tip);
        LinearInterpolator lin = new LinearInterpolator();
        anim.setInterpolator(lin);
        if (anim != null) {
            loading.startAnimation(anim);
        }

        ImageView cancel = (ImageView)dialog_view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        if(!cancelAble){
            cancel.setVisibility(View.GONE);
        }
    }

    //HBVDNA
    public void hbvdna(String title,final TextView textview_hbvdna,final TextView textview_hbvdna_up,final TextView HBVDNA,final TextView unit){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialog_view = inflater.inflate(R.layout.dialog_hbvdna, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
        Window window = alertDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//AlertDialog中的EditText获取焦点时，弹起软键盘
        window.setContentView(dialog_view);
        ((TextView) dialog_view.findViewById(R.id.title)).setText(title);
        
        final RadioGroup radioGroup_unit = (RadioGroup)dialog_view.findViewById(R.id.radioGroup_unit);
        final EditText hbvdna = (EditText)dialog_view.findViewById(R.id.HBVDNA);
        final EditText hbvdna_up = (EditText)dialog_view.findViewById(R.id.HBVDNA_UP);
        final TextView _unit = (TextView)dialog_view.findViewById(R.id.unit);
        final CheckBox checkbox_yingxing = (CheckBox)dialog_view.findViewById(R.id.checkbox_yingxing);
        
        if(unit.getText().equals(((RadioButton)dialog_view.findViewById(R.id.unit0)).getText())){
        	radioGroup_unit.check(R.id.unit0);
        }else if(unit.getText().equals(((RadioButton)dialog_view.findViewById(R.id.unit1)).getText())){
        	radioGroup_unit.check(R.id.unit1);
        }
        
        radioGroup_unit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.unit0) {
//					unit.setText(((RadioButton)dialog_view.findViewById(R.id.unit0)).getText());
                    _unit.setText(((RadioButton) dialog_view.findViewById(R.id.unit0)).getText());
                } else if (checkedId == R.id.unit1) {
                    _unit.setText(((RadioButton) dialog_view.findViewById(R.id.unit1)).getText());
//					unit.setText(((RadioButton)dialog_view.findViewById(R.id.unit1)).getText());
                }
            }
        });
        
        checkbox_yingxing.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout layout_yangxing = (LinearLayout) dialog_view.findViewById(R.id.layout_yangxing);
                if (isChecked) {
                    layout_yangxing.setVisibility(8);
                    hbvdna.setText(null);
                    hbvdna_up.setText(null);
                } else {
                    layout_yangxing.setVisibility(0);
                }
            }
        });
        _unit.setText(unit.getText());
        if(HBVDNA.getText().toString().equals("阴性")){
        	checkbox_yingxing.setChecked(true);
        }else{
        	hbvdna.setText(textview_hbvdna.getText());
            hbvdna_up.setText(textview_hbvdna_up.getText());
        }
        
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setText("确定");
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unit.setText(_unit.getText());
                if (checkbox_yingxing.isChecked()) {
                    textview_hbvdna.setText(null);
                    textview_hbvdna_up.setText(null);
                    HBVDNA.setText("阴性");
                    alertDialog.dismiss();
                } else {
                    if (hbvdna.getText().toString().equals("") || hbvdna.getText().toString().equals(".") || hbvdna_up.getText().toString().equals("")) {
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "请正确填写HBVDNA", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Float.valueOf(hbvdna.getText().toString()) >= 10) {
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "请正确填写HBVDNA:底数应小于10", Toast.LENGTH_SHORT).show();
                        } else {
                            textview_hbvdna.setText(String.valueOf(Float.valueOf(hbvdna.getText().toString())));
                            textview_hbvdna_up.setText(hbvdna_up.getText());
                            HBVDNA.setText(String.valueOf(Float.valueOf(hbvdna.getText().toString())) + "*10^" + hbvdna_up.getText().toString());
                            alertDialog.dismiss();
                        }
                    }
                }
            }
        });
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("取消");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    
    //问医生 修改昵称
    public void callbackedit(String title,final MyListener listener){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialog_view = inflater.inflate(R.layout.dialog_edittext, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
        Window window = alertDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//AlertDialog中的EditText获取焦点时，弹起软键盘
        window.setContentView(dialog_view);
        ((TextView) dialog_view.findViewById(R.id.title)).setText(title);
        
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setText("确定");
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) dialog_view.findViewById(R.id.edittext)).getText().toString().trim().equals("")) {
                    Toast.makeText(context, "请填写内容", 500).show();
                } else {
                    listener.callback(((TextView) dialog_view.findViewById(R.id.edittext)).getText().toString());
                    alertDialog.dismiss();
                }

            }
        });
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("取消");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    
    
    //
    public void message(String title,String content){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialog_view = inflater.inflate(R.layout.dialog_text, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
        Window window = alertDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//AlertDialog中的EditText获取焦点时，弹起软键盘
        window.setContentView(dialog_view);
        ((TextView) dialog_view.findViewById(R.id.title)).setText(title);
        ((TextView) dialog_view.findViewById(R.id.text)).setText(content);
        ((TextView) dialog_view.findViewById(R.id.text)).setGravity(Gravity.LEFT);
        
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setVisibility(8);
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("关闭");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    
    
    //确定，取消，回调
    public void ok(int image,String title,final MyListener listener1,final MyListener listener2,String positive,String negative){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialog_view = inflater.inflate(R.layout.dialog_ok, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(true);
        Window window = alertDialog.getWindow();
               
//        setWindowSize(window);//设置Dialog window大小
        
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//AlertDialog中的EditText获取焦点时，弹起软键盘
        window.setContentView(dialog_view);
        
        ((ImageView) dialog_view.findViewById(R.id.icon)).setImageResource(image);
        
        ((TextView) dialog_view.findViewById(R.id.title)).setText(title);
        
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        if(positive!=null){
        	positiveButton.setText(positive);
            positiveButton.setOnClickListener(new View.OnClickListener() {
            	@Override
                public void onClick(View v) {
            		alertDialog.dismiss();
            		if(listener1!=null){
            			listener1.callback("");
            		}
                }
            });
        }else{
        	positiveButton.setVisibility(View.GONE);
        }
        
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        if(negative!=null){
        	negativeButton.setText(negative);
            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if(listener2!=null){
            			listener2.callback("");
            		}
                }
            });
        }else{
        	negativeButton.setVisibility(View.GONE);
        }
        
        if(positive==null && negative==null){
        	((View)dialog_view.findViewById(R.id.bottom)).setVisibility(View.GONE);
        }
    }
    
    
    //确定，取消，回调
    public void callback(String title,String text,final MyListener listener){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialog_view = inflater.inflate(R.layout.dialog_text, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        Window window = alertDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//AlertDialog中的EditText获取焦点时，弹起软键盘
        window.setContentView(dialog_view);
        ((TextView) dialog_view.findViewById(R.id.title)).setText(title);
        ((TextView) dialog_view.findViewById(R.id.text)).setText(text);
        
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setText("确定");
        positiveButton.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
        		alertDialog.dismiss();
        		listener.callback("");
            }
        });
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("取消");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    public CheckBox cb;
    //update
    public void updateVersion(String title,String text,final MyListener listener1,final MyListener listener2){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialog_view = inflater.inflate(R.layout.dialog_text, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        Window window = alertDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//AlertDialog中的EditText获取焦点时，弹起软键盘
        window.setContentView(dialog_view);
        ((TextView) dialog_view.findViewById(R.id.title)).setText(title);
        ((TextView) dialog_view.findViewById(R.id.text)).setText(text);

        cb = ((CheckBox) dialog_view.findViewById(R.id.cb_dialog));
//        cb.setVisibility(View.GONE);
//        cb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 SharedPreferencesUtils.put("SHOWUPDATE",cb.isChecked());
//            }
//        });

        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setText("立即更新");
        positiveButton.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
        		alertDialog.dismiss();
        		listener1.callback("");
            }
        });
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("暂不更新");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                listener2.callback("");
            }
        });
    }
    
    
    //Item
    public void select(String title,List<String> mDatas, final TextView textview){
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_view = inflater.inflate(R.layout.custom_dialog_layout, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//AlertDialog中的EditText获取焦点时，弹起软键盘
        window.setContentView(dialog_view);
        ((TextView) dialog_view.findViewById(R.id.title)).setText(title);
        ListView dialog_listview = (ListView)inflater.inflate(R.layout.dialog_listview, null);
        dialog_listview.setAdapter(new CommonAdapter<String>(MyApplication.getInstance().getApplicationContext(), mDatas, R.layout.dialog_item){
            @Override
            public void convert(ViewHolder helper, final String item){
                helper.setText(R.id.tv_item, item);
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textview.setText(item);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        LinearLayout content = (LinearLayout)dialog_view.findViewById(R.id.content);
        content.addView(dialog_listview);
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setVisibility(View.GONE);
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("取消");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    
    //Item
    public void showVistList(String title,List<String> mDatas, final TextView textview){
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_view = inflater.inflate(R.layout.custom_dialog_layout, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(dialog_view);
        ((TextView) window.findViewById(R.id.title)).setText(title);
        ListView dialog_listview = (ListView)inflater.inflate(R.layout.dialog_listview, null);
        dialog_listview.setAdapter(new CommonAdapter<String>(MyApplication.getInstance().getApplicationContext(), mDatas, R.layout.dialog_item_visit){
            @Override
            public void convert(ViewHolder helper, final String item){
            	helper.setText(R.id.tv_id, item.split(",")[0]);
                helper.setText(R.id.tv_item, item.split(",")[1]);
                helper.setText(R.id.tv_hbvdna, "病毒载量HBVDNA:"+item.split(",")[2]);
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	textview.setText(item.split(",")[0]);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        LinearLayout content = (LinearLayout)dialog_view.findViewById(R.id.content);
        content.addView(dialog_listview);
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setVisibility(View.GONE);
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("取消");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    
    //Item
    public void choice(String title,List<String> mDatas, final TextView textview){
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_view = inflater.inflate(R.layout.custom_dialog_layout, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(dialog_view);
        ((TextView) window.findViewById(R.id.title)).setText(title);
        ListView dialog_listview = (ListView)inflater.inflate(R.layout.dialog_listview, null);
        dialog_listview.setAdapter(new CommonAdapter<String>(MyApplication.getInstance().getApplicationContext(), mDatas, R.layout.dialog_item){
            @Override
            public void convert(ViewHolder helper, final String item){
            	helper.setText(R.id.tv_id, item.split(",")[0]);
                helper.setText(R.id.tv_item, item.split(",")[1]);
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	textview.setText(item.split(",")[0]);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        LinearLayout content = (LinearLayout)dialog_view.findViewById(R.id.content);
        content.addView(dialog_listview);
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setVisibility(View.GONE);
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("取消");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    
    	//Item
    public void callbackselect(String title,List<String> mDatas, final MyListener listener){
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_view = inflater.inflate(R.layout.custom_dialog_layout, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(dialog_view);
        ((TextView) window.findViewById(R.id.title)).setText(title);
        ListView dialog_listview = (ListView)inflater.inflate(R.layout.dialog_listview, null);
        dialog_listview.setAdapter(new CommonAdapter<String>(MyApplication.getInstance().getApplicationContext(), mDatas, R.layout.dialog_item){
            @Override
            public void convert(ViewHolder helper, final String item){
            	helper.setText(R.id.tv_id, item.split(",")[0]);
                helper.setText(R.id.tv_item, item.split(",")[1]);
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	listener.callback(item.split(",")[0]);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        LinearLayout content = (LinearLayout)dialog_view.findViewById(R.id.content);
        content.addView(dialog_listview);
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setVisibility(View.GONE);
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("取消");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    
    
    //注册：选择医院，医生
    public void choice2(String title,List<String> mDatas,final TextView id, final TextView textview){
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_view = inflater.inflate(R.layout.custom_dialog_layout, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(dialog_view);
        ((TextView) window.findViewById(R.id.title)).setText(title);
        ListView dialog_listview = (ListView)inflater.inflate(R.layout.dialog_listview, null);
        dialog_listview.setAdapter(new CommonAdapter<String>(MyApplication.getInstance().getApplicationContext(), mDatas, R.layout.dialog_item){
            @Override
            public void convert(ViewHolder helper, final String item){
            	helper.setText(R.id.tv_id, item.split(",")[0]);
                helper.setText(R.id.tv_item, item.split(",")[1]);
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	id.setText(item.split(",")[0]);
                    	textview.setText(item.split(",")[1]);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        LinearLayout content = (LinearLayout)dialog_view.findViewById(R.id.content);
        content.addView(dialog_listview);
        //确定
        Button positiveButton = (Button)dialog_view.findViewById(R.id.positiveButton);
        positiveButton.setVisibility(View.GONE);
        //取消
        Button negativeButton = (Button)dialog_view.findViewById(R.id.negativeButton);
        negativeButton.setText("取消");
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
