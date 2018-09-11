package com.lm.jbm.socket;


import java.net.Socket;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.service.JmService;
import com.lm.jbm.thread.PeachThread;
import com.lm.jbm.thread.ThreadManager;
import com.lm.jbm.utils.JsonUtil;


public class SocketInThread implements Runnable {

	public SocketInThread() {
	}

	public void run() {
		try {
			// 进入房间
			SocketUtil.msgListen();
			while(true) {
				try {
					String msg = SocketUtil.recieve();
					handleMsg(msg);
				} catch (Exception e) {
					break;
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void handleMsg(String msg) {
		try {
			if(StringUtils.isEmpty(msg)) {
				return;
			}
			JSONObject ret = JsonUtil.strToJsonObject(msg);
			JSONObject data = JsonUtil.strToJsonObject(ret.getString("data"));
			if(data != null) {
				if(data.containsKey("status")) {
					if(data.getIntValue("status") == 5007) {
						try {
							if(SocketClient.client != null) {
								SocketClient.client.close();
								SocketClient.client = null;
							}
							SocketClient.init();
						} catch(Exception e) {
							//
						}
					}
				}
				if(data.containsKey("type")) {
					int type = Integer.parseInt(data.get("type").toString());
					switch(type) {
					case 17: 
						JSONObject content = JsonUtil.strToJsonObject(data.get("content").toString());
						if(content != null && content.containsKey("roomId")) {
							String roomId = content.getString("roomId");
							System.out.println("收到成熟通知，开启抢桃，房间：" + roomId);
							JmService.peach(roomId);
						}
						break;
					}
				}
			}
		} catch(Exception e) {
			//
		}
	}
}
