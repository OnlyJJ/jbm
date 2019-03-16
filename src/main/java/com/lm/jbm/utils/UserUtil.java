package com.lm.jbm.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

public class UserUtil {
	
	/**
	 * 分时段分配在线用户map
	 */
	public static Map<String, String> USERONLINE_MAP = null;
	/**
	 * 当前登录的用户map
	 */
	public static ConcurrentHashMap<String, Integer> LOGINUSER_MAP = new ConcurrentHashMap<String, Integer>(512);
	
	/**
	 * 校验用户是否在当前时段有效，如果过期，则应该重新登录新用户
	 * @param userId
	 * @return true-过期，false-未过期
	 */
	public static boolean checkValid(String userId) {
		boolean flag = false;
		String nowStr = DateUtil.getFormatDate("HH", new Date());
		String time = "";
		if(nowStr.indexOf("0") > -1) {
			time = nowStr.substring(1);
		} else {
			time = nowStr;
		}
		int now = Integer.parseInt(time);
		if(LOGINUSER_MAP.containsKey(userId)) {
			int end = LOGINUSER_MAP.get(userId);
			if(now > end) {
				flag = true;
			}
		}else {
			LOGINUSER_MAP.put(userId, now);
		}
		return flag;
	}
	
	/**
	 * 获取新用户，用户登录监听
	 * @return
	 */
	public static String getUser() {
		String userId = "";
		String now = DateUtil.getFormatDate("HH", new Date());
		String time = "";
		if(now.indexOf("0") > -1) {
			time = now.substring(1);
		} else {
			time = now;
		}
		int timeKey = Integer.parseInt(time);
		for(String key : USERONLINE_MAP.keySet()) {
			String interval = USERONLINE_MAP.get(key);
			int begin = Integer.parseInt(interval.split("-")[0]);
			int end = Integer.parseInt(interval.split("-")[1]);
			if(timeKey >= begin && timeKey < end) {
				if(LOGINUSER_MAP.containsKey(key)) {
					continue;
				}
				LOGINUSER_MAP.put(key, end);
			}
		}
		return userId;
	}
	
	/**
	 * 移除已登录的用户
	 * @param userId
	 */
	public static void removeUser(String userId) {
		if(StringUtils.isEmpty(userId)) {
			return;
		}
		if(LOGINUSER_MAP == null) {
			return;
		}
		LOGINUSER_MAP.remove(userId);
	}

	/**
	 * 服务启动时，随机取的登录监听用户（多个）
	 * @param num 个数
	 * @return
	 */
	public static List<String> getUsers(int num) {
		if(USERONLINE_MAP == null) {
			init();
		}
		String now = DateUtil.getFormatDate("HH", new Date());
		String time = "";
		if(now.indexOf("0") > -1) {
			time = now.substring(1);
		} else {
			time = now;
		}
		int timeKey = Integer.parseInt(time);
		List<String> ret = new ArrayList<String>();
		if(num >0) {
			int endIndex = 0;
			for(String key : USERONLINE_MAP.keySet()) {
				String interval = USERONLINE_MAP.get(key);
				int begin = Integer.parseInt(interval.split("-")[0]);
				int end = Integer.parseInt(interval.split("-")[1]);
				if(timeKey >= begin && timeKey < end) {
					if(endIndex >= num) {
						break;
					}
					LOGINUSER_MAP.put(key, end);
					ret.add(key);
					endIndex++;
				}
			}
		}
		System.err.println("取到的时间段监听用户：" + ret.toString());
		return ret;
	}
	
	private static synchronized void init() {
		USERONLINE_MAP = new HashMap<String, String>(256);
		List<String> users = RandomUtil.getUserIds("listener");
		Collections.shuffle(users);
		if(users != null && users.size() >0) {
			int begin = 0;
			int end = 2;
			for(String userId : users) {
				if(2 == end) {
					begin = 8;
					end = 10;
				} else if(24 == end) {
					begin = 0;
					end = 2;
				} else {
					end += 2;
					begin += 2;
				}
				USERONLINE_MAP.put(userId, String.valueOf(begin + "-" + end));
			}
		}
	}
	
	public static void main(String[] args) {
		String now = DateUtil.getFormatDate("HH", new Date());
		String time = "";
		if(now.indexOf("0") > -1) {
			time = now.substring(1);
		} else {
			time = now;
		}
		System.err.println(time);
	}

}
