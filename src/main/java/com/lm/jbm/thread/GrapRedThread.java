package com.lm.jbm.thread;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.lm.jbm.service.InitializingBiz;
import com.lm.jbm.service.RedPackService;
import com.lm.jbm.socket.MsgManager;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;




public class GrapRedThread implements Runnable {

	
	private String roomId;
	private String sessionId;
	private String userId;
	private String token;
	private Socket socket;
	private boolean isInRoom;
	private String rebId;
	
	public GrapRedThread() {
	}

	public void run() {
		OutputStream ops = null;
		try {
			
			if(isInRoom) {
				Thread.sleep(RandomUtil.getRandom(1000, 2000));
				ops = socket.getOutputStream();
				MsgManager.getInstance().inRoom(roomId, userId, token, ops);
			}
			RedPackService.grapReb(roomId, userId, sessionId, rebId);
			
			Thread.sleep(RandomUtil.getRandom(5*60*1000, 10*60*1000));
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

	public String getRebId() {
		return rebId;
	}

	public void setRebId(String rebId) {
		this.rebId = rebId;
	}

	public boolean isInRoom() {
		return isInRoom;
	}

	public void setInRoom(boolean isInRoom) {
		this.isInRoom = isInRoom;
	}


}
