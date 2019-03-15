package com.lm.jbm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.utils.HttpUtils;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.PropertiesUtil;
import com.lm.jbm.utils.UserIPUtil;

public class CommonService {
	public static ConcurrentHashMap<String, String> serssionMap = new ConcurrentHashMap<String, String>(512);
	public static ConcurrentHashMap<String, Integer> RELOGIN_MAP = new ConcurrentHashMap<String, Integer>(512);
	
	public static final String C1 = PropertiesUtil.getValue("C1");
	public static final String U1 = PropertiesUtil.getValue("U1");
	public static final String U15 = PropertiesUtil.getValue("U15");
	public static final String U48 = PropertiesUtil.getValue("U48");
	public static final String U50 = PropertiesUtil.getValue("U50");
	
	public static List<String> qryHome(String pageNum) {
		JSONObject json = new JSONObject();
		JSONObject kind = new JSONObject();
		kind.put("a", 0);
		kind.put("b", 1);
		
		JSONObject page = new JSONObject();
		page.put("b", pageNum);
		page.put("c", "36");
		
		JSONObject deviceproperties = new JSONObject();
		deviceproperties.put("a", "V1818CT");
		deviceproperties.put("b", "unKnow");
		deviceproperties.put("c", "4F93017B-7314-4149-8293-4D477A819AF7");
		deviceproperties.put("d", 320);
		deviceproperties.put("e", 720);
		deviceproperties.put("f", 1436);
		deviceproperties.put("g", "vivo");
		deviceproperties.put("h", "WIFI");
		deviceproperties.put("i", "0");
		deviceproperties.put("j", "8");
		deviceproperties.put("k", "4.1.4");
		deviceproperties.put("l", "9c:99:a0:ab:81:90");
		deviceproperties.put("p", "com.jj.shows");
		deviceproperties.put("q", "daae8c4df719dae63e93d9c71febaa62");
		
		json.put("kind", kind);
		json.put("page", page);
		json.put("deviceproperties", deviceproperties);
		String str = json.toString();
		String strRes = HttpUtils.post3(C1, str, "116.22.44.8");
		JSONObject res = JsonUtil.strToJsonObject(strRes);
		
		List<String> ret = new ArrayList<String>();
		if(res != null) {
			JSONArray data = JsonUtil.strToJSONArray(res.getString("data"));
			if(data != null && data.size() >0) {
				for(int i=0; i<data.size(); i++) {
					JSONObject body = JsonUtil.strToJsonObject(data.getString(i));
					String roomId = body.getString("b");
					if(!StringUtils.isEmpty(roomId)) {
						ret.add(roomId);
					}
				}
			}
		}
		Collections.shuffle(ret);
		return ret;
	}
	
	public static String getSessionId(String userId) {
		String sessionId = serssionMap.get(userId);
		if(StringUtils.isEmpty(sessionId)) {
			sessionId = login(userId);
			if(sessionId != null && !StringUtils.isEmpty(sessionId)) {
				serssionMap.put(userId, sessionId);
			}
		}
		return sessionId;
	}
	
	/**
	 * 登录
	 * @param userId
	 * @return
	 */
	public static String login(String userId) {
		try {
			System.err.println("开始登录，userId="+userId);
			JSONObject json = new JSONObject();
			JSONObject userbaseinfo = new JSONObject();
			userbaseinfo.put("a", userId);
			userbaseinfo.put("b", "123456");
			userbaseinfo.put("m", "false");
			json.put("userbaseinfo", userbaseinfo);
			String str = json.toString();
			String ip = UserIPUtil.getIP(userId);
			String strRes = HttpUtils.post3(U1, str, ip);
			JSONObject res = JsonUtil.strToJsonObject(strRes);
			while(true) {
				if (res != null) {
					JSONObject session = JsonUtil.strToJsonObject(res.getString("session"));
					if (session != null && session.containsKey("b")) {
						RELOGIN_MAP.put(userId, 0);
						System.err.println("登录成功！");
						return session.get("b").toString();
					}
				}
				Thread.sleep(10000);
				int recount = 0;
				if(RELOGIN_MAP.containsKey(userId)) {
					recount = RELOGIN_MAP.get(userId);
				} 
				if(recount >10) {
					return null;
				}
				RELOGIN_MAP.put(userId, recount+1);
				login(userId);
			}
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static void sign(String userId, String sessionId) {
		 String ip = UserIPUtil.getIP(userId);
		JSONObject json = new JSONObject();
		JSONObject session = new JSONObject();
		session.put("b", sessionId);
		
		JSONObject userbaseinfo = new JSONObject();
		userbaseinfo.put("a", userId);
		userbaseinfo.put("j", ip);
		
		json.put("session", session);
		json.put("userbaseinfo", userbaseinfo);
		
		// 是否签到
		boolean flag = false;
		String res = HttpUtils.post3(U50, json.toString(), ip);
		if(StringUtils.isNotEmpty(res)) {
			JSONObject data = JsonUtil.strToJsonObject(res);
			if(data != null && data.containsKey("signinfovo")) {
				JSONObject ret = JsonUtil.strToJsonObject(data.getString("signinfovo"));
				String signFlag = ret.getString("e");
				if(signFlag.equalsIgnoreCase("n")) {
					flag = true;
				}
			}
		}
		if(flag) {
			System.err.println("签到：" + userId);
			HttpUtils.post3(U48, json.toString(), ip);
		}
	}
	
	public static int findOnline(String roomId) {
		try {
			JSONObject json = new JSONObject();
			JSONObject roomonlineinfo = new JSONObject();
			roomonlineinfo.put("b", roomId);
			JSONObject page = new JSONObject();
			page.put("b", "1");
			page.put("c", "50");
			json.put("roomonlineinfo", roomonlineinfo);
			json.put("page", page);
			String str = json.toString();
			String res = HttpUtils.post(U15, str);
			int real = 0;
			int total = 0;
			if(StringUtils.isNotEmpty(res)) {
				JSONObject data = JsonUtil.strToJsonObject(res);
				if(data != null) {
					if(data.containsKey("page")) {
						JSONObject pageData = JsonUtil.strToJsonObject(data.getString("page"));
						total = Integer.parseInt(pageData.getString("a"));
					}
					if(total >0) {
						if(data.containsKey("onlineuserinfo")) {
							JSONArray array = JsonUtil.strToJSONArray(data.getString("onlineuserinfo"));
							if(array != null && array.size() >0) {
								int size = array.size();
								for(int i=0; i<size; i++) {
									JSONObject obj = array.getJSONObject(i);
									String userId = obj.getString("a");
									if(userId.indexOf("robot") != -1 || userId.indexOf("pesudo") != -1) {
										break;
									}
									real++;
								}
							}
						}
					}
				}
			}
			LogUtil.log.info("房间：" + roomId + "，当前真实用户数：" + real);
			return real;
		} catch(Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
		return 0;
	}
}
