package com.lm.jbm.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.service.JmService;
import com.lm.jbm.utils.PropertiesUtil;
import com.lm.jbm.utils.RandomUtil;




public class LoginThread implements Runnable {
	
	public static ConcurrentHashMap<String, String> serssionMap = new ConcurrentHashMap<String, String>(512);
	
	public static ConcurrentHashMap<String, String> ipMap = new ConcurrentHashMap<String, String>(512);
	
	public static ConcurrentHashMap<String, String> signMap = new ConcurrentHashMap<String, String>(512);

	public LoginThread() {}

	public void run() {
		while(true) {
			try {
				List<String> list = new ArrayList<String>();
				if(PropertiesUtil.getLevelUserIds(1) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(1));
				}
				if(PropertiesUtil.getLevelUserIds(2) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(2));
				}
				if(PropertiesUtil.getLevelUserIds(3) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(3));
				}
				if(PropertiesUtil.getLevelUserIds(4) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(4));
				}
				if(PropertiesUtil.getLevelUserIds(5) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(5));
				}
				if(PropertiesUtil.getLevelUserIds(6) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(6));
				}
				if(PropertiesUtil.getLevelUserIds(7) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(7));
				}
				for(String userId : list) {
					String ip = "";
					if(ipMap.contains(userId)) {
						ip = ipMap.get(userId);
					} else {
						ip = RandomUtil.getIp();
						ipMap.put(userId, ip);
					}
					String ret = JmService.login(userId, RandomUtil.getPwd(), ip);
					if(StringUtils.isNotEmpty(ret)) {
						serssionMap.put(userId, ret);
						if(!signMap.contains(userId)) {
							signMap.put(userId, "1");
							JmService.sign(userId, ret, ip);
							Thread.sleep(3000);
						}
					}
					Thread.sleep(5000);
				}
				Thread.sleep(1000*60*30);
			} catch (Exception e) {
				break;
			}
		}
	}

}
