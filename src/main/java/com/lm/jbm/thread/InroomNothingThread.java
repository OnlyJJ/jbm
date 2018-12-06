package com.lm.jbm.thread;


import java.net.Socket;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.service.JmService;
import com.lm.jbm.socket.SocketUtil;
import com.lm.jbm.utils.DateUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;




public class InroomNothingThread implements Runnable {

	
	private String roomId;
	
	public InroomNothingThread(String roomId) {
		this.roomId = roomId;
	}

	public void run() {
		try {
			Socket socket = null;
			Map<String, Socket> m = new HashMap<String, Socket>();
			List<String> users = RandomUtil.getNothingUser();
			boolean flag = JmService.checkWorkTime();
			int outTime = 3;
			if(users != null && users.size() >0) {
				Collections.shuffle(users);
				int index = 0;
				int time = 600;
				for(String userId : users) {
					if(index >= 4) {
						break;
					} 
					if(index == 0) {
						time = 4000;
					}else {
						time = 600;
					} 
					Thread.sleep(time);
					socket = SocketUtil.inRoom(roomId, userId);
					m.put(userId, socket);
					if(flag) {
						outTime = 5;
					}
					RandomUtil.nothingMap.put(userId, DateUtil.format2Str(DateUtil.addMinute(new Date(), outTime), "yyyy-MM-dd HH:mm:ss"));
					LogUtil.log.info("InroomNothingThread：房间：" + roomId + "，随机混入用户："+ userId 
							+ "，进入房间时间：" + DateUtil.format2Str(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
					index++;
				}
				Thread.sleep(30000);
				if(m.size() >0) {
					for(String key : m.keySet()) {
						Socket soc = m.get(key);
						if(soc != null) {
							socket.close();
						}
						JmService.outRoom(roomId, key);
					}
				}
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
}
