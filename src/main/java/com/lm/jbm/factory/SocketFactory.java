package com.lm.jbm.factory;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.contants.Contant;
import com.lm.jbm.socket.SocketHertThread;
import com.lm.jbm.thread.ThreadManager;
import com.lm.jbm.utils.LogUtil;

public class SocketFactory {
	/**
	 * 用户sokcet维护map 
	 */
	public static ConcurrentHashMap<String,Socket> SOCKET_MAP = new ConcurrentHashMap<String,Socket>();
	
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
		System.err.println("获取socket服务端信息：" + server);
		return socket;
	}
	
	/**
	 * 初始化socket
	 * @param userId
	 */
	private static Socket init(String userId) {
		// 创建socket
		Socket socket = null;
		try {
			socket = new Socket(Contant.IP, Contant.PORT);
			 System.err.println("创建socket成功，服务端："+ socket.getRemoteSocketAddress().toString());
			 LogUtil.log.info("创建socket成功，服务端："+ socket.getRemoteSocketAddress().toString());
			// 发送心跳
			SocketHertThread hert = new SocketHertThread(socket);
			ThreadManager.getInstance().execute(hert);
			
			SOCKET_MAP.put(userId, socket);
		} catch(Exception e) {
			 LogUtil.log.error(e.getMessage());
		}
		return socket;
	}
	
	/**
	 * 销毁socket
	 * @param socket
	 * @param userId
	 */
	public static void destory(Socket socket, String userId) {
		if(SOCKET_MAP.containsKey(userId)) {
			SOCKET_MAP.remove(userId);
		}
		try {
			if(socket != null) {
				socket.close();
			}
		} catch(Exception e) {
			
		}
	}
}
