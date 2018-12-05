package com.lm.jbm.application;





import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.service.JmService;
import com.lm.jbm.socket.SocketClient;
import com.lm.jbm.utils.DateUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.PropertiesUtil;
import com.lm.jbm.utils.RandomUtil;



public class JbmApplication {
	public static ConcurrentHashMap<String, String> pluckRecordMap = new ConcurrentHashMap<String, String>(512);
	public static void main(String[] args) {
		try {
			// false：开发环境，true：生产
			PropertiesUtil.load(true);
			SocketClient.init();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
