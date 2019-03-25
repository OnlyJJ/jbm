package com.lm.jbm.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MobileUserUtil {
	/**
	 * 账号-手机对应关系
	 */
	public static Map<String, String> MOBILE_MAP = null;

	/**
	 * 通过账号获取用户手机
	 */
	public static synchronized String getMobile(String userId) {
		if(MOBILE_MAP == null) {
			init();
		}
		return MOBILE_MAP.get(userId);
	}
	
	public static void init() {
		MOBILE_MAP = new ConcurrentHashMap<String, String>(256);
		MOBILE_MAP.put("1001", "");
			
	}
	
}
