package com.lm.jbm.utils;

import java.util.List;

import com.lm.jbm.service.JmService;
import com.lm.jbm.thread.PeachThread;
import com.lm.jbm.thread.ThreadManager;

public class PluckUtil {

	public static void sortInRoom(String roomId, int real, int members, List<String> userIds) {

	}

	/**
	 * 在线成员5人以下
	 * @author Shao.x
	 * @date 2018年12月1日
	 */
	private static void sort1(String roomId, int real, List<String> userIds) throws InterruptedException {
		if(userIds != null && userIds.size() >0) {
			int size = userIds.size();
			boolean flag = JmService.checkFreeTime(); // 是否是 01:00 ~ 10:30
			Thread.sleep(4000); 
			int sleepTime = 500;
			for(int i=0; i<size; i++) {
				String userId = userIds.get(i);
				PeachThread peach = new PeachThread(roomId, userId, real, true);
				ThreadManager.getInstance().execute(peach);
				if(i >= 2) {
					if(flag) {
						sleepTime = 900;
					} else {
						sleepTime = 500;
					}
				} else if(i >= 4) {
					if(flag) {
						sleepTime = 700;
					} else {
						sleepTime = 400;
					}
				} else if(i >= 6) {
					if(flag) {
						sleepTime = 600;
					} else {
						sleepTime = 300;
					}
				} else if(i >= 8) {
					if(flag) {
						sleepTime = 500;
					} else {
						sleepTime = 200;
					}
				} 
				Thread.sleep(sleepTime);
			}
		}
	}

	private static void sort2(List<String> userIds) {

	}

	private static void sort3(List<String> userIds) {

	}

	private static void sort4(List<String> userIds) {

	}

	private static void sort5(List<String> userIds) {

	}

	private static void sort6(List<String> userIds) {

	}

	private static void sort7(List<String> userIds) {

	}
}
