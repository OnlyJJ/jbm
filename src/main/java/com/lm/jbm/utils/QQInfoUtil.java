package com.lm.jbm.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QQInfoUtil {
	/**
	 * qq基本信息
	 */
	public static Map<String, Object> QQINFO_MAP = null;
	/**
	 * qq授权code，登录过期时，重新手动登录，并更新这里
	 */
	public static Map<String, String> CODE_MAP = new ConcurrentHashMap<String, String>();

	/**
	 * 获取用户设备全部信息
	 */
	public static synchronized Object getQQInfo(String userId) {
		if(QQINFO_MAP == null) {
			init();
		}
		return QQINFO_MAP.get(userId);
	}
	
	public static synchronized String getCode(String userId) {
		if(CODE_MAP.size() <1) {
			initcode();
		}
		return CODE_MAP.get(userId);
	}
	
	public static void init() {
		QQINFO_MAP = new ConcurrentHashMap<String, Object>(256);
		QQINFO_MAP.put("10051936", JsonUtil.strToJsonObject("{\"b\":\"爱可维\",\"c\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105171490\\/1969101C9D0220D95388BB9D8D57EADE\\/30\",\"d\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105171490\\/1969101C9D0220D95388BB9D8D57EADE\\/50\",\"e\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105171490\\/1969101C9D0220D95388BB9D8D57EADE\\/100\",\"f\":\"http:\\/\\/thirdqq.qlogo.cn\\/g?b=oidb&k=HGdSBSwUYngCRfmotaBYGw&s=40\",\"g\":\"http:\\/\\/thirdqq.qlogo.cn\\/g?b=oidb&k=HGdSBSwUYngCRfmotaBYGw&s=100\",\"h\":\"男\",\"i\":0,\"j\":0,\"k\":\"0\",\"l\":\"0\",\"m\":0,\"n\":\"1969101C9D0220D95388BB9D8D57EADE\",\"o\":\"福建\",\"p\":\"福州\"}"));
		QQINFO_MAP.put("", JsonUtil.strToJsonObject("{}"));
			
	}
	
	public static void initcode() {
		CODE_MAP.put("10051936", "EFF4829C8B9AE69088D7A35034A12F12");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
		CODE_MAP.put("", "");
	}
	
}
