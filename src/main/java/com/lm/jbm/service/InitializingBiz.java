package com.lm.jbm.service;

import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.factory.SocketFactory;
import com.lm.jbm.socket.SocketListenThread;
import com.lm.jbm.thread.ThreadManager;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;
import com.lm.jbm.utils.UserIPUtil;
import com.lm.jbm.utils.UserUtil;

public class InitializingBiz {
	/**
	 * 用户被踢出的房间
	 */
	public static ConcurrentHashMap<String, String> OUTROOM_MAP = new ConcurrentHashMap<String, String>(512);
	
	/**
	 * 初始化服务，获取首页--获取当前时段登录用户--登录--创建socket--随机进入房间--监听消息
	 */
	public static void init() {
		try {
			List<String> rooms = getRoom("1");
			List<String> users = UserUtil.getUsers(5);
			if(users != null && users.size() >0) {
				for(String userId : users) {
					String sessionId = CommonService.login(userId);
					
					CommonService.sign(userId, sessionId);
					
					Thread.sleep(3000);
					
					Socket socket = SocketFactory.connect(userId);
					SocketListenThread t = new SocketListenThread(userId, 
							rooms.get(RandomUtil.getRandom(0, rooms.size())),sessionId, socket);
					ThreadManager.getInstance().execute(t);
				}
			}
		} catch(Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 初始化新监听用户
	 */
	public static void newUserListen() {
		try {
			String userId = UserUtil.getUser();
			if(StringUtils.isEmpty(userId)) {
				return;
			}
			List<String> rooms = getRoom("1");
			String sessionId = CommonService.login(userId);
			
			CommonService.sign(userId, sessionId);
			
			Thread.sleep(3000);
			
			Socket socket = SocketFactory.connect(userId);
			SocketListenThread t = new SocketListenThread(userId, 
					rooms.get(RandomUtil.getRandom(0, rooms.size())),sessionId, socket);
			ThreadManager.getInstance().execute(t);
		} catch(Exception e) {
			LogUtil.log.error("重启新用户发送异常");
			LogUtil.log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 被踢出后，更换房间
	 * @param userId
	 * @param oldRoom
	 */
	public static void changeRoom(String userId, String oldRoom) {
		try {
			OUTROOM_MAP.put(userId, oldRoom);
			String newRoom = getNewRoom();
			
			String ip = UserIPUtil.getIP(userId);
			String sessionId = CommonService.login(userId);
			Socket socket = SocketFactory.connect(userId);
			
			Thread.sleep(5000);
			
			SocketListenThread t = new SocketListenThread(userId, newRoom, sessionId, socket);
			ThreadManager.getInstance().execute(t);
		} catch(Exception e) {
			LogUtil.log.error(e.getMessage(), e);
		}
	}
	
	private static String getNewRoom() {
		List<String> rooms = getRoom("2");
		String newRoom = "";
		int recount = 0;
		int size = rooms.size();
		while(true) {
			if(recount == size) {
				LogUtil.log.error("### 没有可使用的房间。。。");
				break;
			}
			newRoom = rooms.get(RandomUtil.getRandom(0, size));
			if(!OUTROOM_MAP.containsKey(newRoom)) {
				break;
			}
			recount++;
		}
		return newRoom;
	}
	
	private static List<String> getRoom(String pageNum) {
		try {
			List<String> rooms = CommonService.qryHome(pageNum);
			while(true) {
				if(rooms != null && rooms.size() > 0) {
					System.err.println("取到的主播：" + rooms.toString());
					return rooms;
				}
				LogUtil.log.info("## 未获取到首页数据，睡眠2S，重新获取。。。");
				Thread.sleep(2000);
				String num = String.valueOf((Integer.parseInt(pageNum) + 1));
				getRoom(num);
			}
		} catch(Exception e) {
			
		}
		return null;
	}
}
