package com.lm.jbm.thread;


import java.net.Socket;

import com.lm.jbm.service.InitializingBiz;
import com.lm.jbm.service.PeachService;
import com.lm.jbm.socket.MsgManager;
import com.lm.jbm.utils.RandomUtil;

public class PeachThread implements Runnable {
	private String roomId;
	private String userId;
	private Socket socket;
	private String token;
	private String sessionId;
	private boolean isInRoom;
	
	public PeachThread() {
	}

	public void run() {
		try {
			if(isInRoom) {
				int sleep = PeachService.getSleepTime(roomId);
				Thread.sleep(sleep);
				MsgManager.getInstance().inRoom(roomId, userId, token, socket.getOutputStream());
			}
			PeachService.pluck(roomId, userId, sessionId);
			
			Thread.sleep(RandomUtil.getRandom(60*1000, 3*60*1000));
		} catch (Exception e) {
		} finally {
			try {
				MsgManager.getInstance().outRoom(roomId, userId, token, socket.getOutputStream());
			} catch (Exception e1) {
			}
			InitializingBiz.changeRoom(userId, roomId);
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

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
	
}
