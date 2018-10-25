package com.lm.jbm.application;





import java.util.Random;

import com.lm.jbm.service.JmService;
import com.lm.jbm.socket.SocketClient;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.PropertiesUtil;
import com.lm.jbm.utils.RandomUtil;



public class JbmApplication {
	
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
