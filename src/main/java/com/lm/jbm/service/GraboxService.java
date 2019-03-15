package com.lm.jbm.service;


import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.utils.DevUtil;
import com.lm.jbm.utils.HttpUtils;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.Md5CommonUtils;
import com.lm.jbm.utils.PropertiesUtil;
import com.lm.jbm.utils.UserIPUtil;


public class GraboxService extends CommonService {
	
	public static final String G47 = PropertiesUtil.getValue("G47");
	public static final String G48 = PropertiesUtil.getValue("G48");
	
	/**
	 * 查询房间当前宝箱
	 * @param roomId
	 * @param userId
	 * @return
	 */
	public static JSONObject qyrBox(String roomId, String userId) {
		JSONObject resp = new JSONObject();
		try {
			String ip = UserIPUtil.getIP(userId);
			JSONObject json = new JSONObject();
			
			JSONObject anchorinfo = new JSONObject();
			anchorinfo.put("b", roomId);
			
			json.put("deviceproperties", DevUtil.getDevInfo(userId));
			json.put("anchorinfo", anchorinfo);
			
			String str = json.toString();
			String res = HttpUtils.post3(G47, str, ip);
			System.err.println("查询宝箱信息：" + res);
			if(StringUtils.isNotEmpty(res)) {
				JSONObject data = JsonUtil.strToJsonObject(res);
				if(data != null && data.containsKey("grabboxvo")) {
					JSONObject ret = JsonUtil.strToJsonObject(data.getString("grabboxvo"));
					int boxId = ret.getIntValue("a");
					long time = ret.getLongValue("e");
					resp.put("boxId", boxId);
					resp.put("currenttime", time);
				}
			}
		} catch(Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
		return resp;
	}
	
	public static void grapBox(String roomId, String sessionId, String userId) {
		try {
			JSONObject boxInfo = qyrBox(roomId, userId);
			if(!boxInfo.containsKey("boxId") || !boxInfo.containsKey("currenttime")) {
				return;
			}
			int boxId = boxInfo.getIntValue("boxId");
			long currenttime = boxInfo.getLongValue("currenttime");
			
			String ip = UserIPUtil.getIP(userId);
			JSONObject json = new JSONObject();
			JSONObject session = new JSONObject();
			session.put("b", sessionId);
			
			JSONObject userbaseinfo = new JSONObject();
			userbaseinfo.put("a", userId);
			userbaseinfo.put("m", "false");
			
			JSONObject anchorinfo = new JSONObject();
			anchorinfo.put("b", roomId);
			
			json.put("session", session);
			json.put("userbaseinfo", userbaseinfo);
			json.put("anchorinfo", anchorinfo);
			json.put("deviceproperties", DevUtil.getDevInfo(userId));
			
			JSONObject grabboxvo = new JSONObject();
			String realStr = Md5CommonUtils.getMD5String(Md5CommonUtils.getMD5String(userId+boxId+currenttime) + boxId);
			grabboxvo.put("b", realStr);
			json.put("grabboxvo", grabboxvo);
			String str = json.toString();
			String res = HttpUtils.post3(G48, str, ip);
			if(StringUtils.isNotEmpty(res)) {
				JSONObject data = JsonUtil.strToJsonObject(res);
				if(data != null && data.containsKey("grabboxvo")) {
					JSONObject ret = JsonUtil.strToJsonObject(data.getString("grabboxvo"));
					int num = ret.getIntValue("h");
					String name = ret.getString("f");
					StringBuilder msg = new StringBuilder();
					msg.append(userId).append("抢宝箱，抢到：").append(name).append("X").append(num).append("个");
					System.err.println(msg.toString());
				}
			}
		} catch(Exception e) {
			System.err.println("抢宝箱异常！");
			LogUtil.log.error(e.getMessage(), e);
		}
	}
	
}