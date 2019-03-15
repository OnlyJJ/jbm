package com.lm.jbm.socket;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.contants.MessageFunID;
import com.lm.jbm.factory.SocketFactory;
import com.lm.jbm.service.InitializingBiz;
import com.lm.jbm.thread.GrapBoxThread;
import com.lm.jbm.thread.GrapRedThread;
import com.lm.jbm.thread.PeachThread;
import com.lm.jbm.thread.ThreadManager;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.UserUtil;


public class SocketListenThread implements Runnable {

	private String userId;
	private String roomId;
	private String sessionId;
	private Socket socket;
	
	private static String token;
	private static OutputStream ops = null;
	
	public SocketListenThread(String userId, String roomId, String sessionId, Socket socket) {
		this.userId = userId;
		this.roomId = roomId;
		this.sessionId = sessionId;
		this.socket = socket;
	}

	public void run() {
		try {
			// 推送用户验证，获取token
			MsgManager.sendSocketMsg(socket.getOutputStream(), encaseVerificData(userId, "", sessionId));
			token = MsgManager.getToken(socket);
			
			Thread.sleep(5000);
			ops = socket.getOutputStream();
			MsgManager.getInstance().inRoom(roomId, userId, token, ops);
			while(true) {
				try {
					String msg = MsgManager.recieve(socket);
					System.err.println("收到的消息：" + msg);
					handleMsg(msg);
					validUser(userId);
				} catch (Exception e) {
					throw e;
				}
			}
		} catch(Exception e) {
			System.err.println("监听消息异常：" + e.getMessage());
			LogUtil.log.error(e.getMessage(), e);
			try {
				if(socket != null) {
					socket.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(ops != null) {
				try {
					ops.close();
				} catch (IOException e1) {
					LogUtil.log.error(e.getMessage(), e);
				}
			}
		}
	}
	
	private String encaseVerificData(String uid, String token, String sessionid) {
		 JSONObject postJsonObject = new JSONObject();

	        try {
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("token", token);
	            jsonObject.put("uid", uid);
	            jsonObject.put("sessionid", sessionid);

	            postJsonObject.put("data", jsonObject);
	            postJsonObject.put("funID", MessageFunID.FUNID_11000.getFunID());
	            postJsonObject.put("seqID", MsgManager.getSeqID());
	            return postJsonObject.toString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	}

	private void handleMsg(String msg) throws Exception {
		try {
			if(StringUtils.isEmpty(msg)) {
				return;
			}
			JSONObject ret = JsonUtil.strToJsonObject(msg);
			JSONObject data = JsonUtil.strToJsonObject(ret.getString("data"));
			if(data != null) {
				if(data.containsKey("status")) {
					if(data.getIntValue("status") == 5007) {
						try {
							SocketFactory.destory(socket, userId);
							
							Thread.sleep(30 * 1000);
							
							InitializingBiz.changeRoom(userId, roomId);
						} catch(Exception e) {
							//
						}
					}
				}
				if(data.containsKey("type")) {
					int type = Integer.parseInt(data.get("type").toString());
					JSONObject content = JsonUtil.strToJsonObject(data.get("content").toString());
					switch(type) {
					case 7: // 踢出房间
						// 发送退出房间消息
						MsgManager.getInstance().outRoom(msg, msg, token, ops);
						// 重新进入其他房间
						InitializingBiz.changeRoom(userId, roomId);
						break;
					case 8: // 红包
						if(content != null && content.containsKey("id")) {
							String id = content.getString("id");
							String redRoom = content.getString("roomId");
							System.out.println("收到红包通知，房间：" + redRoom);
							GrapRedThread red = new GrapRedThread();
							if(roomId.equals(redRoom)) {
								red.setInRoom(false);
							}
							red.setRoomId(redRoom);
							red.setUserId(userId);
							red.setToken(token);
							red.setSessionId(sessionId);
							red.setSocket(socket);
							red.setRebId(id);
							ThreadManager.getInstance().execute(red);
						}
						break;
						
					case 17: 
						if(content != null && content.containsKey("roomId")) {
							String peacRoom = content.getString("roomId");
							boolean flag = false;
							if(content.containsKey("peachRipeLevel")) {
								int level = content.getIntValue("peachRipeLevel");
								if(level == 5) {
									flag = true;
								}
							}
							if(flag) {
								System.out.println("收到成熟通知，开启抢桃，房间：" + peacRoom);
								PeachThread peach = new PeachThread();
								peach.setRoomId(peacRoom);
								peach.setSessionId(sessionId);
								peach.setSocket(socket);
								peach.setToken(token);
								peach.setUserId(userId);
								boolean isInRoom = false;
								if(!peacRoom.equals(roomId)) {
									isInRoom = true;
								}
								peach.setInRoom(isInRoom);
								ThreadManager.getInstance().execute(peach);
							}
						}
						break;
					case 50: 
						if(content != null && content.containsKey("roomId")) {
							String boxRoom = content.getString("roomId");
							if(content.containsKey("uriMsg")) {
								String uriMsg = content.getString("uriMsg");
								if(uriMsg.indexOf("宝箱") > -1) {
									System.out.println("收到宝箱通知，开启抢宝箱，房间：" + boxRoom);
									GrapBoxThread box = new GrapBoxThread();
									box.setRoomId(boxRoom);
									box.setUserId(userId);
									box.setToken(token);
									box.setSessionId(sessionId);
									box.setSocket(socket);
									boolean isInRoom = false;
									if(!boxRoom.equals(roomId)) {
										isInRoom = true;
									}
									box.setInRoom(isInRoom);
									ThreadManager.getInstance().execute(box);
								}
							}
						}
						break;
					}
				}
			}
		} catch(Exception e) {
			//
		}
	}
	
	
	private void validUser(String userId) {
		if(UserUtil.checkValid(userId)) {
			// 销毁socket
			SocketFactory.destory(socket, userId);
			// 登录新用户
			InitializingBiz.newUserListen();
		}
	}
}
