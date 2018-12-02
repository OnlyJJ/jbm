package com.lm.jbm.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static String format2Str(Date nowDate, String format) {
		SimpleDateFormat sdf =new SimpleDateFormat(format);
		return sdf.format(nowDate);
	}
	
	public static Date parse(String dateStr,String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStr);
	}
	
	public static Date addMinute(Date date,int n){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}
	
	public static Date addSecond(Date date,int n){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MILLISECOND, n);
		return cal.getTime();
	}
}
