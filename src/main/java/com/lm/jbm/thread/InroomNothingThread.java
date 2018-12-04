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
			Thread.sleep(5000); // 5s后，每秒进一个
			Map<String, Socket> m = new HashMap<String, Socket>();
			List<String> users = RandomUtil.getNothingUser();
			if(users != null && users.size() >0) {
				Collections.shuffle(users);
				int index = 0;
				for(String userId : users) {
					if(!isTimeOut(userId)) {
						continue;
					}
					if(index >= 4) {
						break;
					}
					socket = SocketUtil.inRoom(roomId, userId);
					m.put(userId, socket);
					RandomUtil.nothingMap.put(userId, DateUtil.format2Str(DateUtil.addMinute(new Date(), 2), "yyyy-MM-dd HH:mm:ss"));
					Thread.sleep(1000);
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
	
	private static boolean isTimeOut(String userId) {
		boolean flag = false;
		if(RandomUtil.nothingMap.containsKey(userId)) {
			Date now = new Date();
			String time = JmService.pluckRecordMap.get(userId);
			try {
				if(StringUtils.isNotEmpty(time)) {
					Date record = DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss");
					if(now.after(record)) {
						flag = true;
					} else {
						LogUtil.log.info("### 随机进入房间旁观：还在休息中，userId：" + userId + "，到期时间：" + time);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}
