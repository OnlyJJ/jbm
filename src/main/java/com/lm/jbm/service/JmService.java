package com.lm.jbm.service;


import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.thread.PeachThread;
import com.lm.jbm.thread.ThreadManager;
import com.lm.jbm.utils.HttpUtils;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.RandomUtil;


public class JmService {

//	public static final String U1 = "http://testservice.9shows.com/U1/0/";
//	public static final String U16 = "http://testservice.9shows.com/U16/0/";
//	public static final String U53 = "http://testservice.9shows.com/U53/0/";
	
	public static final String U1 = "http://service.9shows.com/U1/0/";
	public static final String U16 = "http://service.9shows.com/U16/0/";
	public static final String U53 = "http://service.9shows.com/U53/0/";

	public static String login(String userId, String pwd, String ip) {
		try {
			JSONObject json = new JSONObject();
			JSONObject userbaseinfo = new JSONObject();
			userbaseinfo.put("a", userId);
			userbaseinfo.put("b", pwd);
			userbaseinfo.put("j", ip);
			json.put("userbaseinfo", userbaseinfo);
			String str = json.toString();
			String strRes = HttpUtils.post(U1, str);
			JSONObject res = JsonUtil.strToJsonObject(strRes);
			String sessionId = null;
			if (res != null) {
				JSONObject session = JsonUtil.strToJsonObject(res
						.getString("session"));
				if (session != null && session.containsKey("b")) {
					sessionId = session.get("b").toString();
				}
			}
			return sessionId;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static String inRoom(String roomId, String userId) {
		try {
			JSONObject json = new JSONObject();
			JSONObject roomonlineinfo = new JSONObject();
			roomonlineinfo.put("a", 1);
			roomonlineinfo.put("b", roomId);
			JSONObject onlineUserInfo = new JSONObject();
			onlineUserInfo.put("a", userId);
			roomonlineinfo.put("c", onlineUserInfo);
			json.put("roomonlineinfo", roomonlineinfo);
			String str = json.toString();
			return HttpUtils.post(U16, str);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static String pluck(String roomId, String userId, String sessionId, String ip) {
		try {
			JSONObject json = new JSONObject();
			JSONObject session = new JSONObject();
			session.put("b", sessionId);
			
			JSONObject userbaseinfo = new JSONObject();
			userbaseinfo.put("a", userId);
			userbaseinfo.put("j", ip);
			
			JSONObject peachvo = new JSONObject();
			peachvo.put("b", roomId);
			json.put("session", session);
			json.put("userbaseinfo", userbaseinfo);
			json.put("peachvo", peachvo);
			
			String str = json.toString();
			String res = HttpUtils.post3(U53, str, ip);
			if(StringUtils.isNotEmpty(res)) {
				JSONObject data = JsonUtil.strToJsonObject(res);
				if(data != null && data.containsKey("peachvo")) {
					JSONObject peachvoJson = JsonUtil.strToJsonObject(data.getString("peachvo"));
					int num = peachvoJson.getIntValue("l");
					String name = peachvoJson.getString("m");
					StringBuilder msg = new StringBuilder();
					msg.append(userId).append("摘到：").append(name).append("X").append(num).append("个");
					System.err.println(msg.toString());
					return msg.toString();
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return "";
	}
	
	public static void peach(String roomId) {
		try {
			String[] userIds = RandomUtil.getUserIds();
			int index = 1;
			for(int i=0; i<userIds.length; i++) {
				if(index > RandomUtil.getTotal()) {
					return;
				}
				String userId = userIds[i];
				PeachThread peach = new PeachThread(roomId, userId);
				ThreadManager.getInstance().execute(peach);
				index++;
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
