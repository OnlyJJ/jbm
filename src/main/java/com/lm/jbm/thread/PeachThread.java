package com.lm.jbm.thread;


import java.net.Socket;

import com.lm.jbm.factory.RoomListenFactory;
import com.lm.jbm.service.PeachService;
import com.lm.jbm.utils.RandomUtil;

public class PeachThread implements Runnable {
	private String roomId;
	private String userId;
	private String sessionId;
	private boolean isInRoom;
	private Socket socket;
	
	public PeachThread() {
	}

	public void run() {
		try {
			if(isInRoom) {
				int sleep = PeachService.getSleepTime(roomId);
				Thread.sleep(sleep);
				RoomListenFactory.listenRoom(userId, sessionId, roomId);
			}
			System.out.println("开始枪桃：thread: " + Thread.currentThread().getName() + " ,userId=" + userId + ", roomId = " + roomId);
			PeachService.pluck(roomId, userId, sessionId);
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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean isInRoom() {
		return isInRoom;
	}

	public void setInRoom(boolean isInRoom) {
		this.isInRoom = isInRoom;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
