package com.jktaihe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */

public class DateUtils {

	public static final String YYMMDD = "yyyy-MM-dd";
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private DateUtils(){
		throw new AssertionError();
	}

	/**
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int betweenWeeks(String beginDate,String endDate){

		int w = 0;
		if(!beginDate.equals("") && !beginDate.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat(YYMMDD);
			try {
				w = (int) ((sdf.parse(endDate).getTime() - sdf.parse(beginDate).getTime())/(1000*60*60*24*7));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return w;
	}

	/**
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int betweenDays(String beginDate,String endDate){
		int w = 0;
		if(!beginDate.equals("") && !beginDate.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat(YYMMDD);
			try {
				w = (int) ((sdf.parse(endDate).getTime() - sdf.parse(beginDate).getTime())/(1000*60*60*24));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return w;
	}

	/**
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		if (date == null)return "";
		SimpleDateFormat sdf = new SimpleDateFormat(YYMMDD);
		return sdf.format(date);
	}

	/**
	 * @param date
	 * @return
	 */
	public static String format(long date){
		SimpleDateFormat sdf = new SimpleDateFormat(YYMMDD);
		return sdf.format(date);
	}

	/**
	 * long time to string
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 *
	 * @param timeInMillis
	 * @return
	 */
	public static String getTime(long timeInMillis) {
		return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
	}

	/**
	 * get current time in milliseconds
	 *
	 * @return
	 */
	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}

	/**
	 * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
	 *
	 * @return
	 */
	public static String getCurrentTimeInString() {
		return getTime(getCurrentTimeInLong());
	}

	/**
	 * get current time in milliseconds
	 *
	 * @return
	 */
	public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
		return getTime(getCurrentTimeInLong(), dateFormat);
	}
}
