package com.lm.jbm.thread;


import java.net.Socket;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.service.JmService;
import com.lm.jbm.socket.SocketUtil;
import com.lm.jbm.utils.DateUtil;
import com.lm.jbm.utils.LogUtil;
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
			String session = JmService.getSessionId(userId);
			int sleepTime1 = 1000; // 延迟进入房间时间
			int sleepTime2 = 1000; // 进入房间后延迟摘桃时间
			boolean flag = JmService.checkFreeTime(); // 是否是 01:00 ~ 10:30
			boolean isInroom = true;
			if(way <= 5) { // 人太少
				if(flag) { // 01:00 ~ 10:30，间隔6~12秒
					sleepTime1 = RandomUtil.getRandom(4000, 6000);
					sleepTime2 = RandomUtil.getRandom(2000, 3000);
				} else { // 其他时间段， 间隔4~8秒
					sleepTime1 = RandomUtil.getRandom(4000, 5500);
					sleepTime2 = RandomUtil.getRandom(1000, 2000);
				}
			} else if(way <= 10) { // 人太少
				if(flag) { // 01:00 ~ 10:30，间隔6~12秒
					sleepTime1 = RandomUtil.getRandom(4000, 6000);
					sleepTime2 = RandomUtil.getRandom(2000, 3000);
				} else { // 其他时间段， 间隔4~8秒
					sleepTime1 = RandomUtil.getRandom(4000, 5500);
					sleepTime2 = RandomUtil.getRandom(1000, 2000);
				}
			} else if(way <= 15) { // 人少
				if(flag) {  // 01:00 ~ 10:30，间隔5~10秒
					sleepTime1 = RandomUtil.getRandom(4000, 6000);
					sleepTime2 = RandomUtil.getRandom(2000, 3000);
				} else { // 其他时间段，间隔3~8秒
					sleepTime1 = RandomUtil.getRandom(4000, 5500);
					sleepTime2 = RandomUtil.getRandom(1000, 2000);
				}
			} else if(way <= 25) { // 人少
				if(flag) {  // 01:00 ~ 10:30，间隔5~10秒
					sleepTime1 = RandomUtil.getRandom(4000, 6000);
					sleepTime2 = RandomUtil.getRandom(2000, 3000);
				} else { // 其他时间段，间隔3~8秒
					sleepTime1 = RandomUtil.getRandom(4000, 5500);
					sleepTime2 = RandomUtil.getRandom(1000, 1800);
				}
			} else if(way <= 35) { // 一般，
				if(flag) {  // 01:00 ~ 10:30，间隔4~8秒
					sleepTime1 = RandomUtil.getRandom(4000, 6000);
					sleepTime2 = RandomUtil.getRandom(2000, 3000);
				} else { // 其他时间段，间隔3~6秒
					sleepTime1 = RandomUtil.getRandom(4000, 5500);
					sleepTime2 = RandomUtil.getRandom(1000, 1800);
				}
			} else if(way <= 40) { // 人多，
				if(flag) {  // 01:00 ~ 10:30，间隔3~5秒
					sleepTime1 = RandomUtil.getRandom(4000, 5500);
					sleepTime2 = RandomUtil.getRandom(1000, 2000);
				} else { // 其他时间段，间隔2~4秒
					sleepTime1 = RandomUtil.getRandom(4000, 5500);
					sleepTime2 = RandomUtil.getRandom(1000, 1800);
				}
			} else { // 人很多，
				isInroom = false;
				sleepTime1 = RandomUtil.getRandom(800, 1500);
				sleepTime2 = RandomUtil.getRandom(300, 600);
			}
			if(isInroom) {
				Thread.sleep(sleepTime1);
				socket = SocketUtil.inRoom(roomId, userId);
			} else {
				JmService.inRoom(roomId, userId);
			}
			Thread.sleep(sleepTime2);
			LogUtil.log.info("peachThread：房间：" + roomId + "，摘桃账号："+userId 
					+ "，在线成员：" + way 
					+ "，摘桃时间：" +DateUtil.format2Str(new Date(), "yyyy-MM-dd HH:mm:ss")
					+ "，是否进入房间（IM）："+isInroom
					+ "，(进入房间前，睡眠时间：" + sleepTime1 + ")"
					+ "，摘桃前，睡眠时间：" + sleepTime2);
			JmService.pluck(roomId, userId, session, ip);
			if(socket != null) {
				Thread.sleep(15000);
				socket.close();
			}
			Thread.sleep(5000);
			JmService.outRoom(roomId, userId);
			JmService.remove(userId);
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
