package com.lm.jbm.thread;



import com.lm.jbm.factory.RoomListenFactory;
import com.lm.jbm.service.RedPackService;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;

public class GrapRedThread implements Runnable {

	
	private String roomId;
	private String sessionId;
	private String userId;
	private boolean isInRoom;
	private String rebId;
	
	public GrapRedThread() {
	}

	public void run() {
		try {
			if(isInRoom) {
				Thread.sleep(RandomUtil.getRandom(1000, 2000));
				RoomListenFactory.listenRoom(userId, sessionId, roomId);
			}
			RedPackService.grapReb(roomId, userId, sessionId, rebId);
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


}
