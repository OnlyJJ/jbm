package com.lm.jbm.factory;

import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.contants.MessageFunID;
import com.lm.jbm.msg.MsgManager;
import com.lm.jbm.service.CommonService;
import com.lm.jbm.socket.SocketListenThread;
import com.lm.jbm.thread.ThreadManager;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;

public class RoomListenFactory {
	/**
	 * 用户被踢出的房间
	 */
	public static ConcurrentHashMap<String, String> OUTROOM_MAP = new ConcurrentHashMap<String, String>(512);
	
	/**
	 * 进入房间，并监听房间消息
	 */
	public static void listenRoom(String userId, String sessionId, String roomId) {
		try {
			// 链接socket，并启动线程监听消息
			Socket socket = SocketFactory.connect(userId);
			String token = SocketFactory.getToken(userId, sessionId, socket);
			// 进入房间
			inRoom(roomId, userId, token, socket.getOutputStream());
			// 监听房间消息
			SocketListenThread t = new SocketListenThread();
			t.setRoomId(roomId);
			t.setSessionId(sessionId);
			t.setUserId(userId);;
			t.setSocket(socket);
			ThreadManager.getInstance().execute(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	
	/**
	 * 离开房间
	 */
	public static void outRoom(String userId, String sessionId, String roomId, Socket socket) {
		try {
			// 链接socket，并启动线程监听消息
			String token = SocketFactory.getToken(userId, sessionId, socket);
			// 离开房间
			outRoom(roomId, userId, token, socket.getOutputStream());
		} catch(Exception e) {
			//
		}
	}
	
	/**
	 * 被踢出后，更换房间
	 * @param userId
	 * @param oldRoom
	 * @param isShotOff 是否被踢出房间，true-是
	 */
	public static void changeRoom(String userId, String oldRoom, boolean isShotOff) {
		try {
			if(isShotOff) {
				OUTROOM_MAP.put(userId, oldRoom);
			}
			String newRoom = getNewRoom(userId, isShotOff, oldRoom);
			String sessionId = CommonService.getSessionId(userId);
			Thread.sleep(5000);
			listenRoom(userId, sessionId, newRoom);
		} catch(Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
	}
	
	
	
	private static String getNewRoom(String userId, boolean isShotOff, String oldRoom) {
		List<String> rooms = CommonService.getRoom(userId, "2");
		String newRoom = "";
		int recount = 0;
		int size = rooms.size();
		while(true) {
			if(recount == size) {
				LogUtil.log.error("### 没有可使用的房间。。。");
				break;
			}
			newRoom = rooms.get(RandomUtil.getRandom(0, size));
			if(!isShotOff) {
				if((!newRoom.equals(oldRoom))) {
					break;
				}
				continue;
			}
			if(!OUTROOM_MAP.containsKey(newRoom)) {
				break;
			}
			recount++;
		}
		return newRoom;
	}
	
	private static void inRoom(String roomId, String userId, String token, OutputStream ops) throws Exception {
		try {
            JSONObject chatMSgObject = new JSONObject();
            chatMSgObject.put("funID", MessageFunID.FUNID_11006.getFunID());

            JSONObject dataObject = new JSONObject();
            dataObject.put("token", token);
            dataObject.put("roomId", roomId);

            chatMSgObject.put("seqID", MsgManager.getSeqID());
            chatMSgObject.put("data", dataObject);

           String data = chatMSgObject.toString();
           MsgManager.sendSocketMsg(ops, data);
        } catch (Exception e) {
            LogUtil.log.error(e.getMessage());
            throw e;
        }
		LogUtil.log.info("加入房间：roomId = " + roomId + " , userId = " + userId);
		System.err.println("加入房间：roomId = " + roomId + ", userId = " + userId);
	}
	
	private static void outRoom(String roomId, String userId, String token, OutputStream ops) throws Exception {
        try {
            JSONObject chatMSgObject = new JSONObject();
            chatMSgObject.put("funID", MessageFunID.FUNID_11007.getFunID());

            JSONObject dataObject = new JSONObject();
            dataObject.put("token", token);
            dataObject.put("roomId", roomId);

            chatMSgObject.put("seqID", MsgManager.getSeqID());
            chatMSgObject.put("data", dataObject);

            String content = chatMSgObject.toString();
            MsgManager.sendSocketMsg(ops, content);
        } catch (Exception e) {
        	LogUtil.log.error(e.getMessage());
        	throw e;
        }
        System.out.println("退出房间：roomId = " + roomId + " ,userId = " + userId);
		LogUtil.log.info("退出房间：roomId = " + roomId + " ,userId = " + userId);
	}
	
}
