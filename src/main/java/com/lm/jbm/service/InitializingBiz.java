package com.lm.jbm.service;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.lm.jbm.factory.RoomListenFactory;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.RandomUtil;
import com.lm.jbm.utils.UserUtil;

public class InitializingBiz {
	/**
	 * 初始化服务，获取首页--获取当前时段登录用户--登录--创建socket--随机进入房间--监听消息
	 */
	public static void init() {
		try {
			List<String> users = UserUtil.getUsers(5);
			if(users != null && users.size() >0) {
				for(String userId : users) {
					List<String> rooms = CommonService.getRoom(userId, "1");
					// 登录
					String sessionId = CommonService.login(userId);
					// 签到
					CommonService.sign(userId, sessionId);
					Thread.sleep(3000);
					// 进入房间并监听消息
					String roomId = rooms.get(RandomUtil.getRandom(0, rooms.size()));
					RoomListenFactory.listenRoom(userId, sessionId, roomId);
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
			// 获取首页房间
			List<String> rooms = CommonService.getRoom(userId, "1");
			// 登录
			String sessionId = CommonService.login(userId);
			// 签到
			CommonService.sign(userId, sessionId);
			Thread.sleep(3000);
			// 进入房间并监听消息
			String roomId = rooms.get(RandomUtil.getRandom(0, rooms.size()));
			RoomListenFactory.listenRoom(userId, sessionId, roomId);
			System.out.println("重新启动一个新用户监听，取到的用户：" + userId);
		} catch(Exception e) {
			LogUtil.log.error("重启新用户发送异常");
			LogUtil.log.error(e.getMessage(), e);
		}
	}
	
}
