package com.lm.jbm.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.utils.DateUtil;
import com.lm.jbm.utils.DevUtil;
import com.lm.jbm.utils.HttpUtils;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.Md5CommonUtils;
import com.lm.jbm.utils.PropertiesUtil;
import com.lm.jbm.utils.UserIPUtil;


public class PeachService extends CommonService {
	public static ConcurrentHashMap<String, List<Integer>> SLEEP_MAP = new ConcurrentHashMap<String, List<Integer>>(512);
	public static ConcurrentHashMap<String, String> pluckRecordMap = new ConcurrentHashMap<String, String>(512);
	public static ConcurrentHashMap<String, String> peachMap = new ConcurrentHashMap<String, String>(512);
	public static ConcurrentHashMap<String, Integer> recordMap = new ConcurrentHashMap<String, Integer>(512);
	public static ConcurrentHashMap<String, Integer> pluckMap = new ConcurrentHashMap<String, Integer>(512);
	public static ConcurrentHashMap<String, Integer> pluckCountMap = new ConcurrentHashMap<String, Integer>(512);
	public static int PLUCK_TOTAL = 0;
	
	public static final String U53 = PropertiesUtil.getValue("U53");
	
	public static synchronized int getSleepTime(String roomId) {
		int time = 4500;
		List<Integer> val = null;
		if(!SLEEP_MAP.containsKey(roomId) || SLEEP_MAP.get(roomId) == null
				|| SLEEP_MAP.get(roomId).size() <1) {
			val = new ArrayList<Integer>();
			int timeinterval = 1000;
			for(int i=0; i<5; i++) {
				val.add(time);
				if(i>=1) {
					timeinterval = 500;
				}
				time = time + timeinterval;
			}
			SLEEP_MAP.put(roomId, val);
		}
		val = SLEEP_MAP.get(roomId);
		if(val != null && val.size() >0) {
			time = val.get(0);
			val.remove(0);
			SLEEP_MAP.put(roomId, val);
		}
		LogUtil.log.info("当前取到的时间：" + time + "，剩余的：" + SLEEP_MAP.get(roomId).toString());
		return time;
	}

	public static String pluck(String roomId, String userId, String sessionId) {
		try {
			if(isPluck(userId)) {
				String ip = UserIPUtil.getIP(userId);
				JSONObject json = new JSONObject(true);
				JSONObject session = new JSONObject();
				session.put("b", sessionId);
				
				JSONObject userbaseinfo = new JSONObject(true);
				userbaseinfo.put("a", userId);
				userbaseinfo.put("m", "false");
				
				JSONObject peachvo = new JSONObject(true);
				long time = DateUtil.getTime();
				String realStr = Md5CommonUtils.getMD5String(Md5CommonUtils.getMD5String(userId + roomId) + time);
				peachvo.put("b", roomId);
				peachvo.put("s", realStr);
				peachvo.put("t", time);
				
				json.put("userbaseinfo", userbaseinfo);
				json.put("session", session);
				json.put("peachvo", peachvo);
				json.put("deviceproperties", DevUtil.getDevInfo(userId));
				String str = json.toString();
				String res = HttpUtils.post3(userId, U53, str, ip);
				if(StringUtils.isNotEmpty(res)) {
					JSONObject data = JsonUtil.strToJsonObject(res);
					if(data != null && data.containsKey("peachvo")) {
						JSONObject peachvoJson = JsonUtil.strToJsonObject(data.getString("peachvo"));
						int num = peachvoJson.getIntValue("l");
						String name = peachvoJson.getString("m");
						StringBuilder msg = new StringBuilder();
						msg.append(userId).append("摘到：").append(name).append("X").append(num).append("个");
						LogUtil.log.info(msg.toString());
						int total = num;
						int successCount = 1;
						if(pluckMap.containsKey(roomId)) {
							total += pluckMap.get(roomId); // 房间摘取量
						}
						PLUCK_TOTAL += num; // 全站一天总摘取量
						pluckMap.put(roomId, total);
						if(pluckCountMap.containsKey(roomId)) {
							successCount = pluckCountMap.get(roomId) + 1; // 每次摘成功次数
						}
						pluckCountMap.put(roomId, successCount);
						// 抢成功后，记录次数
						if(DateUtil.checkSpecialTime()) {
							int count = 1;
							if(recordMap.containsKey(userId)) {
								count = recordMap.get(userId) + 1;
							}
							recordMap.put(userId, count);
							LogUtil.log.info("高峰时段抢桃次数控制：userId：" + userId + "，已抢次数：" + count);
						}
					}
				}
				pluckRecordMap.put(userId, DateUtil.format2Str(DateUtil.addMinute(new Date(), 1), "yyyy-MM-dd HH:mm:ss"));
			}
		} catch(Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
		return "";
	}
	
	public static boolean isPluck(String userId) {
		try {
			if(pluckRecordMap.containsKey(userId)) {
				Date time = DateUtil.parse(pluckRecordMap.get(userId), "yyyy-MM-dd HH:mm:ss");
				if(new Date().before(time)) {
					return false;
				}
			}
			if(recordMap.containsKey(userId)) {
				int count = recordMap.get(userId);
				if(count >= 28) {
					return false;
				}
			}
		} catch(Exception e) {
			
		}
		return true;
	}
}
