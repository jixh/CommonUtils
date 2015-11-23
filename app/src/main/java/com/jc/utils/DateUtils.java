package com.jc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final String YYMMDD = "yyyy-MM-dd";

	public DateUtils(){
		throw new AssertionError();
	}

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

	public static String format(Date date){
		if (date == null)return "";
		SimpleDateFormat sdf = new SimpleDateFormat(YYMMDD);
		return sdf.format(date);
	}

	public static String format(long date){
		SimpleDateFormat sdf = new SimpleDateFormat(YYMMDD);
		return sdf.format(date);
	}

}
