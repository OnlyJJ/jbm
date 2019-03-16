package com.lm.jbm.service;



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


public class RedPackService extends CommonService {
	
	public static final String U32 = PropertiesUtil.getValue("U32");
	
	public static void grapReb(String roomId, String userId, String sessionId, String rebId) {
		try {
			String ip = UserIPUtil.getIP(userId);
			JSONObject json = new JSONObject(true);
			JSONObject session = new JSONObject();
			session.put("b", sessionId);
			
			JSONObject userbaseinfo = new JSONObject(true);
			userbaseinfo.put("a", userId);
			userbaseinfo.put("m", "false");
			
			JSONObject redpacketsendvo = new JSONObject(true);
			long time = DateUtil.getTime();
			redpacketsendvo.put("a", rebId);
			redpacketsendvo.put("m", 0);
			redpacketsendvo.put("p", Md5CommonUtils.getMD5String(Md5CommonUtils.getMD5String(userId + roomId) + time));
			redpacketsendvo.put("q", roomId);
			redpacketsendvo.put("r", time);
			
			json.put("userbaseinfo", userbaseinfo);
			json.put("session", session);
			json.put("redpacketsendvo", redpacketsendvo);
			json.put("deviceproperties", DevUtil.getDevInfo(userId));
			
			String str = json.toString();
			String res = HttpUtils.post3(userId, U32, str, ip);
			if(StringUtils.isNotEmpty(res)) {
				JSONObject data = JsonUtil.strToJsonObject(res);
				if(data != null && data.containsKey("redpacketreceivevo")) {
					JSONObject peachvoJson = JsonUtil.strToJsonObject(data.getString("redpacketreceivevo"));
					int gold = peachvoJson.getIntValue("d");
					StringBuilder msg = new StringBuilder();
					msg.append(userId).append("抢红包，抢到：").append("X").append(gold).append("个金币");
					System.err.println(msg.toString());
				}
			}
		} catch(Exception e) {
			System.err.println("抢红包异常！");
			LogUtil.log.error(e.getMessage(), e);
		}
	}
	
}
