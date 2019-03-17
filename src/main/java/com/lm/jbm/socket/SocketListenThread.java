package com.lm.jbm.socket;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.factory.RoomListenFactory;
import com.lm.jbm.factory.SocketFactory;
import com.lm.jbm.msg.MsgManager;
import com.lm.jbm.service.InitializingBiz;
import com.lm.jbm.thread.GrapBoxThread;
import com.lm.jbm.thread.GrapRedThread;
import com.lm.jbm.thread.PeachThread;
import com.lm.jbm.thread.ThreadManager;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;
import com.lm.jbm.utils.UserUtil;


public class SocketListenThread implements Runnable {
	
	private String userId;
	private String roomId;
	private String sessionId;
	private Socket socket;
	private String token;
	public SocketListenThread() {
	}

	public void run() {
		OutputStream ops = null;
		try {
			ops = socket.getOutputStream();
			int liveTime = RandomUtil.getRandom(10, 20);
			int time = 1;
			System.err.println("开始监听消息thread：" + Thread.currentThread().getName() + ", roomId = " + roomId +  ", userId: " + userId);
			while(true) {
				try {
					if(time > liveTime) {
						System.err.println("thread：" + Thread.currentThread().getName() + ",时长：" + liveTime + ",time = " + time + "，退出当前房间，roomId=" + roomId);
						// 离开房间
						RoomListenFactory.outRoom(userId, sessionId, roomId, socket);
						RoomListenFactory.changeRoom(userId, roomId, false);
						break;
					}
					String msg = MsgManager.recieve(socket);
					if(UserUtil.checkValid(userId)) {
						System.err.println("用户已过监听时段，销毁当前socket，userId= " +userId + ",roomId = " + roomId);
						// 离开房间
						RoomListenFactory.outRoom(userId, sessionId, roomId, socket);
						// 销毁socket
						SocketFactory.destory(socket, userId);
						// 登录新用户
						InitializingBiz.newUserListen();
						break;
					}
					if(handleMsg(msg)) {
						System.err.println("切换房间，取消当前监听线程：roomId = " +roomId + " , userId = " + userId);
						break;
					}
					time++;
				} catch (Exception e) {
					throw e;
				}
			}
		} catch(Exception e) {
			System.err.println("监听消息异常：" + e.getMessage() + ",roomId = " + roomId + ",userId = " + userId + ",thread = " + Thread.currentThread().getName());
			LogUtil.log.error(e.getMessage(), e);
		} 
	}
	
	private boolean handleMsg(String msg) throws Exception {
		boolean isInterap = false;
		try {
			if(StringUtils.isEmpty(msg)) {
				return false;
			}
			JSONObject ret = JsonUtil.strToJsonObject(msg);
			JSONObject data = JsonUtil.strToJsonObject(ret.getString("data"));
			if(data != null) {
				if(data.containsKey("status")) {
					if(data.getIntValue("status") == 5007) {
						try {
							RoomListenFactory.outRoom(msg, msg, msg, socket);
						} catch(Exception e) {}
						try {
							SocketFactory.destory(socket, userId);
							Thread.sleep(30 * 1000);
						} catch(Exception e) {
							throw e;
						} finally {
							RoomListenFactory.changeRoom(userId, roomId, false);
						}
					}
				}
				if(data.containsKey("type")) {
					int type = Integer.parseInt(data.get("type").toString());
					JSONObject content = JsonUtil.strToJsonObject(data.get("content").toString());
					switch(type) {
					case 7: // 踢出房间
						isInterap = true;
						// 发送退出房间消息
						RoomListenFactory.outRoom(userId, sessionId, roomId, socket);
						// 重新进入其他房间
						RoomListenFactory.changeRoom(userId, roomId, true);
						break;
					case 8: // 红包
						if(content != null && content.containsKey("id")) {
							String id = content.getString("id");
							String redRoom = content.getString("roomId");
							System.out.println("收到本房间红包通知，房间：" + redRoom);
							GrapRedThread red = new GrapRedThread();
							boolean isInRoom = false;
							if(!roomId.equals(redRoom)) {
								// 发送退出房间消息
								isInRoom = true;
								isInterap = true;
								// 发送退出房间消息
								RoomListenFactory.outRoom(userId, sessionId, roomId, socket);
							}
							red.setInRoom(isInRoom);
							red.setRoomId(redRoom);
							red.setUserId(userId);
							red.setSessionId(sessionId);
							red.setRebId(id);
							red.setSocket(socket);
							red.setToken(token);
							ThreadManager.getInstance().execute(red);
						}
						break;
						
					case 17: 
						if(content != null && content.containsKey("roomId")) {
							String peacRoom = content.getString("roomId");
							if(content.containsKey("peachRipeLevel")) {
								int level = content.getIntValue("peachRipeLevel");
								if(level == 5) {
									System.out.println("收到成熟通知，开启抢桃，房间：" + peacRoom);
									PeachThread peach = new PeachThread();
									peach.setRoomId(peacRoom);
									peach.setSessionId(sessionId);
									peach.setUserId(userId);
									boolean isInRoom = false;
									if(!peacRoom.equals(roomId)) {
										isInRoom = true;
										isInterap = true;
										// 发送退出房间消息
										RoomListenFactory.outRoom(userId, sessionId, roomId, socket);
									}
									peach.setInRoom(isInRoom);
									ThreadManager.getInstance().execute(peach);
								}
							}
						}
						break;
					case 50: 
						if(content != null && content.containsKey("roomId")) {
							String room = content.getString("roomId");
							if(content.containsKey("uriMsg")) {
								String uriMsg = content.getString("uriMsg");
								if(uriMsg.indexOf("宝箱") > -1) {
									System.out.println("收到宝箱通知，开启抢宝箱，房间：" + room);
									GrapBoxThread box = new GrapBoxThread();
									box.setRoomId(room);
									box.setUserId(userId);
									box.setSessionId(sessionId);
									boolean isInRoom = false;
									if(!room.equals(roomId)) {
										isInRoom = true;
										isInterap = true;
										// 发送退出房间消息
										RoomListenFactory.outRoom(userId, sessionId, roomId, socket);
									}
									box.setInRoom(isInRoom);
									ThreadManager.getInstance().execute(box);
								} else if(uriMsg.indexOf("红包") > -1) {
									if(!roomId.equals(room)) {
										// 发送退出房间消息
										isInterap = true;
										// 发送退出房间消息
										RoomListenFactory.outRoom(userId, sessionId, roomId, socket);
										// 进入待抢红包房
										Thread.sleep(RandomUtil.getRandom(5000, 10000));
										RoomListenFactory.listenRoom(userId, sessionId, room);
									}
								}
							}
						}
						break;
					}
				}
			}
		} catch(Exception e) {
			//
			isInterap = true;
			RoomListenFactory.outRoom(userId, sessionId, roomId, socket);
		}
		return isInterap;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
