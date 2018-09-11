package com.lm.jbm.application;

import com.lm.jbm.socket.SocketClient;
import com.lm.jbm.utils.RandomUtil;



public class JbmApplication {
	
	public static void main(String[] args) {
		try {
			SocketClient.init();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
