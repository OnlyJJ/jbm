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
	public static List<String> LEVELUSER_1 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_2 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_3 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_4 = new ArrayList<String>(512);
	public static List<String> LEVELUSER_5 = new ArrayList<String>(512);
	public static List<String> FASEPEACHUSER_6 = new ArrayList<String>(512);
	public static List<String> NOT_INROOM_7 = new ArrayList<String>(512);
	
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
	 * @author Shao.x
	 * @date 2018年10月19日
	 * @param level
	 * @return
	 */
	public static List<String> getLevelUserIds(int level) {
		String key = "";
		List<String> list = null;
		switch(level) {
		case 1:
			key = "user_level_1";
			list = LEVELUSER_1;
			break;
		case 2:
			key = "user_level_2";
			list = LEVELUSER_2;
			break;
		case 3:
			key = "user_level_3";
			list = LEVELUSER_3;
			break;
		case 4:
			key = "user_level_4";
			list = LEVELUSER_4;
			break;
		case 5:
			key = "user_level_5";
			list = LEVELUSER_5;
			break;
		case 6:
			key = "fast_peach_user";
			list = FASEPEACHUSER_6;
			break;
		case 7:
			key = "user_no_inroom";
			list = NOT_INROOM_7;
			break;
		}
		if(list != null && list.size() >0) {
			Collections.shuffle(list);
			return list;
		}
		if(StringUtils.isNotEmpty(key)) {
			list = new ArrayList<String>();
			String group = PropertiesUtil.getValue(key);
			if(StringUtils.isNotEmpty(group)) {
				String[] userIds = group.split(",");
				List<String> tar = Arrays.asList(userIds);
				Collections.shuffle(tar);
				list =  tar;
			}
		}
		return list;
	}
}
