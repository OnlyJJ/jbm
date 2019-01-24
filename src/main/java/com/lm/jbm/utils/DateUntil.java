package com.lm.jbm.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUntil {
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
}
