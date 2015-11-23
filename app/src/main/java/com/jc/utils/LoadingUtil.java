package com.jc.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.hq.shell.R;
import com.hq.shell.activity.MyApplication;
import com.jc.commonutils.R;

public class LoadingUtil{
    View view = null;
    AnimationDrawable animationDrawable = null;
    LayoutInflater inflater;

    public LoadingUtil(){
        inflater = LayoutInflater.from(CommonUtils.context);
        view = inflater.inflate(R.layout.loading, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.image_loading);

        imageView.setBackgroundResource(R.drawable.drawable_anim);

        // 获取AnimationDrawable对象
        animationDrawable = (AnimationDrawable)imageView.getBackground();
    }

    public View startLoading(){
        // 动画是否正在运行
        if(animationDrawable.isRunning()){
            //停止动画播放
            animationDrawable.stop();
            animationDrawable.start();
        }else{
            //开始或者继续动画播放
            animationDrawable.start();
        }
        return view;
    }

    public void endLoading(){
        if(animationDrawable!=null){
            if(animationDrawable.isRunning()){
                animationDrawable.stop();
                view.clearAnimation();
            }
        }
    }
    
    public View error(String msg){
         View error_view = inflater.inflate(R.layout.error, null);
         TextView textview_msg = (TextView)error_view.findViewById(R.id.msg);
         textview_msg.setText(msg);
         return error_view;
    }
    
    //旋转
	public View rotate(Context context,View view){
        ImageView loading = (ImageView)view.findViewById(R.id.image_loading);
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.tip);
        LinearInterpolator lin = new LinearInterpolator();
        anim.setInterpolator(lin);

        if (anim != null) {
            loading.startAnimation(anim);
        }

        return view;
    }
}
