package com.lm.jbm.thread;


import java.net.Socket;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.service.JmService;
import com.lm.jbm.socket.SocketUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;




public class PeachFastThread implements Runnable {

	
	private String roomId;
	
	private String userId;
	
	private int way;
	
	public PeachFastThread(String roomId, String userId, int way) {
		this.roomId = roomId;
		this.userId = userId;
		this.way = way;
	}

	public void run() {
		try {
			int sleepTime = 1000;
			int pluckTime = 1000;
			if(way <= 5) {  
				pluckTime = RandomUtil.getRandom(6000, 8000);
			} else if(way <= 10) {  
				pluckTime = RandomUtil.getRandom(5000, 8000);
			} else if(way <= 15) {  
				pluckTime = RandomUtil.getRandom(3000, 5000);
			} else if(way <= 25) { 
				pluckTime = RandomUtil.getRandom(2000, 3000);
			} else if(way <= 35) { 
				pluckTime = RandomUtil.getRandom(1000, 2000);
			} else if(way <= 40) { 
				pluckTime = RandomUtil.getRandom(1000, 1500);
			} else {  
				pluckTime = RandomUtil.getRandom(500, 700);
			}
			String ip = RandomUtil.getUserIp(userId);
			String session = JmService.getSessionId(userId);
			JmService.inRoom(roomId, userId);
			Thread.sleep(pluckTime);
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

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

}
