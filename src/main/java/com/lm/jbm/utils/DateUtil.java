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
	
	public static String getFormatDate(String format,Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 当前月份
	 */
	public static int getMounth() {
		int m = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		m = calendar.get(Calendar.MONTH) + 1;
		return m;
	}
	/**
	 * 获取当前月的第几天
	 */
	public static int getDay() {
		int day = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	/**
	 * 高峰时段
	 */
	public static boolean checkSpecialTime() {
		try {
			Date now = new Date();
			String str1 = DateUtil.format2Str(now, "yyyy-MM-dd") + " 19:00:00";
			String str2 = DateUtil.format2Str(now, "yyyy-MM-dd") + " 23:59:59";
			Date d = DateUtil.parse(str1, "yyyy-MM-dd HH:mm:ss");
			Date d2 = DateUtil.parse(str2, "yyyy-MM-dd HH:mm:ss");
			if(now.after(d) && now.before(d2)) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	
	public static boolean checkFreeTime() {
		try {
			Date now = new Date();
			String str1 = DateUtil.format2Str(now, "yyyy-MM-dd") + " 01:00:00";
			String str2 = DateUtil.format2Str(now, "yyyy-MM-dd") + " 10:00:00";
			Date d = DateUtil.parse(str1, "yyyy-MM-dd HH:mm:ss");
			Date d2 = DateUtil.parse(str2, "yyyy-MM-dd HH:mm:ss");
			if(now.after(d) && now.before(d2)) {
				return true;
			}
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
		return false;
	}
	
	public static boolean checkWorkTime() {
		try {
			Date now = new Date();
			String str1 = DateUtil.format2Str(now, "yyyy-MM-dd") + " 06:00:00";
			String str2 = DateUtil.format2Str(now, "yyyy-MM-dd") + " 12:30:00";
			String str3 = DateUtil.format2Str(now, "yyyy-MM-dd") + " 13:30:00";
			String str4 = DateUtil.format2Str(now, "yyyy-MM-dd") + " 19:00:00";
			Date d = DateUtil.parse(str1, "yyyy-MM-dd HH:mm:ss");
			Date d2 = DateUtil.parse(str2, "yyyy-MM-dd HH:mm:ss");
			Date d3 = DateUtil.parse(str3, "yyyy-MM-dd HH:mm:ss");
			Date d4 = DateUtil.parse(str4, "yyyy-MM-dd HH:mm:ss");
			if(now.after(d) && now.before(d2) || (now.after(d3) && now.before(d4))) {
				return true;
			}
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
		return false;
	}
	
	public static long getTime() {
		long time = 0;
		try {
			time = new Date().getTime();
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
		return time;
	}
}
