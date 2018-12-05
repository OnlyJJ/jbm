package com.lm.jbm.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.service.JmService;
import com.lm.jbm.utils.LogUtil;
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
				if(PropertiesUtil.getLevelUserIds(0) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(0));
				}
				if(PropertiesUtil.getLevelUserIds(1) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(1));
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
				if(PropertiesUtil.getLevelUserIds(8) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(8));
				}
				if(PropertiesUtil.getLevelUserIds(9) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(9));
				}
				if(PropertiesUtil.getLevelUserIds(11) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(11));
				}
				if(PropertiesUtil.getLevelUserIds(12) != null) {
					list.addAll(PropertiesUtil.getLevelUserIds(12));
				}
				String[] boxUser = RandomUtil.getUserIds("boxUser");
				if(boxUser != null) {
					list.addAll(Arrays.asList(boxUser));
				}
				String[] nothing_1 = RandomUtil.getUserIds("nothing_1");
				if(nothing_1 != null) {
					list.addAll(Arrays.asList(nothing_1));
				}
				String[] nothing_2 = RandomUtil.getUserIds("nothing_2");
				if(nothing_2 != null) {
					list.addAll(Arrays.asList(nothing_2));
				}
				String[] nothing_3 = RandomUtil.getUserIds("nothing_3");
				if(nothing_3 != null) {
					list.addAll(Arrays.asList(nothing_3));
				}
				String[] nothing_4 = RandomUtil.getUserIds("nothing_4");
				if(nothing_4 != null) {
					list.addAll(Arrays.asList(nothing_4));
				}
				for(String userId : list) {
					String ip = "";
					if(ipMap.containsKey(userId)) {
						ip = ipMap.get(userId);
					} else {
						ip = RandomUtil.getIp();
						ipMap.put(userId, ip);
					}
					String ret = JmService.login(userId, RandomUtil.getPwd(), ip);
					if(StringUtils.isNotEmpty(ret)) {
						serssionMap.put(userId, ret);
						if(!signMap.containsKey(userId)) {
							signMap.put(userId, "1");
							JmService.sign(userId, ret, ip);
							Thread.sleep(5000);
						}
					}
					Thread.sleep(10000);
				}
				Thread.sleep(1000*60*60);
			} catch (Exception e) {
				LogUtil.log.error(e.getMessage(), e);
				break;
			}
		}
	}

}
