package com.lm.jbm.thread;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.lm.jbm.factory.RoomListenFactory;
import com.lm.jbm.msg.MsgManager;
import com.lm.jbm.service.GraboxService;
import com.lm.jbm.service.InitializingBiz;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;




public class GrapBoxThread implements Runnable {

	private boolean isInRoom;
	private String roomId;
	private String sessionId;
	private String userId;
	
	public GrapBoxThread() {
	}

	public void run() {
		try {
			if(isInRoom) {
				Thread.sleep(RandomUtil.getRandom(2000, 5000));
				RoomListenFactory.listenRoom(userId, sessionId, roomId);
			} 
			Thread.sleep(2000);
			GraboxService.grapBox(roomId, sessionId, userId);
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage(), e);
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

}
