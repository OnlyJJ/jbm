package com.lm.jbm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.utils.DevUtil;
import com.lm.jbm.utils.HttpUtils;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.PropertiesUtil;
import com.lm.jbm.utils.QQInfoUtil;
import com.lm.jbm.utils.UserIPUtil;

public class CommonService {
	public static ConcurrentHashMap<String, String> serssionMap = new ConcurrentHashMap<String, String>(512);
	public static ConcurrentHashMap<String, Integer> RELOGIN_MAP = new ConcurrentHashMap<String, Integer>(512);
	
	public static final String C1 = PropertiesUtil.getValue("C1");
	public static final String U1 = PropertiesUtil.getValue("U1");
	public static final String U15 = PropertiesUtil.getValue("U15");
	public static final String U29 = PropertiesUtil.getValue("U29");
	public static final String U48 = PropertiesUtil.getValue("U48");
	public static final String U50 = PropertiesUtil.getValue("U50");
	
	public static List<String> getRoom(String userId, String pageNum) {
		try {
			List<String> rooms = CommonService.qryHome(userId, pageNum);
			while(true) {
				if(rooms != null && rooms.size() > 0) {
					return rooms;
				}
				LogUtil.log.info("## 未获取到首页数据，睡眠2S，重新获取。。。");
				Thread.sleep(2000);
				String num = String.valueOf((Integer.parseInt(pageNum) + 1));
				getRoom(userId, num);
			}
		} catch(Exception e) {
			
		}
		return null;
	}
	
	private static List<String> qryHome(String userId, String pageNum) {
		JSONObject json = new JSONObject(true);
		JSONObject kind = new JSONObject(true);
		kind.put("a", 0);
		kind.put("b", 1);
		
		JSONObject page = new JSONObject(true);
		page.put("b", pageNum);
		page.put("c", "36");
		
		json.put("kind", kind);
		json.put("page", page);
		json.put("deviceproperties", DevUtil.getDevInfo(userId));
		String str = json.toString();
		String strRes = HttpUtils.post3(userId, C1, str, UserIPUtil.getIP(userId));
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
	 * 账号密码登录
	 * @param userId
	 * @return
	 */
	public static String login(String userId) {
		String sessionId = "";
		try {
			JSONObject json = new JSONObject(true);
			JSONObject userbaseinfo = new JSONObject(true);
			userbaseinfo.put("a", userId);
			userbaseinfo.put("b", "123456");
			userbaseinfo.put("m", "false");
			json.put("userbaseinfo", userbaseinfo);
			String str = json.toString();
			String ip = UserIPUtil.getIP(userId);
			String strRes = HttpUtils.post3(userId, U1, str, ip);
			JSONObject res = JsonUtil.strToJsonObject(strRes);
			if (res != null) {
				JSONObject session = JsonUtil.strToJsonObject(res.getString("session"));
				if (session != null && session.containsKey("b")) {
					sessionId = session.get("b").toString();
					serssionMap.put(userId, sessionId);
					LogUtil.log.info("登录成功！userId：" +userId);
					System.out.println("登录成功，userId = " + userId + ",sessionid = " + sessionId);
				}
			}
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
		return sessionId;
	}
	
	/**
	 * qq登录
	 */
	public static String qqLogin(String userId) {
		String sessionId = "";
		try {
			JSONObject json = new JSONObject(true);
			JSONObject code = new JSONObject();
			code.put("a", QQInfoUtil.getCode(userId));
			json.put("code", code);
			json.put("qqconnectuserinfovo", QQInfoUtil.getQQInfo(userId));
			json.put("deviceproperties", DevUtil.getDevInfo(userId));
			String str = json.toString();
			String ip = UserIPUtil.getIP(userId);
			String strRes = HttpUtils.post3(userId, U29, str, ip);
			JSONObject res = JsonUtil.strToJsonObject(strRes);
			while(true) {
				if (res != null) {
					JSONObject session = JsonUtil.strToJsonObject(res.getString("session"));
					if (session != null && session.containsKey("b")) {
						RELOGIN_MAP.put(userId, 0);
						sessionId = session.get("b").toString();
						LogUtil.log.info("登录成功！userId：" +userId);
						serssionMap.put(userId, sessionId);
					}
				}
				Thread.sleep(10000);
				int recount = 0;
				if(RELOGIN_MAP.containsKey(userId)) {
					recount = RELOGIN_MAP.get(userId);
				} 
				if(recount >10) {
					LogUtil.log.info("qq登录失败，重试次数：" + recount + ",userId = " + userId + ",不再尝试登录！");
					return null;
				}
				RELOGIN_MAP.put(userId, recount+1);
				qqLogin(userId);
			}
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
		return sessionId;
	}
	
	public static void sign(String userId, String sessionId) {
		String ip = UserIPUtil.getIP(userId);
		JSONObject json = new JSONObject(true);
		JSONObject session = new JSONObject();
		session.put("b", sessionId);
		
		JSONObject userbaseinfo = new JSONObject(true);
		userbaseinfo.put("a", userId);
		userbaseinfo.put("m", "false");
		
		json.put("userbaseinfo", userbaseinfo);
		json.put("session", session);
		json.put("deviceproperties", DevUtil.getDevInfo(userId));
		
		// 是否签到
		boolean flag = false;
		String res = HttpUtils.post3(userId, U50, json.toString(), ip);
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
			LogUtil.log.info("成功签到：" + userId);
			HttpUtils.post3(userId, U48, json.toString(), ip);
		} else {
			LogUtil.log.info("已签到，不重复签到！");
		}
	}
	
	/**
	 * 查询当前房间真实用户数
	 */
	public static int findOnline(String userId, String roomId) {
		try {
			JSONObject json = new JSONObject(true);
			JSONObject roomonlineinfo = new JSONObject(true);
			roomonlineinfo.put("b", roomId);
			JSONObject page = new JSONObject(true);
			page.put("b", "1");
			page.put("c", "50");
			json.put("roomonlineinfo", roomonlineinfo);
			json.put("page", page);
			json.put("deviceproperties", DevUtil.getDevInfo(userId));
			
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
									String user = obj.getString("a");
									if(user.indexOf("robot") != -1 || user.indexOf("pesudo") != -1) {
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
