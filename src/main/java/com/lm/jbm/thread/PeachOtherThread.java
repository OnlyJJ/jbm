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




public class PeachOtherThread implements Runnable {

	
	private String roomId;
	
	private String userId;
	
	private boolean isInroom;
	
	private int way;
	
	public PeachOtherThread(String roomId, String userId, int way, boolean isInroom) {
		this.roomId = roomId;
		this.userId = userId;
		this.way = way;
		this.isInroom = isInroom;
	}

	public void run() {
		try {
			Socket socket = null;
			String ip = RandomUtil.getUserIp(userId);
			String session = JmService.getSessionId(userId);
			int sleepTime2 = 500;  
			boolean flag = JmService.checkWorkTime(); 
			if(way <= 5) {  
				if(flag) {  
					sleepTime2 = RandomUtil.getRandom(1000, 1500);
				} else {  
					sleepTime2 = RandomUtil.getRandom(800, 1000);
				}
			} else if(way <= 10) {  
				if(flag) {  
					sleepTime2 = RandomUtil.getRandom(1000, 1500);
				} else {  
					sleepTime2 = RandomUtil.getRandom(700, 1000);
				}
			} else if(way <= 15) {  
				if(flag) {   
					sleepTime2 = RandomUtil.getRandom(1000, 1500);
				} else {  
					sleepTime2 = RandomUtil.getRandom(600, 800);
				}
			} else if(way <= 25) {  
				if(flag) {   
					sleepTime2 = RandomUtil.getRandom(1000, 1500);
				} else { 
					sleepTime2 = RandomUtil.getRandom(500, 800);
				}
			} else if(way <= 35) {  
				if(flag) {   
					sleepTime2 = RandomUtil.getRandom(1000, 1500);
				} else {  
					sleepTime2 = RandomUtil.getRandom(500, 800);
				}
			} else if(way <= 40) {  
				if(flag) {   
					sleepTime2 = RandomUtil.getRandom(1000, 1500);
				} else {  
					sleepTime2 = RandomUtil.getRandom(500, 800);
				}
			} else {  
				sleepTime2 = RandomUtil.getRandom(400, 600);
			}
			socket = SocketUtil.inRoom(roomId, userId);
			
			LogUtil.log.info("PeachOtherThread(方式二)：房间：" + roomId + "，摘桃账号："+userId 
					+ "，在线成员：" + way 
					+ "，进入房间时间：" + DateUtil.format2Str(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")
					+ "，摘桃前，睡眠时间：" + sleepTime2
					+ "，摘桃时间：" +DateUtil.format2Str(DateUtil.addSecond(new Date(), sleepTime2), "yyyy-MM-dd HH:mm:ss.SSS"));
			Thread.sleep(sleepTime2);
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

	public boolean isInroom() {
		return isInroom;
	}

	public void setInroom(boolean isInroom) {
		this.isInroom = isInroom;
	}

}
