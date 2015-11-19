package com.jc.utils;

import java.util.Date;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.hq.shell.R;
import com.hq.shell.activity.MainActivity;
import com.hq.shell.activity.MyApplication;
import com.hq.shell.activity.PushActivity;
import com.jc.commonutils.R;

public final class MyNotification {
	
	/**
	* 添加顶部通知
	*/
	public static void addNotification(String type,String title,String text){
//		WakeLockUtil.acquireWakeLock();
		
		//添加通知到顶部任务栏
		Context context = MyApplication.getInstance().getApplicationContext();
	    //获得NotificationManager实例
	    String service = Context.NOTIFICATION_SERVICE;
	    NotificationManager nm = (NotificationManager)(context.getSystemService(service));
	    //实例化Notification
	    Notification n = new Notification();
	    //设置显示图标
	    int icon = R.drawable.ic_launcher;
	    //设置提示信息
	    String tickerText = title;
	    //显示时间
	    long when = System.currentTimeMillis(); 
	    n.icon = icon;
	    n.tickerText = tickerText;
	    n.when = when;
	    n.flags = Notification.FLAG_AUTO_CANCEL;
	    n.defaults = Notification.DEFAULT_SOUND;  
//	    n.sound = Uri.parse("android.resource://com.hq.alarmforedc/raw/music");
	    
	    //实例化Intent
//	    Intent intent = new Intent(MyApplication.getInstance().getApplicationContext(), PushActivity.class);  
//	    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED); 
	    
	    //获得PendingIntent
	    PendingIntent pi = null;
	    pi = PendingIntent.getActivities(context, 0, makeIntentStack(type), 0);
	    /*
	    if(checkMainActivity()){
	    	pi = PendingIntent.getActivity(context, 0, intent, 0);
	    }else{
	    	pi = PendingIntent.getActivities(context, 0, makeIntentStack(type), 0);
	    }
	    */
	    //设置事件信息，显示在拉开的里面
	    n.setLatestEventInfo(context, title,text, pi);
	    //发出通知
	    nm.notify((int) (new Date()).getTime(),n);//第一个参数值保持每次不一样，这样就会出现多个通知
//	    nm.notify(0,n);
	}
	
	static Intent[] makeIntentStack(String type) {
		Intent[] intents = new Intent[2];
		intents[0] = Intent.makeRestartActivityTask(new ComponentName(MyApplication.getInstance().getApplicationContext(),MainActivity.class));
//		if(type.equals("Question")){
//			intents[1] = new Intent(MyApplication.getInstance().getApplicationContext(),QuestionDetailActivity.class);
//		}else{
			intents[1] = new Intent(MyApplication.getInstance().getApplicationContext(),PushActivity.class);
//		}
		return intents;
	}
	
	private static boolean checkMainActivity() {
		Log.i("MyNotification", "---checkMainActivity---");
		Intent intent = new Intent(MyApplication.getInstance().getApplicationContext(), MainActivity.class);
		ComponentName cmpName = intent.resolveActivity(MyApplication.getInstance().getApplicationContext().getPackageManager());
		boolean isExist = false;
		if (cmpName != null) { // 说明系统中存在这个activity
			ActivityManager am = (ActivityManager)MyApplication.getInstance().getApplicationContext().getSystemService(MyApplication.getInstance().getApplicationContext().ACTIVITY_SERVICE);
			List<RunningTaskInfo> taskInfoList = am.getRunningTasks(100);
			for (RunningTaskInfo taskInfo : taskInfoList) {
				if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
					isExist = true;
					break;
				}
			}
		}
		Toast.makeText(MyApplication.getInstance().getApplicationContext(), String.valueOf(isExist), Toast.LENGTH_SHORT).show();
		return isExist;
	}

}
