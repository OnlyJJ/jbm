package com.lm.jbm.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserTypeUtil {
	/**
	 * 用户类型，qq/手机
	 */
	public static Map<String, Integer> USERTYPE_MAP = null;

	/**
	 * 获取用户类型，1-QQ用户，2-手机注册用户，3-账号密码用户
	 */
	public static synchronized Integer getUserType(String userId) {
		if(USERTYPE_MAP == null) {
			init();
		}
		return USERTYPE_MAP.get(userId);
	}
	
	public static void init() {
		USERTYPE_MAP = new ConcurrentHashMap<String, Integer>(256);
		USERTYPE_MAP.put("153706", 3);
		USERTYPE_MAP.put("276641", 3);
			
	}
	
}
