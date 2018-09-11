package com.lm.jbm.thread;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.service.JmService;
import com.lm.jbm.utils.RandomUtil;




public class LoginThread implements Runnable {
	
	public static ConcurrentHashMap<String, String> serssionMap = new ConcurrentHashMap<String, String>(512);

	public LoginThread() {}

	public void run() {
		while(true) {
			try {
				String[] userIds = RandomUtil.getUserIds();
				if(userIds != null) {
					for(String userId : userIds) {
						String ret = JmService.login(userId, RandomUtil.getPwd(), RandomUtil.getIp());
						if(StringUtils.isNotEmpty(ret)) {
							serssionMap.put(userId, ret);
						}
						Thread.sleep(2000);
					}
				}
				Thread.sleep(1000*60*6);
			} catch (Exception e) {
				break;
			}
		}
	}

}
