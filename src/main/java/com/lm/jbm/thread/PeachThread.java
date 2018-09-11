package com.lm.jbm.thread;


import java.net.Socket;
import java.util.Random;

import com.lm.jbm.service.JmService;
import com.lm.jbm.socket.SocketUtil;
import com.lm.jbm.utils.RandomUtil;




public class PeachThread implements Runnable {

	
	private String roomId;
	
	private String userId;
	
	public PeachThread(String roomId, String userId) {
		this.roomId = roomId;
		this.userId = userId;
	}

	public void run() {
		try {
			String ip = RandomUtil.getIp();
			String session = LoginThread.serssionMap.get(userId);
			Thread.sleep(new Random().nextInt(500));
			Socket socket = SocketUtil.inRoom(roomId, userId);
			Thread.sleep(new Random().nextInt(1000));
			JmService.pluck(roomId, userId, session, ip);
			if(socket != null) {
				socket.close();
			}
		} catch (Exception e) {
		}
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
