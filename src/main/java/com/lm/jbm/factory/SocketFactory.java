package com.lm.jbm.factory;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.contants.MessageFunID;
import com.lm.jbm.msg.MsgManager;
import com.lm.jbm.socket.SocketHertThread;
import com.lm.jbm.thread.ThreadManager;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.PropertiesUtil;

public class SocketFactory {
	/**
	 * 用户sokcet信息 
	 */
	public static ConcurrentHashMap<String,Socket> SOCKET_MAP = new ConcurrentHashMap<String,Socket>();
	/**
	 * 用户socket的token信息
	 */
	public static ConcurrentHashMap<String,String> TOKEN_MAP = new ConcurrentHashMap<String, String>();
	
	private static final String URL = PropertiesUtil.getValue("SOCKET_URL");
	private static final int PORT = Integer.parseInt(PropertiesUtil.getValue("SOKCET_PORT"));
	
	/**
	 * 获取用户socket，已连接则直接返回有效socket，否则重连，并发送心跳
	 * @param userId
	 */
	public static Socket connect(String userId) {
		Socket socket = null;
		if(!SOCKET_MAP.containsKey(userId)) {
			socket = init(userId);
		}
		socket = SOCKET_MAP.get(userId);
		if(socket.isClosed() || !socket.isConnected()) {
			socket = init(userId);
		}
		String server = socket.getRemoteSocketAddress().toString();
		if(StringUtils.isEmpty(server) || server.indexOf("9999") < 0) {
			socket = init(userId);
		}
		LogUtil.log.info("链接socket，userId= " + userId + ",获取socket服务端信息：" + server);
		return socket;
	}
	
	/**
	 * 获取socket的token信息
	 */
	public static String getToken(String userId, String sessionId, Socket socket) {
		String token = "";
		try {
			if(TOKEN_MAP.containsKey(userId)) {
				token = TOKEN_MAP.get(userId);
			} else {
				// 推送用户验证，获取token
				
				MsgManager.sendSocketMsg(socket.getOutputStream(), encaseVerificData(userId, "", sessionId));
				String imAuthenticationResponseStr = MsgManager.recieve(socket);
				JSONObject json = JsonUtil.strToJsonObject(imAuthenticationResponseStr);
				JSONObject data = json.getJSONObject("data");
				token = data.getString("token");
				TOKEN_MAP.put(userId, token);
				System.err.println("token不存在，重新请求获取token = " + token + "，userId：" + userId);
			}
		} catch(Exception e) {
			//
		}
		System.err.println("获取token：：" + token + "，userId：" + userId);
		LogUtil.log.info("获取token：：" + token + "，userId：" + userId);
		return token;
	}
	
	
	/**
	 * 销毁socket
	 * @param socket
	 * @param userId
	 */
	public static void destory(Socket socket, String userId) {
		LogUtil.log.info("用户已过期，销毁socket！");
		if(SOCKET_MAP.containsKey(userId)) {
			SOCKET_MAP.remove(userId);
		}
		if(TOKEN_MAP.containsKey(userId)) {
			TOKEN_MAP.remove(userId);
		}
		try {
			if(socket != null) {
				socket.close();
			}
		} catch(Exception e) {
			
		}
	}
	
	private static String encaseVerificData(String uid, String token, String sessionid) {
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

	/**
	 * 初始化socket
	 * @param userId
	 */
	private static Socket init(String userId) {
		// 创建socket
		Socket socket = null;
		try {
			socket = new Socket(URL, PORT);
			SOCKET_MAP.put(userId, socket);
			// 发送心跳
			SocketHertThread hert = new SocketHertThread(socket, userId);
			ThreadManager.getInstance().execute(hert);
			
		} catch(Exception e) {
			LogUtil.log.error(e.getMessage());
		}
		return socket;
	}
}
