package com.lm.jbm.thread;


import java.net.Socket;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.service.JmService;
import com.lm.jbm.socket.SocketUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;




public class PeachNoInRoomThread implements Runnable {

	
	private String roomId;
	
	private String userId;
	
	private int way;
	
	public PeachNoInRoomThread(String roomId, String userId) {
		this.roomId = roomId;
		this.userId = userId;
	}

	public void run() {
		try {
			String ip = RandomUtil.getUserIp(userId);
			String session = JmService.getSessionId(userId);
			JmService.inRoom(roomId, userId);
			int sleepTime2 = 1000; // 进入房间后延迟摘桃时间
			Thread.sleep(sleepTime2);
			JmService.pluck(roomId, userId, session, ip);
			Thread.sleep(15000);
			JmService.outRoom(roomId, userId);
			JmService.remove(userId);
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage(), e);
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
