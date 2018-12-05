package com.lm.jbm.thread;


import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	private boolean isInroom;
	
	private int way;
	
	private List<String> list;
	
	public PeachThread(String roomId, String userId, int way, boolean isInroom, List<String> list) {
		this.roomId = roomId;
		this.userId = userId;
		this.way = way;
		this.isInroom = isInroom;
		this.list = list;
	}

	public void run() {
		try {
			Socket socket = null;
//			int sleepTime1 = 1000; // 延迟进入房间时间
//			int sleepTime2 = 500; // 进入房间后延迟摘桃时间
//			if(way <= 5) { // 人太少
//				if(flag) { // 01:00 ~ 10:30，间隔6~12秒
//					sleepTime1 = RandomUtil.getRandom(4000, 9000);
//					sleepTime2 = RandomUtil.getRandom(1000, 5000);
//				} else { // 其他时间段， 间隔4~8秒
//					sleepTime1 = RandomUtil.getRandom(4000, 5500);
//					sleepTime2 = RandomUtil.getRandom(1000, 2000);
//				}
//			} else if(way <= 10) { // 人太少
//				if(flag) { // 01:00 ~ 10:30，间隔6~12秒
//					sleepTime1 = RandomUtil.getRandom(4000, 6000);
//					sleepTime2 = RandomUtil.getRandom(2000, 3000);
//				} else { // 其他时间段， 间隔4~8秒
//					sleepTime1 = RandomUtil.getRandom(4000, 5500);
//					sleepTime2 = RandomUtil.getRandom(1000, 2000);
//				}
//			} else if(way <= 15) { // 人少
//				if(flag) {  // 01:00 ~ 10:30，间隔5~10秒
//					sleepTime1 = RandomUtil.getRandom(4000, 6000);
//					sleepTime2 = RandomUtil.getRandom(2000, 3000);
//				} else { // 其他时间段，间隔3~8秒
//					sleepTime1 = RandomUtil.getRandom(4000, 5500);
//					sleepTime2 = RandomUtil.getRandom(1000, 2000);
//				}
//			} else if(way <= 25) { // 人少
//				if(flag) {  // 01:00 ~ 10:30，间隔5~10秒
//					sleepTime1 = RandomUtil.getRandom(4000, 6000);
//					sleepTime2 = RandomUtil.getRandom(2000, 3000);
//				} else { // 其他时间段，间隔3~8秒
//					sleepTime1 = RandomUtil.getRandom(4000, 5500);
//					sleepTime2 = RandomUtil.getRandom(1000, 1800);
//				}
//			} else if(way <= 35) { // 一般，
//				if(flag) {  // 01:00 ~ 10:30，间隔4~8秒
//					sleepTime1 = RandomUtil.getRandom(4000, 6000);
//					sleepTime2 = RandomUtil.getRandom(2000, 3000);
//				} else { // 其他时间段，间隔3~6秒
//					sleepTime1 = RandomUtil.getRandom(4000, 5500);
//					sleepTime2 = RandomUtil.getRandom(1000, 1800);
//				}
//			} else if(way <= 40) { // 人多，
//				if(flag) {  // 01:00 ~ 10:30，间隔3~5秒
//					sleepTime1 = RandomUtil.getRandom(4000, 5500);
//					sleepTime2 = RandomUtil.getRandom(1000, 2000);
//				} else { // 其他时间段，间隔2~4秒
//					sleepTime1 = RandomUtil.getRandom(4000, 5500);
//					sleepTime2 = RandomUtil.getRandom(1000, 1800);
//				}
//			} else { // 人很多，
//				isInroom = false;
//				sleepTime1 = RandomUtil.getRandom(800, 1500);
//				sleepTime2 = RandomUtil.getRandom(300, 600);
//			}
//				if(isInroom) {
//					Thread.sleep(sleepTime1);
//					socket = SocketUtil.inRoom(roomId, userId);
//				} else {
//					JmService.inRoom(roomId, userId);
//				}
//				
//				LogUtil.log.info("peachThread（方式一）：房间：" + roomId + "，摘桃账号："+userId 
//						+ "，在线成员：" + way 
//						+ "，进入房间时间：" + DateUtil.format2Str(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")
//						+ "，摘桃前，睡眠时间：" + sleepTime2
//						+ "，摘桃时间：" +DateUtil.format2Str(DateUtil.addSecond(new Date(), sleepTime2), "yyyy-MM-dd HH:mm:ss.SSS"));
//				Thread.sleep(sleepTime2);
//				JmService.pluck(roomId, userId, session, ip);
//				if(socket != null) {
//					Thread.sleep(15000);
//					socket.close();
//				}
//				Thread.sleep(5000);
//				JmService.outRoom(roomId, userId);
//				JmService.remove(userId);
			// 固定进场顺序
			if(list != null && list.size() >0) {
				Map<String, Socket> m = new HashMap<String, Socket>();
				boolean flag = JmService.checkWorkTime();
				int size = list.size();
				int sleepTime1 = 3000; // 延迟进入房间时间
				int sleepTime2 = 1000; // 延迟摘
				for(int i=0; i<size; i++) {
					long time0 = System.currentTimeMillis();
					String user = list.get(i);
					if(way >= 35) { 
						switch(i) {
						case 0:
							sleepTime1 = 3500;
							sleepTime2 = 1200;
							break;
						case 1:
							sleepTime1 = 4000;
							sleepTime2 = 1000;
							break;
						case 2:
							sleepTime1 = 4500;
							sleepTime2 = 1000;
							break;
						case 3:
							sleepTime1 = 5000;
							sleepTime2 = 1000;
							break;
						case 4:
							sleepTime1 = 5200;
							sleepTime2 = 800;
							break;
						case 5:
							sleepTime1 = 5300;
							sleepTime2 = 700;
							break;
						case 6:
							sleepTime1 = 5400;
							sleepTime2 = 700;
							break;
						case 7:
							sleepTime1 = 5600;
							sleepTime2 = 600;
							break;
						default:
							sleepTime1 = 6000;
							sleepTime2 = 800;
						}
					} else if(way >= 30) { 
						switch(i) {
						case 0:
							sleepTime1 = 3500;
							sleepTime2 = 1300;
							break;
						case 1:
							sleepTime1 = 4000;
							sleepTime2 = 1200;
							break;
						case 2:
							sleepTime1 = 4500;
							sleepTime2 = 1000;
							break;
						case 3:
							sleepTime1 = 5000;
							sleepTime2 = 800;
							break;
						case 4:
							sleepTime1 = 5100;
							sleepTime2 = 800;
							break;
						case 5:
							sleepTime1 = 5200;
							sleepTime2 = 700;
							break;
						case 6:
							sleepTime1 = 5300;
							sleepTime2 = 600;
							break;
						case 7:
							sleepTime1 = 5500;
							sleepTime2 = 600;
							break;
						case 8:
							sleepTime1 = 6000;
							sleepTime2 = 600;
							break;
						default:
							sleepTime1 = 7000;
							sleepTime2 = 1000;
						}
					} else if(way >= 25) { 
						switch(i) {
						case 0:
							sleepTime1 = 3500;
							sleepTime2 = 1000;
							break;
						case 1:
							sleepTime1 = 4000;
							sleepTime2 = 1000;
							break;
						case 2:
							sleepTime1 = 4500;
							sleepTime2 = 1000;
							break;
						case 3:
							sleepTime1 = 5000;
							sleepTime2 = 700;
							break;
						case 4:
							sleepTime1 = 5100;
							sleepTime2 = 700;
							break;
						case 5:
							sleepTime1 = 5200;
							sleepTime2 = 600;
							break;
						case 6:
							sleepTime1 = 5300;
							sleepTime2 = 600;
							break;
						case 7:
							sleepTime1 = 5400;
							sleepTime2 = 600;
							break;
						case 8:
							sleepTime1 = 5500;
							sleepTime2 = 600;
							break;
						case 9:
							sleepTime1 = 6000;
							sleepTime2 = 500;
							break;
						case 10:
							sleepTime1 = 6100;
							sleepTime2 = 500;
							break;
						default:
							sleepTime1 = 7000;
							sleepTime2 = 1000;
						}
					} else if(way >= 20) { 
						switch(i) {
						case 0:
							sleepTime1 = 3500;
							sleepTime2 = 1500;
							break;
						case 1:
							sleepTime1 = 4000;
							sleepTime2 = 1400;
							break;
						case 2:
							sleepTime1 = 4500;
							sleepTime2 = 1200;
							break;
						case 3:
							sleepTime1 = 5000;
							sleepTime2 = 1000;
							break;
						case 4:
							sleepTime1 = 5100;
							sleepTime2 = 800;
							break;
						case 5:
							sleepTime1 = 5200;
							sleepTime2 = 800;
							break;
						case 6:
							sleepTime1 = 5300;
							sleepTime2 = 800;
							break;
						case 7:
							sleepTime1 = 5400;
							sleepTime2 = 700;
							break;
						case 8:
							sleepTime1 = 5600;
							sleepTime2 = 600;
							break;
						case 9:
							sleepTime1 = 6000;
							sleepTime2 = 600;
							break;
						case 10:
							sleepTime1 = 6100;
							sleepTime2 = 500;
							break;
						case 11:
							sleepTime1 = 6200;
							sleepTime2 = 500;
							break;
						case 12:
							sleepTime1 = 6300;
							sleepTime2 = 500;
							break;
						default:
							sleepTime1 = 8000;
							sleepTime2 = 1000;
						}
					} else if(way >= 15) { 
						switch(i) {
						case 0:
							sleepTime1 = 3500;
							sleepTime2 = 1500;
							break;
						case 1:
							sleepTime1 = 4000;
							sleepTime2 = 1400;
							break;
						case 2:
							sleepTime1 = 4500;
							sleepTime2 = 1300;
							break;
						case 3:
							sleepTime1 = 5000;
							sleepTime2 = 1000;
							break;
						case 4:
							sleepTime1 = 5500;
							sleepTime2 = 800;
							break;
						case 5:
							sleepTime1 = 5600;
							sleepTime2 = 800;
							break;
						case 6:
							sleepTime1 = 5700;
							sleepTime2 = 800;
							break;
						case 7:
							sleepTime1 = 6000;
							sleepTime2 = 800;
							break;
						case 8:
							sleepTime1 = 6100;
							sleepTime2 = 800;
							break;
						case 9:
							sleepTime1 = 6200;
							sleepTime2 = 800;
							break;
						case 10:
							sleepTime1 = 6300;
							sleepTime2 = 800;
							break;
						case 11:
							sleepTime1 = 6400;
							sleepTime2 = 700;
							break;
						case 12:
							sleepTime1 = 6500;
							sleepTime2 = 600;
							break;
						case 13:
							sleepTime1 = 6600;
							sleepTime2 = 600;
							break;
						case 14:
							sleepTime1 = 6700;
							sleepTime2 = 600;
							break;
						default:
							sleepTime1 = 8000;
							sleepTime2 = 1000;
						}
					} else if(way >= 10) { // 人多，
						switch(i) {
						case 0:
							sleepTime1 = 4000;
							sleepTime2 = 1500;
							break;
						case 1:
							sleepTime1 = 4500;
							sleepTime2 = 1000;
							break;
						case 2:
							sleepTime1 = 5000;
							sleepTime2 = 1000;
							break;
						case 3:
							sleepTime1 = 5300;
							sleepTime2 = 1000;
							break;
						case 4:
							sleepTime1 = 5500;
							sleepTime2 = 1000;
							break;
						case 5:
							sleepTime1 = 5600;
							sleepTime2 = 800;
							break;
						case 6:
							sleepTime1 = 5700;
							sleepTime2 = 800;
							break;
						case 7:
							sleepTime1 = 5800;
							sleepTime2 = 700;
							break;
						case 8:
							sleepTime1 = 6000;
							sleepTime2 = 600;
							break;
						case 9:
							sleepTime1 = 6100;
							sleepTime2 = 600;
							break;
						case 10:
							sleepTime1 = 6200;
							sleepTime2 = 600;
							break;
						case 11:
							sleepTime1 = 6400;
							sleepTime2 = 600;
							break;
						case 12:
							sleepTime1 = 6500;
							sleepTime2 = 600;
							break;
						case 13:
							sleepTime1 = 6600;
							sleepTime2 = 600;
							break;
						case 14:
							sleepTime1 = 6700;
							sleepTime2 = 600;
							break;
						case 15:
							sleepTime1 = 6800;
							sleepTime2 = 600;
							break;
						case 16:
							sleepTime1 = 6900;
							sleepTime2 = 600;
							break;
						case 17:
							sleepTime1 = 7000;
							sleepTime2 = 600;
							break;
						default:
							sleepTime1 = 8000;
							sleepTime2 = 1000;
						}
					} else if(way >= 5) {
						switch(i) {
						case 0:
							sleepTime1 = 4000;
							sleepTime2 = 1500;
							break;
						case 1:
							sleepTime1 = 4500;
							sleepTime2 = 1500;
							break;
						case 2:
							sleepTime1 = 5000;
							sleepTime2 = 1300;
							break;
						case 3:
							sleepTime1 = 5300;
							sleepTime2 = 1200;
							break;
						case 4:
							sleepTime1 = 5500;
							sleepTime2 = 1200;
							break;
						case 5:
							sleepTime1 = 5800;
							sleepTime2 = 1000;
							break;
						case 6:
							sleepTime1 = 5900;
							sleepTime2 = 1000;
							break;
						case 7:
							sleepTime1 = 6000;
							sleepTime2 = 1000;
							break;
						case 8:
							sleepTime1 = 6100;
							sleepTime2 = 900;
							break;
						case 9:
							sleepTime1 = 6200;
							sleepTime2 = 800;
							break;
						case 10:
							sleepTime1 = 6500;
							sleepTime2 = 800;
							break;
						case 11:
							sleepTime1 = 6700;
							sleepTime2 = 700;
							break;
						case 12:
							sleepTime1 = 6800;
							sleepTime2 = 600;
							break;
						case 13:
							sleepTime1 = 7000;
							sleepTime2 = 600;
							break;
						case 14:
							sleepTime1 = 7100;
							sleepTime2 = 600;
							break;
						case 15:
							sleepTime1 = 7150;
							sleepTime2 = 600;
							break;
						case 16:
							sleepTime1 = 7200;
							sleepTime2 = 600;
							break;
						case 17:
							sleepTime1 = 7300;
							sleepTime2 = 600;
							break;
						case 18:
							sleepTime1 = 7400;
							sleepTime2 = 600;
							break;
						case 19:
							sleepTime1 = 7500;
							sleepTime2 = 600;
							break;
						default:
							sleepTime1 = 9000;
							sleepTime2 = 1000;
						}
					} else{ 
						switch(i) {
						case 0:
							sleepTime1 = 4000;
							sleepTime2 = 1500;
							break;
						case 1:
							sleepTime1 = 4500;
							sleepTime2 = 1500;
							break;
						case 2:
							sleepTime1 = 5000;
							sleepTime2 = 1300;
							break;
						case 3:
							sleepTime1 = 5300;
							sleepTime2 = 1200;
							break;
						case 4:
							sleepTime1 = 5500;
							sleepTime2 = 1200;
							break;
						case 5:
							sleepTime1 = 5800;
							sleepTime2 = 1000;
							break;
						case 6:
							sleepTime1 = 5900;
							sleepTime2 = 1000;
							break;
						case 7:
							sleepTime1 = 6000;
							sleepTime2 = 1000;
							break;
						case 8:
							sleepTime1 = 6100;
							sleepTime2 = 900;
							break;
						case 9:
							sleepTime1 = 6200;
							sleepTime2 = 800;
							break;
						case 10:
							sleepTime1 = 6500;
							sleepTime2 = 800;
							break;
						case 11:
							sleepTime1 = 6700;
							sleepTime2 = 700;
							break;
						case 12:
							sleepTime1 = 6800;
							sleepTime2 = 600;
							break;
						case 13:
							sleepTime1 = 7000;
							sleepTime2 = 600;
							break;
						case 14:
							sleepTime1 = 7100;
							sleepTime2 = 600;
							break;
						case 15:
							sleepTime1 = 7150;
							sleepTime2 = 600;
							break;
						case 16:
							sleepTime1 = 7200;
							sleepTime2 = 600;
							break;
						case 17:
							sleepTime1 = 7300;
							sleepTime2 = 600;
							break;
						case 18:
							sleepTime1 = 7400;
							sleepTime2 = 600;
							break;
						case 19:
							sleepTime1 = 7500;
							sleepTime2 = 600;
							break;
						case 20:
							sleepTime1 = 7600;
							sleepTime2 = 600;
							break;
						default:
							sleepTime1 = 9000;
							sleepTime2 = 1000;
						}
					}
//					if(isInroom) {
//						Thread.sleep(sleepTime1);
//						socket = SocketUtil.inRoom(roomId, user);
//						m.put(user, socket);
//					} else {
//						JmService.inRoom(roomId, user);
//					}
					long time1 = System.currentTimeMillis();
					String ip = RandomUtil.getUserIp(user);
					long time2 =  System.currentTimeMillis();
					String session = JmService.getSessionId(user);
					long time3 =  System.currentTimeMillis();
					pluck(roomId, user, session, ip, sleepTime1, sleepTime2);
					long time4 =  System.currentTimeMillis();
					LogUtil.log.info("### 枪桃线程，房间：" + roomId + "，用户：" + user 
							+ "，获取ip耗时：" + (time2 - time1) 
							+ "，获取session耗时：" + (time3 - time2)
							+ "，处理线程耗时：" + (time4-time3)
							+ "，每处理一个用户，总耗时：" + (time4 - time0));
//					Thread.sleep(sleepTime2);
//					JmService.pluck(roomId, user, session, ip);
				}
//				Thread.sleep(30000);
//				if(m.size() >0) {
//					for(String key : m.keySet()) {
//						Socket soc = m.get(key);
//						if(soc != null) {
//							socket.close();
//						} 
//						JmService.outRoom(roomId, key);
//						JmService.remove(key);
//					}
//				}
			}
		} catch (Exception e) {
		}
	}
	
	private static void pluck(final String roomId, final String userId,final String session, final String ip,final int sleepTime1,final int sleepTime2) {
		ThreadManager.getInstance().execute(new Runnable() {
			public void run() {
				Socket socket = null;
				try {
					Date now = new Date();
					Thread.sleep(sleepTime1);
					Date begin = DateUtil.addSecond(now, sleepTime1);
					socket = SocketUtil.inRoom(roomId, userId);
					Thread.sleep(sleepTime2);
					Date end = DateUtil.addSecond(now, sleepTime2);
					JmService.pluck(roomId, userId, session, ip);
					LogUtil.log.info("peachThread（方式一）：房间：" + roomId + "，摘桃账号："+ userId 
							+ "，进入房间时间：" + DateUtil.format2Str(begin, "yyyy-MM-dd HH:mm:ss.SSS")
							+ "，摘桃时间：" +DateUtil.format2Str(end, "yyyy-MM-dd HH:mm:ss.SSS"));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(socket != null) {
						try {
							Thread.sleep(20000);
							socket.close();
							JmService.outRoom(roomId, userId);
							JmService.remove(userId);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
	}
}
