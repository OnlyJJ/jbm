package com.lm.jbm.thread;



import java.net.Socket;

import com.lm.jbm.factory.RoomListenFactory;
import com.lm.jbm.msg.MsgManager;
import com.lm.jbm.service.RedPackService;
import com.lm.jbm.utils.ChatMsgUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;

public class GrapRedThread implements Runnable {

	
	private String roomId;
	private String sessionId;
	private String userId;
	private boolean isInRoom;
	private String rebId;
	private String token;
	private Socket socket;
	
	public GrapRedThread() {
	}

	public void run() {
		try {
			if(isInRoom) {
				Thread.sleep(RandomUtil.getRandom(1000, 3000));
				RoomListenFactory.listenRoom(userId, sessionId, roomId);
			}
			if(RedPackService.grapReb(roomId, userId, sessionId, rebId)) {
				Thread.sleep(RandomUtil.getRandom(5000, 20000));
				MsgManager.sendChatMsg(roomId, userId, token, ChatMsgUtil.getMsgInfo(1), socket.getOutputStream());
			}
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
