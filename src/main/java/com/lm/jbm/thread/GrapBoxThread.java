package com.lm.jbm.thread;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.lm.jbm.service.GraboxService;
import com.lm.jbm.service.InitializingBiz;
import com.lm.jbm.socket.MsgManager;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;




public class GrapBoxThread implements Runnable {

	private boolean isInRoom;
	private String roomId;
	private String sessionId;
	private String userId;
	private String token;
	private Socket socket;
	
	public GrapBoxThread() {
	}

	public void run() {
		OutputStream ops = null;
		try {
			if(isInRoom) {
				Thread.sleep(RandomUtil.getRandom(2000, 3000));
				ops = socket.getOutputStream();
				MsgManager.getInstance().inRoom(roomId, userId, token, ops);
			}
			GraboxService.grapBox(roomId, sessionId, userId);
			
			Thread.sleep(RandomUtil.getRandom(60*1000, 5*60*1000));
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		} finally {
			try {
				MsgManager.getInstance().outRoom(roomId, userId, token, ops);
			} catch (Exception e1) {
			}
			InitializingBiz.changeRoom(userId, roomId);
		}
	}

	public boolean isInRoom() {
		return isInRoom;
	}

	public void setInRoom(boolean isInRoom) {
		this.isInRoom = isInRoom;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}


}
