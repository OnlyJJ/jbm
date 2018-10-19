package com.lm.jbm.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUntil {
	/**
	 * 当前月份
	 * @author Shao.x
	 * @date 2018年10月19日
	 * @return
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
	 * @author Shao.x
	 * @date 2018年10月19日
	 * @return
	 */
	public static int getDay() {
		int day = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}
}
