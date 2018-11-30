package com.lm.jbm.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;


public final class PropertiesUtil {
	
	public static Properties pro;
	public static List<String> LEVELUSER_0 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_1 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_4 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_5 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_6 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_7 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_8 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_9 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_10 = new ArrayList<String>(512);
	public static List<String> FASEPEACHUSER_11 = new ArrayList<String>(512);
	public static List<String> NOT_INROOM_12 = new ArrayList<String>(512);
	
	public static void load(boolean dev) {
		try {
			pro = new Properties();
			if(dev) {
				pro.load(RandomUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			} else {
				pro.load(RandomUtil.class.getClassLoader().getResourceAsStream("config-test.properties"));
			}
			for(int i=1; i <8;i++) {
				getLevelUserIds(i);
			}
			System.err.println("当前环境：" + pro.getProperty("environment"));
		} catch (Exception e) {
		}
	}
	
	public static String getValue(String key) {
		if(StringUtils.isEmpty(key)) {
			return "";
		}
		return pro.getProperty(key);
	}
	
	/**
	 * 获取等级用户
	 * @param level 0:0级，1：1~3级，4:4级，5：5级，6:6级，7:7级，8:8级，9:9级，10:10级，11：快速摘桃,12:不需要进入房间
	 * @return
	 */
	public static List<String> getLevelUserIds(int level) {
		String key = "";
		List<String> list = null;
		switch(level) {
		case 0:
			key = "user_level_0";
			if(LEVELUSER_0 != null && LEVELUSER_0.size() >0) {
				Collections.shuffle(LEVELUSER_0);
			} else {
				list = getConfValue(key);
				LEVELUSER_0 = list;
			}
			break;
		case 1:
			key = "user_level_1";
			if(LEVELUSER_1 != null && LEVELUSER_1.size() >0) {
				Collections.shuffle(LEVELUSER_1);
			} else {
				list = getConfValue(key);
				LEVELUSER_1 = list;
			}
			break;
		case 4:
			key = "user_level_4";
			if(LEVELUSER_4 != null && LEVELUSER_4.size() >0) {
				Collections.shuffle(LEVELUSER_4);
			} else {
				list = getConfValue(key);
				LEVELUSER_4 = list;
			}
			break;
		case 5:
			key = "user_level_5";
			if(LEVELUSER_5 != null && LEVELUSER_5.size() >0) {
				Collections.shuffle(LEVELUSER_5);
			} else {
				list = getConfValue(key);
				LEVELUSER_5 = list;
			}
			break;
		case 6:
			key = "user_level_6";
			if(LEVELUSER_6 != null && LEVELUSER_6.size() >0) {
				Collections.shuffle(LEVELUSER_6);
			} else {
				list = getConfValue(key);
				LEVELUSER_6 = list;
			}
			break;
		case 7:
			key = "user_level_7";
			if(LEVELUSER_7 != null && LEVELUSER_7.size() >0) {
				Collections.shuffle(LEVELUSER_7);
			} else {
				list = getConfValue(key);
				LEVELUSER_7 = list;
			}
			break;
		case 8:
			key = "user_level_8";
			if(LEVELUSER_8 != null && LEVELUSER_8.size() >0) {
				Collections.shuffle(LEVELUSER_8);
			} else {
				list = getConfValue(key);
				LEVELUSER_8 = list;
			}
			break;
		case 9:
			key = "user_level_9";
			if(LEVELUSER_9 != null && LEVELUSER_9.size() >0) {
				Collections.shuffle(LEVELUSER_9);
			} else {
				list = getConfValue(key);
				LEVELUSER_9 = list;
			}
			break;
		case 10:
			key = "user_level_10";
			if(LEVELUSER_10 != null && LEVELUSER_10.size() >0) {
				Collections.shuffle(LEVELUSER_10);
			} else {
				list = getConfValue(key);
				LEVELUSER_10 = list;
			}
			break;
		case 11:
			key = "fast_peach_user";
			if(FASEPEACHUSER_11 != null && FASEPEACHUSER_11.size() >0) {
				Collections.shuffle(FASEPEACHUSER_11);
			} else {
				list = getConfValue(key);
				FASEPEACHUSER_11 = list;
			}
			break;
		case 12:
			key = "user_no_inroom";
			if(NOT_INROOM_12 != null && NOT_INROOM_12.size() >0) {
				Collections.shuffle(NOT_INROOM_12);
			} else {
				list = getConfValue(key);
				NOT_INROOM_12 = list;
			}
			break;
		}
		return list;
	}
	
	private static List<String> getConfValue(String key) {
		List<String> list = null;
		if(StringUtils.isNotEmpty(key)) {
			String group = PropertiesUtil.getValue(key);
			if(StringUtils.isNotEmpty(group)) {
				String[] userIds = group.split(",");
				list = Arrays.asList(userIds);
				Collections.shuffle(list);
			}
		}
		return list;
	}
}
