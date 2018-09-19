package com.lm.jbm.thread;


import java.net.Socket;
import java.util.Random;

import com.lm.jbm.service.JmService;
import com.lm.jbm.socket.SocketUtil;
import com.lm.jbm.utils.RandomUtil;




public class PeachThread implements Runnable {

	
	private String roomId;
	
	private String userId;
	
	private int way;
	
	public PeachThread(String roomId, String userId, int way) {
		this.roomId = roomId;
		this.userId = userId;
		this.way = way;
	}

	public void run() {
		try {
			Socket socket = null;
			String ip = RandomUtil.getUserIp(userId);
			String session = LoginThread.serssionMap.get(userId);
			int sleepTime1 = 1000;
			int sleepTime2 = 1000;
			if(JmService.checkTime()) { // 01:00 ~ 10:30
				if(way == 0) { // 人少，间隔5~10秒
					sleepTime1 = RandomUtil.getRandom(3000, 5000);
					sleepTime2 = RandomUtil.getRandom(2000, 5000);
				} else { // 人多，间隔2.5~5秒
					sleepTime1 = RandomUtil.getRandom(1500, 3000);
					sleepTime2 = RandomUtil.getRandom(1000, 2000);
				}
			} else { // 其他时间段
				if(way == 0) { // 人少，间隔5~9秒
					sleepTime1 = RandomUtil.getRandom(3000, 6000);
					sleepTime2 = RandomUtil.getRandom(2000, 3000);
				} else { // 人多，间隔1.3~3.5秒
					sleepTime1 = RandomUtil.getRandom(800, 2000);
					sleepTime2 = RandomUtil.getRandom(500, 1500);
				}
			}
			Thread.sleep(sleepTime1);
			socket = SocketUtil.inRoom(roomId, userId);
			Thread.sleep(sleepTime2);
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
