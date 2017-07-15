package com.jktaihe.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */

public class DateUtils {

	private static SimpleDateFormat SDF_DAY = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat SDF_DAY_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm");

	private DateUtils(){
		throw new AssertionError();
	}

	/**
	 * @param date
	 * @return
	 */
	public static String formatYMD(Date date){
		if (date == null)return "";
		return SDF_DAY.format(date);
	}

	/**
	 * @param date
	 * @return
	 */
	public static String formatYMD(long date){
		return SDF_DAY.format(date);
	}

	/**
	 * get current time
	 * @return
	 */
	public static String getCurrentTime(SimpleDateFormat dateFormat) {
		return  SDF_DAY.format(System.currentTimeMillis());
	}

	/**
	 * @param date
	 * @return
	 */
	public static String getDay(String date){
		String str = "";
		try {
				str = SDF_DAY.format(SDF_DAY_TIME.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * @param date
	 * @return
	 */
	public static String getTime(String date){
		String str = "";
		try {
				str = SDF_TIME.format(SDF_DAY_TIME.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * @param date
	 * @param duringDay
	 * @return
	 */
	public static String getDate(String date, int duringDay){
		String result = "";
		try {
			Date d = SDF_DAY.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			calendar.add(Calendar.DAY_OF_MONTH,duringDay);
			result = formatYMD(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/***
	 * 根据时间获取星期
	 * @param
	 * @param
	 * @return
	 */
	private static final String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
	public static String getWeek(int year, int monthOfYear, int dayOfMonth) {
		Calendar c = Calendar.getInstance();
		c.set(year, monthOfYear, dayOfMonth);
		int index= c.get(Calendar.DAY_OF_WEEK);
		return weeks[index - 1];
	}


	public static String getDateAndWeek() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		String week = getWeek(year, month,day);
		return (month+1)+"月"+day+"日"+"\t"+week;
	}

	public static String getWeek() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		String week = getWeek(year, month,day);
		return week;
	}

	public static int[] getYMD() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int[] datas = new int[3];
		datas[0] = year;
		datas[1] = month + 1;
		datas[2] = day;
		return datas;
	}



	/**
	 * @param date
	 * @return
	 */
	public static boolean isToDay(String date){
		boolean isCurrent = false;
		try {
			isCurrent = formatYMD(System.currentTimeMillis()).equals(formatYMD(SDF_DAY.parse(date)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isCurrent;
	}

	/**
	 * @param date
	 * @return
	 */
	public static boolean isDefaultDateFormat(String date){
		try {
			SDF_DAY.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @param time
	 * @return
	 */
	public static boolean isDefaultTimeFormat(String time){
		try {
			SDF_TIME.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int compareDate(String beginDate, String endDate){
		int w = 0;

		if(!TextUtils.isEmpty(beginDate) && !TextUtils.isEmpty(endDate)){

			if (endDate.equals(beginDate))return 0;

			try {
				w = SDF_DAY.parse(endDate).getTime() > SDF_DAY.parse(beginDate).getTime()
						? 1	: -1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return w;
	}

	public static int dateCompare(String s1,String s2) {
		//设定时间的模板
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//得到指定模范的时间
		Date d1 = null;
		try {
			d1 = sdf.parse(s1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = sdf.parse(s2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//比较
		long d = d1.getTime() - d2.getTime();
		if (d > 0) {
			return 1;
		} else if (d == 0) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * 日期间隔
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int betweenSecond(String beginDate,String endDate){
		int w = 0;
		if(!TextUtils.isEmpty(beginDate) && !TextUtils.isEmpty(endDate)){
			try {
				w = (int) ((SDF_DAY_TIME.parse(endDate).getTime() - SDF_DAY_TIME.parse(beginDate).getTime())/(1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return w;
	}
	/**
	 * 日期间隔
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int betweenMin(String beginDate,String endDate){
		int w = 0;
		if(!TextUtils.isEmpty(beginDate) && !TextUtils.isEmpty(endDate)){
			try {
				w = (int) ((SDF_DAY_TIME.parse(endDate).getTime() - SDF_DAY_TIME.parse(beginDate).getTime())/(1000*60));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return w;
	}

	/**
	 * 日期间隔
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int betweenHour(String beginDate,String endDate){
		int w = 0;
		if(!TextUtils.isEmpty(beginDate) && !TextUtils.isEmpty(endDate)){
			try {
				w = (int) ((SDF_DAY_TIME.parse(endDate).getTime() - SDF_DAY_TIME.parse(beginDate).getTime())/(1000*60*60));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return w;
	}


	/**
	 * 日期间隔
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int betweenWeeks(String beginDate,String endDate){
		int w = 0;
		if(!TextUtils.isEmpty(beginDate) && !TextUtils.isEmpty(endDate)){
			try {
				w = (int) ((SDF_DAY_TIME.parse(endDate).getTime() - SDF_DAY_TIME.parse(beginDate).getTime())/(1000*60*60*24*7));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return w;
	}

	/**
	 * 日期间隔
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int betweenDays(String beginDate,String endDate){
		int w = 0;
		if(!TextUtils.isEmpty(beginDate) && !TextUtils.isEmpty(endDate)){
			try {
				w = (int) ((SDF_DAY_TIME.parse(endDate).getTime() - SDF_DAY_TIME.parse(beginDate).getTime())/(1000*60*60*24));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return w;
	}
}
