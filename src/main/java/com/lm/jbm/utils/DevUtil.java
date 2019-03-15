package com.lm.jbm.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class DevUtil {

	public static Map<String, Object> DEV_MAP = null;

	public static Object getDevInfo(String userId) {
		if(DEV_MAP == null) {
			init();
		}
		return DEV_MAP.get(userId);
	}
	
	public static synchronized void init() {
		DEV_MAP = new ConcurrentHashMap<String, Object>(256);
		DEV_MAP.put("10074056", JsonUtil.strToJsonObject("{\"a\":\"LG-F600L\",\"b\":\"353541070129768\",\"c\":\"8D099202-8RF5-43C0-BDA1-3A59BE04F653\",\"d\":640,\"e\":1440,\"f\":2392,\"g\":\"yyb\",\"h\":\"WIFI\",\"i\":\"0\",\"j\":\"5.1.1\",\"k\":\"4.1.2\",\"l\":\"64:bc:0c:2f:1d:78\",\"p\":\"com.jj.shows\",\"q\":\"1a46c87041cc497f824a4ffa097fb012\"}"));
		DEV_MAP.put("10074052", JsonUtil.strToJsonObject("{\"a\":\"vivo Y67\",\"b\":\"460121846083170\",\"c\":\"8FD86594-DE1D-4B3D-9147-604EA1D6C266\",\"d\":320,\"e\":720,\"f\":1280,\"g\":\"jjg\",\"h\":\"WIFI\",\"i\":\"0\",\"j\":\"7.0\",\"k\":\"4.1.2\",\"l\":\"02:00:00:00:00:00\",\"p\":\"com.jj.shows\",\"q\":\"2a16c87941ec499tf824a4ffa097fb0a5\"}"));
		DEV_MAP.put("153706", JsonUtil.strToJsonObject("{\"a\":\"MHA-AL00\",\"b\":\"460010614110414\",\"c\":\"55A2944D-5EA8-4259-8EAC-40E8AFB3424B\",\"d\":480,\"e\":1080,\"f\":1808,\"g\":\"huawei\",\"h\":\"4G\",\"i\":\"0\",\"j\":\"8.0\",\"k\":\"4.1.2\",\"l\":\"02:00:00:00:00:00\",\"p\":\"com.jj.shows\",\"q\":\"4a46c87941cc499f824a4ffa097fb0b9\"}"));
	}
}
